package com.qfy.modules.config.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jeecg.dingtalk.api.base.JdtBaseAPI;
import com.jeecg.dingtalk.api.core.response.Response;
import com.jeecg.dingtalk.api.core.vo.AccessToken;
import com.jeecg.dingtalk.api.core.vo.PageResult;
import com.jeecg.dingtalk.api.department.JdtDepartmentAPI;
import com.jeecg.dingtalk.api.department.vo.Department;
import com.jeecg.dingtalk.api.message.JdtMessageAPI;
import com.jeecg.dingtalk.api.message.vo.ActionCardMessage;
import com.jeecg.dingtalk.api.message.vo.MarkdownMessage;
import com.jeecg.dingtalk.api.message.vo.Message;
import com.jeecg.dingtalk.api.message.vo.TextMessage;
import com.jeecg.dingtalk.api.oauth2.JdtOauth2API;
import com.jeecg.dingtalk.api.oauth2.vo.ContactUser;
import com.jeecg.dingtalk.api.user.JdtUserAPI;
import com.jeecg.dingtalk.api.user.body.GetUserListBody;
import com.jeecg.dingtalk.api.user.vo.User;
import com.qfy.modules.config.ThirdAppEnum;
import com.qfy.modules.config.ThirdConfigFactory;
import com.qfy.modules.config.entity.ThirdConfig;
import com.qfy.modules.dingtalk.service.IThirdAppService;
import com.qfy.modules.dingtalk.vo.thirdapp.JdtDepartmentTreeVo;
import com.qfy.modules.dingtalk.vo.thirdapp.SyncInfoVo;
import com.qfy.modules.util.ConvertRGBToHex;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.dto.message.MessageDTO;
import org.jeecg.common.config.TenantContext;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.constant.UserIdentityEnum;
import org.jeecg.common.constant.UserTypeEnum;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.PasswordUtil;
import org.jeecg.common.util.RestUtil;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.config.JeecgBaseConfig;
import org.jeecg.modules.system.entity.*;
import org.jeecg.modules.system.mapper.SysAnnouncementSendMapper;
import org.jeecg.modules.system.mapper.SysUserMapper;
import org.jeecg.modules.system.model.SysDepartTreeModel;
import org.jeecg.modules.system.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 第三方App对接：钉钉实现类
 * @author: jeecg-boot
 */
@Slf4j
@Service
public class ThirdAppDingtalkServiceImpl implements IThirdAppService {

    @Autowired
    private ISysDepartService sysDepartService;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private ISysThirdAccountService sysThirdAccountService;
    @Autowired
    private ISysUserDepartService sysUserDepartService;
    @Autowired
    private ISysPositionService sysPositionService;
    @Autowired
    private SysAnnouncementSendMapper sysAnnouncementSendMapper;
    @Autowired
    private ISysTenantService tenantService;

    /**
     * 第三方APP类型，当前固定为 dingtalk
     */
    public final String THIRD_TYPE = ThirdAppEnum.DINGTALK.getCode();

    @Override
    public String getAccessToken(Integer tenantId) {
        ThirdConfig thirdConfig = ThirdConfigFactory.getConfig(tenantId,THIRD_TYPE);

        String appKey = thirdConfig.getClientId();
        String appSecret = thirdConfig.getClientSecret();
        AccessToken accessToken = JdtBaseAPI.getAccessToken(appKey, appSecret);
        if (accessToken != null) {
            return accessToken.getAccessToken();
        }
        log.warn("获取AccessToken失败");
        return null;
    }

    // update：2022-1-21，updateBy：sunjianlei; for 【JTC-704】【钉钉】部门同步成功，实际没成，后台提示ip白名单
    @Override
    public SyncInfoVo syncLocalDepartmentToThirdApp(String ids,Integer tenantId) {
        SyncInfoVo syncInfo = new SyncInfoVo();
        String accessToken = this.getAccessToken(tenantId);
        if (accessToken == null) {
            syncInfo.addFailInfo("accessToken获取失败！");
            return syncInfo;
        }
        // 获取【钉钉】所有的部门
        List<Response<Department>> departments = JdtDepartmentAPI.listAllResponse(accessToken);
        // 删除钉钉有但本地没有的部门（以本地部门数据为主）（钉钉不能创建同名部门，只能先删除）
        List<SysDepart> sysDepartList = sysDepartService.list();
        for1:
        for (Response<Department> departmentRes : departments) {
            // 判断部门是否查询成功
            if (!departmentRes.isSuccess()) {
                syncInfo.addFailInfo(departmentRes.getErrmsg());
                // 88 是 ip 不在白名单的错误码，如果遇到此错误码，后面的操作都可以不用进行了，因为肯定都是失败的
                if (new Integer(88).equals(departmentRes.getErrcode())) {
                    return syncInfo;
                }
                continue;
            }
            Department department = departmentRes.getResult();
            for (SysDepart depart : sysDepartList) {
                // id相同，代表已存在，不删除
                String sourceIdentifier = department.getSource_identifier();
                if (sourceIdentifier != null && sourceIdentifier.equals(depart.getId())) {
                    continue for1;
                }
            }
            // 循环到此说明本地没有，删除
            int deptId = department.getDept_id();
            // 钉钉不允许删除带有用户的部门，所以需要判断下，将有用户的部门的用户移动至根部门
            Response<List<String>> userIdRes = JdtUserAPI.getUserListIdByDeptId(deptId, accessToken);
            if (userIdRes.isSuccess() && userIdRes.getResult().size() > 0) {
                for (String userId : userIdRes.getResult()) {
                    User updateUser = new User();
                    updateUser.setUserid(userId);
                    updateUser.setDept_id_list(1);
                    JdtUserAPI.update(updateUser, accessToken);
                }
            }
            JdtDepartmentAPI.delete(deptId, accessToken);
        }
        // 获取本地所有部门树结构
        List<SysDepartTreeModel> sysDepartsTree = sysDepartService.queryTreeList();
        // -- 钉钉不能创建新的顶级部门，所以新的顶级部门的parentId就为1
        Department parent = new Department();
        parent.setDept_id(1);
        // 递归同步部门
        departments = JdtDepartmentAPI.listAllResponse(accessToken);
        this.syncDepartmentRecursion(sysDepartsTree, departments, parent, accessToken, syncInfo);
        return syncInfo;
    }

    /**
     * 递归同步部门到本地
     * @param sysDepartsTree
     * @param departments
     * @param parent
     * @param accessToken
     * @param syncInfo
     */
    public void syncDepartmentRecursion(List<SysDepartTreeModel> sysDepartsTree, List<Response<Department>> departments, Department parent, String accessToken, SyncInfoVo syncInfo) {
        if (sysDepartsTree != null && sysDepartsTree.size() != 0) {
            for1:
            for (SysDepartTreeModel depart : sysDepartsTree) {
                for (Response<Department> departmentRes : departments) {
                    // 判断部门是否查询成功
                    if (!departmentRes.isSuccess()) {
                        syncInfo.addFailInfo(departmentRes.getErrmsg());
                        continue;
                    }
                    Department department = departmentRes.getResult();
                    // id相同，代表已存在，执行修改操作
                    String sourceIdentifier = department.getSource_identifier();
                    if (sourceIdentifier != null && sourceIdentifier.equals(depart.getId())) {
                        this.sysDepartToDtDepartment(depart, department, parent.getDept_id());
                        Response<JSONObject> response = JdtDepartmentAPI.update(department, accessToken);
                        if (response.isSuccess()) {
                            // 紧接着同步子级
                            this.syncDepartmentRecursion(depart.getChildren(), departments, department, accessToken, syncInfo);
                        }
                        // 收集错误信息
                        this.syncDepartCollectErrInfo(response, depart, syncInfo);
                        // 跳出外部循环
                        continue for1;
                    }
                }
                // 循环到此说明是新部门，直接调接口创建
                Department newDepartment = this.sysDepartToDtDepartment(depart, parent.getDept_id());
                Response<Integer> response = JdtDepartmentAPI.create(newDepartment, accessToken);
                // 创建成功，将返回的id绑定到本地
                if (response.getResult() != null) {
                    Department newParent = new Department();
                    newParent.setDept_id(response.getResult());
                    // 紧接着同步子级
                    this.syncDepartmentRecursion(depart.getChildren(), departments, newParent, accessToken, syncInfo);
                }
                // 收集错误信息
                this.syncDepartCollectErrInfo(response, depart, syncInfo);
            }
        }
    }

    @Override
    public SyncInfoVo syncThirdAppDepartmentToLocal(String ids, Integer tenantId) {
        SyncInfoVo syncInfo = new SyncInfoVo();

        String accessToken = this.getAccessToken(tenantId);
        if (accessToken == null) {
            syncInfo.addFailInfo("accessToken获取失败！");
            return syncInfo;
        }
        // 获取【钉钉】所有的部门
        List<Department> departments = JdtDepartmentAPI.listAll(accessToken);
        String username = JwtUtil.getUserNameByToken(SpringContextUtils.getHttpServletRequest());
        List<JdtDepartmentTreeVo> departmentTreeList = JdtDepartmentTreeVo.listToTree(departments);
        // 递归同步部门
        JdtDepartmentTreeVo topParent = JdtDepartmentTreeVo.getTopParent(departments);
        String topDepartId = syncTopParentDepartmentToLocal(topParent,username,accessToken);
        this.syncDepartmentToLocalRecursion(departmentTreeList, topDepartId, username, syncInfo, accessToken);
        return syncInfo;
    }

    public String syncTopParentDepartmentToLocal(JdtDepartmentTreeVo topParent, String username,String accessToken) {
        //处理顶级部分
        QueryWrapper<SysDepart> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("parent_id");
        SysDepart parentDepart = sysDepartService.getOne(queryWrapper);

        parentDepart.setDingtalkIdentifier(topParent.getDept_id().toString());
        parentDepart.setUpdateBy(username);
        sysDepartService.updateById(parentDepart);
        // 更新钉钉 source_identifier
        Department updateDtDepart = new Department();
        updateDtDepart.setDept_id(topParent.getDept_id());
        updateDtDepart.setSource_identifier(parentDepart.getId());
        Response response = JdtDepartmentAPI.update(updateDtDepart, accessToken);
//        if (!response.isSuccess()) {
//            throw new RuntimeException(response.getErrmsg());
//        }

        return  parentDepart.getId();
    }

    public void syncDepartmentToLocalRecursion(List<JdtDepartmentTreeVo> departmentTreeList, String sysParentId, String username, SyncInfoVo syncInfo, String accessToken) {

        if (departmentTreeList != null && departmentTreeList.size() != 0) {
            for (JdtDepartmentTreeVo departmentTree : departmentTreeList) {
                LambdaQueryWrapper<SysDepart> queryWrapper = new LambdaQueryWrapper<>();
                // 根据 source_identifier 字段查询
                queryWrapper.or().eq(SysDepart::getDingtalkIdentifier, departmentTree.getDept_id());
                queryWrapper.or().eq(SysDepart::getDepartName, departmentTree.getName());
                SysDepart sysDepart = sysDepartService.getOne(queryWrapper);
                if (sysDepart != null) {
                    //  执行更新操作
                    SysDepart updateSysDepart = this.dtDepartmentToSysDepart(departmentTree, sysDepart);
                    updateSysDepart.setDingtalkIdentifier(departmentTree.getDept_id().toString());
                    if (sysParentId != null) {
                        updateSysDepart.setParentId(sysParentId);
                    }
                    try {
                        sysDepartService.updateDepartDataById(updateSysDepart, username);
                        String str = String.format("部门 %s 更新成功！", updateSysDepart.getDepartName());
                        syncInfo.addSuccessInfo(str);
                    } catch (Exception e) {
                        this.syncDepartCollectErrInfo(e, departmentTree, syncInfo);
                    }
                    if (departmentTree.hasChildren()) {
                        // 紧接着同步子级
                        this.syncDepartmentToLocalRecursion(departmentTree.getChildren(), updateSysDepart.getId(), username, syncInfo, accessToken);
                    }
                } else {
                    //  执行新增操作
                    SysDepart newSysDepart = this.dtDepartmentToSysDepart(departmentTree, null);
                    if (sysParentId != null) {
                        newSysDepart.setParentId(sysParentId);
                        // 2 = 组织机构
                        newSysDepart.setOrgCategory("2");
                    } else {
                        // 1 = 公司
                        newSysDepart.setOrgCategory("1");
                    }
                    try {
                        if(newSysDepart.getDingtalkIdentifier() != null){
                            newSysDepart.setId(newSysDepart.getDingtalkIdentifier());
                        }
                        sysDepartService.saveDepartData(newSysDepart, username);
                        // 更新钉钉 source_identifier
                        Department updateDtDepart = new Department();
                        updateDtDepart.setDept_id(departmentTree.getDept_id());
                        updateDtDepart.setSource_identifier(newSysDepart.getId());
                        Response response = JdtDepartmentAPI.update(updateDtDepart, accessToken);
                        if (!response.isSuccess()) {
                            throw new RuntimeException(response.getErrmsg());
                        }
                        String str = String.format("部门 %s 创建成功！", newSysDepart.getDepartName());
                        syncInfo.addSuccessInfo(str);
                    } catch (Exception e) {
                        this.syncDepartCollectErrInfo(e, departmentTree, syncInfo);
                    }
                    // 紧接着同步子级
                    if (departmentTree.hasChildren()) {
                        this.syncDepartmentToLocalRecursion(departmentTree.getChildren(), newSysDepart.getId(), username, syncInfo, accessToken);
                    }
                }
            }
        }
    }

    private boolean syncDepartCollectErrInfo(Exception e, Department department, SyncInfoVo syncInfo) {
        String msg;
        if (e instanceof DuplicateKeyException) {
            msg = e.getCause().getMessage();
        } else {
            msg = e.getMessage();
        }
        String str = String.format("部门 %s(%s) 同步失败！错误信息：%s", department.getName(), department.getDept_id(), msg);
        syncInfo.addFailInfo(str);
        return false;
    }

    /**
     * 【同步部门】收集同步过程中的错误信息
     */
    private boolean syncDepartCollectErrInfo(Response<?> response, SysDepartTreeModel depart, SyncInfoVo syncInfo) {
        if (!response.isSuccess()) {
            String str = String.format("部门 %s(%s) 同步失败！错误码：%s——%s", depart.getDepartName(), depart.getOrgCode(), response.getErrcode(), response.getErrmsg());
            syncInfo.addFailInfo(str);
            return false;
        } else {
            String str = String.format("部门户 %s(%s) 同步成功！", depart.getDepartName(), depart.getOrgCode());
            syncInfo.addSuccessInfo(str);
            return true;
        }
    }

    @Override
    public SyncInfoVo syncLocalUserToThirdApp(String ids,Integer tenantId) {
        SyncInfoVo syncInfo = new SyncInfoVo();
        String accessToken = this.getAccessToken(tenantId);
        if (accessToken == null) {
            syncInfo.addFailInfo("accessToken获取失败！");
            return syncInfo;
        }
        List<SysUser> sysUsers;
        if (StringUtils.isNotBlank(ids)) {
            String[] idList = ids.split(",");
            LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(SysUser::getId, (Object[]) idList);
            // 获取本地指定用户
            sysUsers = userMapper.selectList(queryWrapper);
        } else {
            // 获取本地所有用户
            sysUsers = userMapper.selectList(Wrappers.emptyWrapper());
        }
        // 查询钉钉所有的部门，用于同步用户和部门的关系
        List<Department> allDepartment = JdtDepartmentAPI.listAll(accessToken);

        for (SysUser sysUser : sysUsers) {
            // 外部模拟登陆临时账号，不同步
            if ("_reserve_user_external".equals(sysUser.getUsername())) {
                continue;
            }
            // 钉钉用户信息，不为null代表已同步过
            Response<User> dtUserInfo;
            /*
             * 判断是否同步过的逻辑：
             * 1. 查询 sys_third_account（第三方账号表）是否有数据，如果有代表已同步
             * 2. 本地表里没有，就先用手机号判断，不通过再用username(用户账号)判断。
             */
            SysThirdAccount sysThirdAccount = sysThirdAccountService.getOneBySysUserId(sysUser.getId(), THIRD_TYPE);
            if (sysThirdAccount != null && oConvertUtils.isNotEmpty(sysThirdAccount.getThirdUserId())) {
                // sys_third_account 表匹配成功，通过第三方userId查询出第三方userInfo
                dtUserInfo = JdtUserAPI.getUserById(sysThirdAccount.getThirdUserId(), accessToken);
            } else {
                // 手机号匹配
                Response<String> thirdUserId = JdtUserAPI.getUseridByMobile(sysUser.getPhone(), accessToken);
                // 手机号匹配成功
                if (thirdUserId.isSuccess() && oConvertUtils.isNotEmpty(thirdUserId.getResult())) {
                    // 通过查询到的userId查询用户详情
                    dtUserInfo = JdtUserAPI.getUserById(thirdUserId.getResult(), accessToken);
                } else {
                    // 手机号匹配失败，尝试使用username匹配
                    dtUserInfo = JdtUserAPI.getUserById(sysUser.getUsername(), accessToken);
                }
            }
            String dtUserId;
            // api 接口是否执行成功
            boolean apiSuccess;
            // 已同步就更新，否则就创建
            if (dtUserInfo != null && dtUserInfo.isSuccess() && dtUserInfo.getResult() != null) {
                User dtUser = dtUserInfo.getResult();
                dtUserId = dtUser.getUserid();
                User updateQwUser = this.sysUserToDtUser(sysUser, dtUser, allDepartment);
                Response<JSONObject> updateRes = JdtUserAPI.update(updateQwUser, accessToken);
                // 收集成功/失败信息
                apiSuccess = this.syncUserCollectErrInfo(updateRes, sysUser, syncInfo);
            } else {
                User newQwUser = this.sysUserToDtUser(sysUser, allDepartment);
                Response<String> createRes = JdtUserAPI.create(newQwUser, accessToken);
                dtUserId = createRes.getResult();
                // 收集成功/失败信息
                apiSuccess = this.syncUserCollectErrInfo(createRes, sysUser, syncInfo);
            }

            // api 接口执行成功，并且 sys_third_account 表匹配失败，就向 sys_third_account 里插入一条数据
            boolean flag = (sysThirdAccount == null || oConvertUtils.isEmpty(sysThirdAccount.getThirdUserId()));
            if (apiSuccess && flag) {
                if (sysThirdAccount == null) {
                    sysThirdAccount = new SysThirdAccount();
                    sysThirdAccount.setSysUserId(sysUser.getId());
                    sysThirdAccount.setStatus(1);
                    sysThirdAccount.setDelFlag(0);
                    sysThirdAccount.setThirdType(THIRD_TYPE);
                }
                // 设置第三方app用户ID
                sysThirdAccount.setThirdUserId(dtUserId);
                sysThirdAccountService.saveOrUpdate(sysThirdAccount);
            }
        }
        return syncInfo;
    }

    @Override
    @Transactional
    public SyncInfoVo syncThirdAppUserToLocal(Integer tenantId,String ids) {
        SyncInfoVo syncInfo = new SyncInfoVo();
        String accessToken = this.getAccessToken(tenantId);
        if (accessToken == null) {
            syncInfo.addFailInfo("accessToken获取失败！");
            return syncInfo;
        }

        // 获取本地用户
        QueryWrapper<SysUser> queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("tenant_id",tenantId);
        List<SysUser> sysUsersList = userMapper.selectList(queryWrapper1);

        List<Department> allDepartment = new ArrayList<>();
        if (StringUtils.isEmpty(ids)){
            // 查询钉钉所有的部门，用于同步用户和部门的关系
            allDepartment = JdtDepartmentAPI.listAll(accessToken);
        }else{
            String[] split = ids.split(",");
            QueryWrapper<SysDepart> queryWrapper = new QueryWrapper<SysDepart>();
            queryWrapper.in("id",split);
            List<SysDepart> departList = sysDepartService.list(queryWrapper);
            for (SysDepart depart : departList) {
                Department d = new Department();
                if(depart.getDingtalkIdentifier() != null){
                    Integer dingId = Integer.parseInt(depart.getDingtalkIdentifier());
                    d.setDept_id(dingId);
                    allDepartment.add(d);
                }

            }
        }

        // 根据钉钉部门查询所有钉钉用户，用于反向同步到本地
        List<User> ddUserList = this.getDtAllUserByDepartment(allDepartment, accessToken);
        // 记录已经同步过的用户id，当有多个部门的情况时，只同步一次
        Set<String> syncedUserIdSet = new HashSet<>();

        for (User dtUserInfo : ddUserList) {
            if (syncedUserIdSet.contains(dtUserInfo.getUserid())) {
                continue;
            }
            syncedUserIdSet.add(dtUserInfo.getUserid());

            List<SysUser> collect = sysUsersList.stream().filter(user -> (dtUserInfo.getMobile().equals(user.getUsername()) || dtUserInfo.getMobile().equals(user.getPhone() ) || dtUserInfo.getUserid().equals(user.getId()))
            ).collect(Collectors.toList());

            //第三方账号关系表
            SysThirdAccount sysThirdAccount = sysThirdAccountService.getOneByThirdUserId(dtUserInfo.getUserid(), THIRD_TYPE);
            //判断系统账号是否存在
            if( (collect == null || collect.size()<=0 ) && sysThirdAccount != null){
                SysUser sysUserTemp = userMapper.selectById(sysThirdAccount.getSysUserId());
                if(sysUserTemp == null) {
                    sysUserTemp = this.dtUserToSysUser(sysThirdAccount.getSerialNo(),dtUserInfo);

                }
                sysUserTemp.setPhone(dtUserInfo.getMobile());
                userMapper.updateById(sysUserTemp);
                this.thirdAccountSaveOrUpdate(sysThirdAccount, sysUserTemp, dtUserInfo.getUserid());
                continue;
            }

            if(collect != null && collect.size() >0 ){
                SysUser sysUserTemp = collect.get(0);
                this.dtUserToSysUser(dtUserInfo, sysUserTemp);
                sysUserTemp.setTenantId(tenantId);
                sysUserTemp.setRelTenantIds(tenantId.toString());
                userMapper.updateById(sysUserTemp);
                String str = String.format("用户 %s(%s) 更新成功！", sysUserTemp.getRealname(), sysUserTemp.getUsername());
                syncInfo.addSuccessInfo(str);

                this.thirdAccountSaveOrUpdate(sysThirdAccount, sysUserTemp, dtUserInfo.getUserid());

            }else {
                //获取租户id 使用租户前缀
                SysTenant tenant = tenantService.getById(tenantId);
                //登录名处理
                String serialno = null;
                if(StringUtils.isNotEmpty(dtUserInfo.getMobile())){
                    QueryWrapper queryWrapper2 = new QueryWrapper();
                    queryWrapper2.eq("username",dtUserInfo.getMobile());
                    Long count = userMapper.selectCount(queryWrapper2);
                    if(count != null && count >0){
                        String  prefix = tenant.getPreCode();
                        serialno = sysThirdAccountService.getNextserial(prefix);
                    }else {
                        serialno = dtUserInfo.getMobile();
                    }
                }else {
                    String  prefix = tenant.getPreCode();
                    serialno = sysThirdAccountService.getNextserial(prefix);
                }

                // 如果没有匹配到用户，则走创建逻辑
                SysUser newSysUser = this.dtUserToSysUser(serialno,dtUserInfo);

                try {
                    Integer[] dept_id_listArray = dtUserInfo.getDept_id_listArray();

                    newSysUser.setDepartIds(dtUserInfo.getDept_id_list());
                    newSysUser.setTenantId(tenantId);
                    newSysUser.setRelTenantIds(tenantId.toString());
                    newSysUser.setUserIdentity(UserIdentityEnum.puTongUser.getType());
                    newSysUser.setColorCode(ConvertRGBToHex.rgb());

                    userMapper.insert(newSysUser);

                    //获取部门
                    if(dept_id_listArray.length >0){
                        String departIds = "";
                        for (Integer deptId : dept_id_listArray){
                            SysDepart sysDepart = sysDepartService.queryByDingtalk(dept_id_listArray[0].toString());
                            departIds += sysDepart.getId() +",";
                            newSysUser.setOrgCode(sysDepart.getOrgCode());
                            SysUserDepart userDepart = new SysUserDepart(newSysUser.getId(),sysDepart.getId().toString());
                            sysUserDepartService.save(userDepart);
                        }
                        newSysUser.setDepartIds(departIds.substring(0,departIds.length()-1));
                    }


                    String str = String.format("用户 %s(%s) 创建成功！", newSysUser.getRealname(), newSysUser.getUsername());
                    syncInfo.addSuccessInfo(str);
                } catch (Exception e) {
                    this.syncUserCollectErrInfo(e, dtUserInfo, syncInfo);
                }
                //第三方账号关系表
                this.thirdAccountSaveOrUpdate(null, newSysUser, dtUserInfo.getUserid());
            }

        }
        return syncInfo;
    }

    private List<User> getDtAllUserByDepartment(List<Department> allDepartment, String accessToken) {
        // 根据钉钉部门查询所有钉钉用户，用于反向同步到本地
        List<User> userList = new ArrayList<>();
        for (Department department : allDepartment) {
            this.getUserListByDeptIdRecursion(department.getDept_id(), 0, userList, accessToken);
        }
        return userList;
    }

    /**
     * 递归查询所有用户
     */
    private void getUserListByDeptIdRecursion(int deptId, int cursor, List<User> userList, String accessToken) {
        // 根据钉钉部门查询所有钉钉用户，用于反向同步到本地
        GetUserListBody getUserListBody = new GetUserListBody(deptId, cursor, 100);
        Response<PageResult<User>> response = JdtUserAPI.getUserListByDeptId(getUserListBody, accessToken);
        if (response.isSuccess()) {
            PageResult<User> page = response.getResult();
            userList.addAll(page.getList());
            if (page.getHas_more()) {
                this.getUserListByDeptIdRecursion(deptId, page.getNext_cursor(), userList, accessToken);
            }
        }
    }

    /**
     * 保存或修改第三方登录表
     *
     * @param sysThirdAccount 第三方账户表对象，为null就新增数据，否则就修改
     * @param sysUser       本地系统用户
     * @param dtUserId        钉钉用户ID
     */
    private void thirdAccountSaveOrUpdate(SysThirdAccount sysThirdAccount, SysUser sysUser, String dtUserId) {
        if (sysThirdAccount == null || sysThirdAccount.getId() == null) {
            sysThirdAccount = new SysThirdAccount();
            sysThirdAccount.setSysUserId(sysUser.getId());
            sysThirdAccount.setThirdUserUuid(dtUserId);
            sysThirdAccount.setThirdUserId(dtUserId);
            sysThirdAccount.setStatus(1);
            sysThirdAccount.setDelFlag(0);
            sysThirdAccount.setRealname(sysUser.getRealname());
            sysThirdAccount.setThirdType(THIRD_TYPE);
            sysThirdAccount.setUserType(sysUser.getUserType());
            sysThirdAccount.setPhone(sysUser.getPhone());
            sysThirdAccount.setSerialNo(sysUser.getUsername());
            sysThirdAccount.setAvatar(sysUser.getAvatar());
            sysThirdAccountService.save(sysThirdAccount);
        }else {
            sysThirdAccount.setPhone(sysUser.getPhone());
            sysThirdAccount.setThirdType(THIRD_TYPE);
            sysThirdAccount.setUserType(sysUser.getUserType());
            sysThirdAccount.setThirdUserId(dtUserId);
            sysThirdAccount.setRealname(sysUser.getRealname());
            sysThirdAccount.setSerialNo(sysUser.getUsername());
            sysThirdAccount.setAvatar(sysUser.getAvatar());
            sysThirdAccountService.saveOrUpdate(sysThirdAccount);
        }


    }

    /**
     * 【同步用户】收集同步过程中的错误信息
     */
    private boolean syncUserCollectErrInfo(Response<?> response, SysUser sysUser, SyncInfoVo syncInfo) {
        if (!response.isSuccess()) {
            String str = String.format("用户 %s(%s) 同步失败！错误码：%s——%s", sysUser.getUsername(), sysUser.getRealname(), response.getErrcode(), response.getErrmsg());
            syncInfo.addFailInfo(str);
            return false;
        } else {
            String str = String.format("用户 %s(%s) 同步成功！", sysUser.getUsername(), sysUser.getRealname());
            syncInfo.addSuccessInfo(str);
            return true;
        }
    }

    /**
     * 【同步用户】收集同步过程中的错误信息
     */
    private boolean syncUserCollectErrInfo(Exception e, User dtUser, SyncInfoVo syncInfo) {
        String msg;
        if (e instanceof DuplicateKeyException) {
            msg = e.getCause().getMessage();
            String emailUniq = "uniq_sys_user_email";
            if(msg.contains(emailUniq)){
                msg = "邮箱重复，请更换邮箱";
            }
            String workNoUniq="uniq_sys_user_work_no";
            if(msg.contains(workNoUniq)){
                msg = "工号重复，请更换工号";
            }
        } else {
            msg = e.getMessage();
        }
        String str = String.format("用户 %s(%s) 同步失败！错误信息：%s", dtUser.getUserid(), dtUser.getName(), msg);
        syncInfo.addFailInfo(str);
        return false;
    }


    /**
     * 【同步用户】将SysUser转为【钉钉】的User对象（创建新用户）
     */
    private User sysUserToDtUser(SysUser sysUser, List<Department> allDepartment) {
        User user = new User();
        // 通过 username 来关联
        user.setUserid(sysUser.getUsername());
        return this.sysUserToDtUser(sysUser, user, allDepartment);
    }

    /**
     * 【同步用户】将SysUser转为【钉钉】的User对象（更新旧用户）
     */
    private User sysUserToDtUser(SysUser sysUser, User user, List<Department> allDepartment) {
        user.setName(sysUser.getRealname());
        user.setMobile(sysUser.getPhone());
        user.setTelephone(sysUser.getTelephone());
        user.setJob_number(sysUser.getWorkNo());
        // 职务翻译
        if (oConvertUtils.isNotEmpty(sysUser.getPost())) {
            SysPosition position = sysPositionService.getByCode(sysUser.getPost());
            if (position != null) {
                user.setTitle(position.getName());
            }
        }
        user.setEmail(sysUser.getEmail());
        // 查询并同步用户部门关系
        List<SysDepart> departList = this.getUserDepart(sysUser);
        if (departList != null) {
            List<Integer> departmentIdList = new ArrayList<>();
            for (SysDepart sysDepart : departList) {
                // 企业微信的部门id
                Department department = this.getDepartmentByDepartId(sysDepart.getId(), allDepartment);
                if (department != null) {
                    departmentIdList.add(department.getDept_id());
                }
            }
            user.setDept_id_list(departmentIdList.toArray(new Integer[]{}));
            user.setDept_order_list(null);
        }
        if (oConvertUtils.isEmpty(user.getDept_id_list())) {
            // 没有找到匹配部门，同步到根部门下
            user.setDept_id_list(1);
            user.setDept_order_list(null);
        }
        // --- 钉钉没有逻辑删除功能
        // sysUser.getDelFlag()
        // --- 钉钉没有冻结、启用禁用功能
        // sysUser.getStatus()
        return user;
    }


    /**
     * 【同步用户】将【钉钉】的User对象转为SysUser（创建新用户）
     */
    private SysUser dtUserToSysUser(String username, User dtUser) {
        SysUser sysUser = new SysUser();
        sysUser.setDelFlag(0);
        // 通过 username 来关联
        sysUser.setUsername(username);
        // 密码默认为 “123456”，随机加盐
        String password = username, salt = oConvertUtils.randomGen(8);
        String passwordEncode = PasswordUtil.encrypt(sysUser.getUsername(), password, salt);
        sysUser.setSalt(salt);
        sysUser.setPassword(passwordEncode);
        sysUser.setStatus(1);
        sysUser.setPhone(dtUser.getMobile());
        return this.dtUserToSysUser(dtUser, sysUser);
    }

    /**
     * 【同步用户】将【钉钉】的User对象转为SysUser（更新旧用户）
     */
    private SysUser dtUserToSysUser(User dtUser, SysUser oldSysUser) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(oldSysUser, sysUser);
        sysUser.setRealname(dtUser.getName());
        sysUser.setTelephone(dtUser.getTelephone());

        sysUser.setUserType(UserTypeEnum.QyAdmin.getType());
        if(dtUser.getAvatar() != null){
            sysUser.setAvatar(dtUser.getAvatar());
        }
        if (sysUser.getSortOrder() == null){
            sysUser.setSortOrder(100);
        }
        //判断部门
         if(sysUser.getDepartIds() != null){
             sysUser.setDepartIds(dtUser.getDept_id_list());
         }


        // 因为唯一键约束的原因，如果原数据和旧数据相同，就不更新
        if (oConvertUtils.isNotEmpty(dtUser.getEmail()) && !dtUser.getEmail().equals(sysUser.getEmail())) {
            sysUser.setEmail(dtUser.getEmail());
        } else {
            sysUser.setEmail(null);
        }
        // 因为唯一键约束的原因，如果原数据和旧数据相同，就不更新
        if (oConvertUtils.isNotEmpty(dtUser.getMobile()) && !dtUser.getMobile().equals(sysUser.getPhone())) {
            sysUser.setPhone(dtUser.getMobile());
        } else {
            sysUser.setPhone(null);
        }
        // 设置工号，如果工号为空，则使用username
        if (oConvertUtils.isEmpty(dtUser.getJob_number())) {
            sysUser.setWorkNo(dtUser.getUserid());
        } else {
            sysUser.setWorkNo(dtUser.getJob_number());
        }
        // --- 钉钉没有逻辑删除功能
        // sysUser.getDelFlag()
        // --- 钉钉没有冻结、启用禁用功能
        // sysUser.getStatus()
        return sysUser;
    }


    /**
     * 查询用户和部门的关系
     */
    private List<SysDepart> getUserDepart(SysUser sysUser) {
        // 根据用户部门关系表查询出用户的部门
        LambdaQueryWrapper<SysUserDepart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserDepart::getUserId, sysUser.getId());
        List<SysUserDepart> sysUserDepartList = sysUserDepartService.list(queryWrapper);
        if (sysUserDepartList.size() == 0) {
            return null;
        }
        // 根据用户部门
        LambdaQueryWrapper<SysDepart> departQueryWrapper = new LambdaQueryWrapper<>();
        List<String> departIdList = sysUserDepartList.stream().map(SysUserDepart::getDepId).collect(Collectors.toList());
        departQueryWrapper.in(SysDepart::getId, departIdList);
        List<SysDepart> departList = sysDepartService.list(departQueryWrapper);
        return departList.size() == 0 ? null : departList;
    }

    /**
     * 根据sysDepartId查询钉钉的部门
     */
    private Department getDepartmentByDepartId(String departId, List<Department> allDepartment) {
        for (Department department : allDepartment) {
            if (departId.equals(department.getSource_identifier())) {
                return department;
            }
        }
        return null;
    }


    /**
     * 【同步部门】将SysDepartTreeModel转为【钉钉】的Department对象（创建新部门）
     */
    private Department sysDepartToDtDepartment(SysDepartTreeModel departTree, Integer parentId) {
        Department department = new Department();
        department.setSource_identifier(departTree.getId());
        return this.sysDepartToDtDepartment(departTree, department, parentId);
    }

    /**
     * 【同步部门】将SysDepartTreeModel转为【钉钉】的Department对象
     */
    private Department sysDepartToDtDepartment(SysDepartTreeModel departTree, Department department, Integer parentId) {
        department.setName(departTree.getDepartName());
        department.setParent_id(parentId);
        department.setOrder(departTree.getDepartOrder());
        return department;
    }


    /**
     * 【同步部门】将【钉钉】的Department对象转为SysDepartTreeModel
     */
    private SysDepart dtDepartmentToSysDepart(Department department, SysDepart departTree) {
        SysDepart sysDepart = new SysDepart();
        if (departTree != null) {
            BeanUtils.copyProperties(departTree, sysDepart);
        }
        sysDepart.setDingtalkIdentifier(department.getDept_id().toString());
        sysDepart.setDepartName(department.getName());
        sysDepart.setDepartOrder(department.getOrder());
        return sysDepart;
    }

    @Override
    public int removeThirdAppUser(List<String> userIdList,Integer tenantId) {
        // 判断启用状态
//        if (!thirdAppConfig.isDingtalkEnabled()) {
//            return -1;
//        }
        int count = 0;
        if (userIdList != null && userIdList.size() > 0) {
            String accessToken = this.getAccessToken(tenantId);
            if (accessToken == null) {
                return count;
            }
            LambdaQueryWrapper<SysThirdAccount> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SysThirdAccount::getThirdType, THIRD_TYPE);
            queryWrapper.in(SysThirdAccount::getSysUserId, userIdList);
            // 根据userId，获取第三方用户的id
            List<SysThirdAccount> thirdAccountList = sysThirdAccountService.list(queryWrapper);
            List<String> thirdUserIdList = thirdAccountList.stream().map(SysThirdAccount::getThirdUserId).collect(Collectors.toList());

            for (String thirdUserId : thirdUserIdList) {
                if (oConvertUtils.isNotEmpty(thirdUserId)) {
                    // 没有批量删除的接口
                    Response<JSONObject> response = JdtUserAPI.delete(thirdUserId, accessToken);
                    if (response.getErrcode() == 0) {
                        count++;
                    }
                }
            }
        }
        return count;

    }

    @Override
    public boolean sendMessage(MessageDTO message,Integer tenantId) {
        return this.sendMessage(message, false,tenantId);
    }

    /**
     * 发送消息
     *
     * @param message
     * @param verifyConfig
     * @return
     */
    @Override
    public boolean sendMessage(MessageDTO message, boolean verifyConfig,Integer tenantId) {
        Response<String> response;
        if (message.isMarkdown()) {
            response = this.sendMarkdownResponse(message, verifyConfig,tenantId);
        } else {
            response = this.sendMessageResponse(message, verifyConfig,tenantId);
        }
        if (response != null) {
            return response.isSuccess();
        }
        return false;

    }

    /**
     * 发送Markdown消息
     * @param message
     * @param verifyConfig
     * @return
     */
    public Response<String> sendMarkdownResponse(MessageDTO message, boolean verifyConfig,Integer tenantId) {

        String accessToken = this.getAccessToken(tenantId);
        if (accessToken == null) {
            return null;
        }
        // 封装钉钉消息
        ThirdConfig thirdConfig = ThirdConfigFactory.getConfig(tenantId,THIRD_TYPE);
        // 封装钉钉消息
        String title = message.getTitle();
        String content = message.getContent();
        String agentId = thirdConfig.getAgentId();
        Message<MarkdownMessage> mdMessage = new Message<>(agentId, new MarkdownMessage(title, content));
        if (message.getToAll()) {
            mdMessage.setTo_all_user(true);
        } else {
            String[] toUsers = message.getToUser().split(",");
            // 通过第三方账号表查询出第三方userId
            List<SysThirdAccount> thirdAccountList = sysThirdAccountService.listThirdUserIdByUsername(toUsers, THIRD_TYPE,tenantId);
            List<String> dtUserIds = thirdAccountList.stream().map(SysThirdAccount::getThirdUserId).collect(Collectors.toList());
            mdMessage.setUserid_list(dtUserIds);
        }
        return JdtMessageAPI.sendMarkdownMessage(mdMessage, accessToken);
    }

    public Response<String> sendMessageResponse(MessageDTO message, boolean verifyConfig,Integer tenantId) {
//        if (verifyConfig && !thirdAppConfig.isDingtalkEnabled()) {
//            return null;
//        }
        String accessToken = this.getAccessToken(tenantId);
        if (accessToken == null) {
            return null;
        }
        // 封装钉钉消息
        ThirdConfig thirdConfig = ThirdConfigFactory.getConfig(tenantId,THIRD_TYPE);
        String content = message.getContent();
        int agentId = Integer.parseInt(thirdConfig.getAgentId());
        Message<TextMessage> textMessage = new Message<>(agentId, new TextMessage(content));
        if (message.getToAll()) {
            textMessage.setTo_all_user(true);
        } else {
            String[] toUsers = message.getToUser().split(",");
            // 通过第三方账号表查询出第三方userId
            List<SysThirdAccount> thirdAccountList = sysThirdAccountService.listThirdUserIdByUsername(toUsers, THIRD_TYPE);
            List<String> dtUserIds = thirdAccountList.stream().map(SysThirdAccount::getThirdUserId).collect(Collectors.toList());
            textMessage.setUserid_list(dtUserIds);
        }
        return JdtMessageAPI.sendTextMessage(textMessage, accessToken);
    }

    public boolean recallMessage(String msgTaskId,Integer tenantId) {
        Response<JSONObject> response = this.recallMessageResponse(msgTaskId, tenantId);
        if (response == null) {
            return false;
        }
        return response.isSuccess();
    }

    /**
     * 撤回消息
     *
     * @param msgTaskId
     * @return
     */
    public Response<JSONObject> recallMessageResponse(String msgTaskId,Integer tenantId) {
        String accessToken = this.getAccessToken(tenantId);
        if (accessToken == null) {
            return null;
        }
        ThirdConfig thirdConfig = ThirdConfigFactory.getConfig(tenantId,THIRD_TYPE);
        int agentId = Integer.parseInt(thirdConfig.getAgentId());
        return JdtMessageAPI.recallMessage(thirdConfig.getAgentId(), msgTaskId, getAccessToken(tenantId));
    }

    /**
     * 发送卡片消息（SysAnnouncement定制）
     *
     * @param announcement
     * @param verifyConfig 是否验证配置（未启用的APP会拒绝发送）
     * @return
     */
    public Response<String> sendActionCardMessage(SysAnnouncement announcement, boolean verifyConfig,Integer tenantId) {
        ThirdConfig thirdConfig = ThirdConfigFactory.getConfig(tenantId,THIRD_TYPE);

        if (verifyConfig && !thirdConfig.getIsEnabled()) {
            return null;
        }
        String accessToken = this.getAccessToken(tenantId);
        if (accessToken == null) {
            return null;
        }
        int agentId = Integer.parseInt(thirdConfig.getAgentId());
        String markdown = "### " + announcement.getTitile() + "\n" + oConvertUtils.getString(announcement.getMsgAbstract(),"空");
        ActionCardMessage actionCard = new ActionCardMessage(markdown);
        actionCard.setTitle(announcement.getTitile());
        actionCard.setSingle_title("详情");
        actionCard.setSingle_url(RestUtil.getBaseUrl() + "/sys/annountCement/show/" + announcement.getId());
        Message<ActionCardMessage> actionCardMessage = new Message<>(agentId, actionCard);
        if (CommonConstant.MSG_TYPE_ALL.equals(announcement.getMsgType())) {
            actionCardMessage.setTo_all_user(true);
            return JdtMessageAPI.sendActionCardMessage(actionCardMessage, accessToken);
        } else {
            // 将userId转为username
            String[] userIds = null;
            String userId = announcement.getUserIds();
            if(oConvertUtils.isNotEmpty(userId)){
                userIds = userId.substring(0, (userId.length() - 1)).split(",");
            }else{
                LambdaQueryWrapper<SysAnnouncementSend> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(SysAnnouncementSend::getAnntId, announcement.getId());
                SysAnnouncementSend sysAnnouncementSend = sysAnnouncementSendMapper.selectOne(queryWrapper);
                userIds = new String[] {sysAnnouncementSend.getUserId()};
            }

            if(userIds!=null){
                LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.in(SysUser::getId, userIds);
                List<SysUser> userList = userMapper.selectList(queryWrapper);
                String[] usernameList = userList.stream().map(SysUser::getUsername).toArray(String[] :: new);

                // 通过第三方账号表查询出第三方userId
                List<SysThirdAccount> thirdAccountList = sysThirdAccountService.listThirdUserIdByUsername(usernameList, THIRD_TYPE);
                List<String> dtUserIds = thirdAccountList.stream().map(SysThirdAccount::getThirdUserId).collect(Collectors.toList());
                actionCardMessage.setUserid_list(dtUserIds);
                return JdtMessageAPI.sendActionCardMessage(actionCardMessage, accessToken);
            }
        }
        return null;
    }

    /**
     * 发送卡片消息（SysAnnouncement定制）
     *
     * @param announcement
     * @param ddMobileUrl 钉钉打开网页地址
     * @param verifyConfig 是否验证配置（未启用的APP会拒绝发送）
     * @return
     */
    public Response<String> sendActionCardMessage(SysAnnouncement announcement, String ddMobileUrl, boolean verifyConfig,Integer tenantId) {
        ThirdConfig thirdConfig = ThirdConfigFactory.getConfig(tenantId,THIRD_TYPE);

        if (verifyConfig && !thirdConfig.getIsEnabled()) {
            return null;
        }
        String accessToken = this.getAccessToken(tenantId);
        if (accessToken == null) {
            return null;
        }
        int agentId = Integer.parseInt(thirdConfig.getAgentId());
        //update-end---author:wangshuai ---date:20230224  for：[QQYUN-3440]新建企业微信和钉钉配置表，通过租户模式隔离------------
        String emptySuffix = null;
        if (oConvertUtils.isNotEmpty(announcement.getMsgAbstract())) {
            String msgAbstract = announcement.getMsgAbstract().trim();
            log.info("获取钉钉通知参数，msgAbstract: {}", msgAbstract);
            if (msgAbstract.startsWith("{") && msgAbstract.endsWith("}")) {
                //如果摘要存的是业务扩展参数json，则取公告内容
                emptySuffix = announcement.getMsgContent();
            } else {
                //如果摘要不为空且是文本格式，则使用摘要
                emptySuffix = msgAbstract;
            }
        } else {
            emptySuffix = "空";
        }

        String markdown = "### " + announcement.getTitile() + "\n" + emptySuffix;
        log.info("钉钉推送参数, markdown: {}", markdown);
        ActionCardMessage actionCard = new ActionCardMessage(markdown);
        actionCard.setTitle(announcement.getTitile());
        actionCard.setSingle_title("详情");
        String baseUrl = null;
        //优先通过请求获取basepath，获取不到读取 jeecg.domainUrl.pc
        try {
            baseUrl = RestUtil.getBaseUrl();
        } catch (Exception e) {
            log.warn(e.getMessage());
//            baseUrl =  JeecgBaseConfig.getDomainUrl().getPc();
            //e.printStackTrace();
        }

        log.info("获取钉钉打开网页地址，参数 ddMobileUrl: {}", ddMobileUrl);
        String ddSingleUrl = null;
        if (oConvertUtils.isNotEmpty(ddMobileUrl)) {
            ddSingleUrl = ddMobileUrl;
        } else {
            ddSingleUrl = baseUrl + "/sys/annountCement/show/" + announcement.getId();
        }
        actionCard.setSingle_url(ddSingleUrl);
        log.info("获取钉钉打开网页地址，最终地址 ddSingleUrl: {}", ddSingleUrl);

        Message<ActionCardMessage> actionCardMessage = new Message<>(agentId, actionCard);
        if (CommonConstant.MSG_TYPE_ALL.equals(announcement.getMsgType())) {
            actionCardMessage.setTo_all_user(true);
            return JdtMessageAPI.sendActionCardMessage(actionCardMessage, accessToken);
        } else {
            // 将userId转为username
            String[] userIds = null;
            String userId = announcement.getUserIds();
            if(oConvertUtils.isNotEmpty(userId)){
                userIds = userId.substring(0, (userId.length() - 1)).split(",");
            }else{
                LambdaQueryWrapper<SysAnnouncementSend> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(SysAnnouncementSend::getAnntId, announcement.getId());
                SysAnnouncementSend sysAnnouncementSend = sysAnnouncementSendMapper.selectOne(queryWrapper);
                userIds = new String[] {sysAnnouncementSend.getUserId()};
            }

            if(userIds!=null){
                LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.in(SysUser::getId, userIds);
                List<SysUser> userList = userMapper.selectList(queryWrapper);
                String[] usernameList = userList.stream().map(SysUser::getUsername).toArray(String[] :: new);

                // 通过第三方账号表查询出第三方userId
//                int tenantId = oConvertUtils.getInt(TenantContext.getTenant(), CommonConstant.TENANT_ID_DEFAULT_VALUE);
                List<SysThirdAccount> thirdAccountList = sysThirdAccountService.listThirdUserIdByUsername(usernameList, THIRD_TYPE, tenantId);
                List<String> dtUserIds = thirdAccountList.stream().map(SysThirdAccount::getThirdUserId).collect(Collectors.toList());
                actionCardMessage.setUserid_list(dtUserIds);
                return JdtMessageAPI.sendActionCardMessage(actionCardMessage, accessToken);
            }
        }
        return null;
    }

    /**
     * OAuth2登录，成功返回登录的SysUser，失败返回null
     */
    public SysUser oauth2Login(String authCode,Integer tenantId) {
        ThirdConfig thirdConfig = ThirdConfigFactory.getConfig(tenantId,THIRD_TYPE);

//        ThirdAppTypeItemVo dtConfig = new ThirdAppTypeItemVo();
//        dtConfig.setAgentId(thirdConfig.getAgentId());
//        dtConfig.setClientId(thirdConfig.getClientId());
//        dtConfig.setClientSecret(thirdConfig.getClientSecret());
//        dtConfig.setEnabled(true);
        // 1. 根据免登授权码获取用户 AccessToken
        String userAccessToken = JdtOauth2API.getUserAccessToken(thirdConfig.getClientId(), thirdConfig.getClientSecret(), authCode);
        if (userAccessToken == null) {
            log.error("oauth2Login userAccessToken is null");
            throw new JeecgBootException("请查看应用key和应用秘钥是否正确，组织ID是否匹配");
        }
        // 2. 根据用户 AccessToken 获取当前用户的基本信息（不包括userId）
        ContactUser contactUser = JdtOauth2API.getContactUsers("me", userAccessToken);
        if (contactUser == null) {
            log.error("oauth2Login contactUser is null");
            throw new JeecgBootException("获取钉钉用户信息失败");
        }
        String unionId = contactUser.getUnionId();
        // 3. 根据获取到的 unionId 换取用户 userId
        String accessToken = this.getAccessToken(tenantId);
        if (accessToken == null) {
            log.error("oauth2Login accessToken is null");
            throw new JeecgBootException("请查看应用key和应用秘钥是否正确，组织ID是否匹配");
        }
        Response<String> getUserIdRes = JdtUserAPI.getUseridByUnionid(unionId, accessToken);
        if (!getUserIdRes.isSuccess()) {
            log.error("oauth2Login getUseridByUnionid failed: " + JSON.toJSONString(getUserIdRes));
            throw new JeecgBootException("获取钉钉用户信息失败");
        }
        String appUserId = getUserIdRes.getResult();
        log.info("appUserId: " + appUserId);
        if (appUserId != null) {
            // 判断第三方用户表有没有这个人
            LambdaQueryWrapper<SysThirdAccount> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SysThirdAccount::getThirdUserUuid, appUserId);
            queryWrapper.or().eq(SysThirdAccount::getThirdUserId, appUserId);
            queryWrapper.eq(SysThirdAccount::getThirdType, THIRD_TYPE);
            SysThirdAccount thirdAccount = sysThirdAccountService.getOne(queryWrapper);
            if (thirdAccount != null) {
                return this.getSysUserByThird(thirdAccount, null, appUserId, accessToken);
            } else {
                // 直接创建新账号
//                User appUser = JdtUserAPI.getUserById(appUserId, accessToken).getResult();
//                ThirdLoginModel tlm = new ThirdLoginModel(THIRD_TYPE, appUser.getUserid(), appUser.getName(), appUser.getAvatar());
//                thirdAccount = sysThirdAccountService.saveThirdUser(tlm);
//                return this.getSysUserByThird(thirdAccount, appUser, null, null);
            }
        }
        return null;
    }

    /**
     * 根据第三方账号获取本地账号，如果不存在就创建
     *
     * @param thirdAccount
     * @param appUser
     * @param appUserId
     * @param accessToken
     * @return
     */
    private SysUser getSysUserByThird(SysThirdAccount thirdAccount, User appUser, String appUserId, String accessToken) {
        String sysUserId = thirdAccount.getSysUserId();
        if (oConvertUtils.isNotEmpty(sysUserId)) {
            return userMapper.selectById(sysUserId);
        } else {
            // 如果没有 sysUserId ，说明没有绑定账号，获取到手机号之后进行绑定
//            if (appUser == null) {
//                appUser = JdtUserAPI.getUserById(appUserId, accessToken).getResult();
//            }
//            // 判断系统里是否有这个手机号的用户
//            SysUser sysUser = userMapper.getUserByPhone(appUser.getMobile());
//            if (sysUser != null) {
//                thirdAccount.setAvatar(appUser.getAvatar());
//                thirdAccount.setRealname(appUser.getName());
//                thirdAccount.setThirdUserId(appUser.getUserid());
//                thirdAccount.setThirdUserUuid(appUser.getUserid());
//                thirdAccount.setSysUserId(sysUser.getId());
//                sysThirdAccountService.updateById(thirdAccount);
//                return sysUser;
//            } else {
//                // 没有就走创建逻辑
////                return sysThirdAccountService.createUser(appUser.getMobile(), appUser.getUserid());
//            }
            return null;

        }
    }



    //========================begin 应用低代码钉钉同步用户部门专用 ====================

//    /**
//     * 添加或保存用户租户
//     * @param userId
//     * @param isUpdate 是否是新增
//     */
//    private void createUserTenant(String userId,Boolean isUpdate){
//        if(MybatisPlusSaasConfig.OPEN_SYSTEM_TENANT_CONTROL){
//            int tenantId = oConvertUtils.getInt(TenantContext.getTenant(), 0);
//            //判断当前用户是否已在该租户下面
//            Integer count = userTenantMapper.userTenantIzExist(userId, tenantId);
//            //count 为0 新增租户用户,否则不用新增
//            if(count == 0){
//                SysUserTenant userTenant = new SysUserTenant();
//                userTenant.setTenantId(tenantId);
//                userTenant.setUserId(userId);
//                userTenant.setStatus(isUpdate?CommonConstant.USER_TENANT_UNDER_REVIEW:CommonConstant.USER_TENANT_NORMAL);
//                userTenantMapper.insert(userTenant);
//            }
//        }
//    }
//
//    /**
//     * 同步用户和部门
//     * @return
//     */
//    public org.jeecg.modules.system.vo.thirdapp.SyncInfoVo syncThirdAppDepartmentUserToLocal() {
//        org.jeecg.modules.system.vo.thirdapp.SyncInfoVo syncInfo = new org.jeecg.modules.system.vo.thirdapp.SyncInfoVo();
//        String accessToken = this.getAccessToken();
//        if (accessToken == null) {
//            syncInfo.addFailInfo("accessToken获取失败！");
//            return syncInfo;
//        }
//        // 获取【钉钉】所有的部门
//        List<Department> departments = JdtDepartmentAPI.listAll(accessToken);
//        String username = JwtUtil.getUserNameByToken(SpringContextUtils.getHttpServletRequest());
//        List<org.jeecg.modules.system.vo.thirdapp.JdtDepartmentTreeVo> departmentTreeList = org.jeecg.modules.system.vo.thirdapp.JdtDepartmentTreeVo.listToTree(departments);
//        int tenantId = oConvertUtils.getInt(TenantContext.getTenant(), 0);
//        // 递归同步部门
//        this.syncDepartmentToLocalRecursion(departmentTreeList, null, username, syncInfo, accessToken,true,tenantId);
//        return syncInfo;
//    }
//
//
//    /**
//     * 添加用户及用户部门关系
//     * @param departId 部门id
//     * @param dingDepartId 钉钉部门id
//     * @param accessToken
//     * @param syncInfo
//     * @param syncedUserIdSet
//     */
//    private void addDepartUser(String departId, Integer dingDepartId, String accessToken, org.jeecg.modules.system.vo.thirdapp.SyncInfoVo syncInfo, Set<String> syncedUserIdSet, Integer tenantId) {
//        List<User> userList = new ArrayList<>();
//        getUserListByDeptIdRecursion(dingDepartId, 0, userList, accessToken);
//        for (User user : userList) {
//            if (syncedUserIdSet.contains(user.getUserid())) {
//                //需要同步用户部门
//                this.syncAddOrUpdateUserDepart(user.getUserid(),departId);
//                continue;
//            }
//            syncedUserIdSet.add(user.getUserid());
//            SysUser userByPhone = userMapper.getUserByPhone(user.getMobile());
//            SysThirdAccount sysThirdAccount = sysThirdAccountService.getOneByUuidAndThirdType(user.getUnionid(), THIRD_TYPE,tenantId);
//            if (null != userByPhone) {
//                // 循环到此说明用户匹配成功，进行更新操作
//                SysUser updateSysUser = this.dtUserToSysUser(user, userByPhone);
//                try {
//                    userMapper.updateById(updateSysUser);
//                    String str = String.format("用户 %s(%s) 更新成功！", updateSysUser.getRealname(), updateSysUser.getUsername());
//                    syncInfo.addSuccessInfo(str);
//                } catch (Exception e) {
//                    this.syncUserCollectErrInfo(e, user, syncInfo);
//                }
//                //第三方账号关系表
//                this.thirdAccountSaveOrUpdate(sysThirdAccount, updateSysUser.getId(), user, tenantId);
//                //创建当前租户
//                this.createUserTenant(updateSysUser.getId(),true);
//                //需要同步用户部门
//                this.syncAddOrUpdateUserDepart(updateSysUser.getId(),departId);
//            } else {
//                // 如果没有匹配到用户，则走创建逻辑
//                SysUser newSysUser = this.dtUserToSysUser(user);
//                try {
//                    userMapper.insert(newSysUser);
//                    String str = String.format("用户 %s(%s) 创建成功！", newSysUser.getRealname(), newSysUser.getUsername());
//                    syncInfo.addSuccessInfo(str);
//                } catch (Exception e) {
//                    this.syncUserCollectErrInfo(e, user, syncInfo);
//                }
//                //第三方账号关系表
//                this.thirdAccountSaveOrUpdate(sysThirdAccount, newSysUser.getId(), user,tenantId);
//                //创建当前租户
//                this.createUserTenant(newSysUser.getId(),false);
//                //需要同步用户部门
//                this.syncAddOrUpdateUserDepart(newSysUser.getId(),departId);
//            }
//        }
//    }
//
//    /**
//     * 通过用户id和部门id新增用户部门关系表
//     * @param userId
//     * @param departId
//     */
//    private void syncAddOrUpdateUserDepart(String userId, String departId) {
//        //查询用户是否在部门里面
//        LambdaQueryWrapper<SysUserDepart> query = new LambdaQueryWrapper<>();
//        query.eq(SysUserDepart::getDepId,departId);
//        query.eq(SysUserDepart::getUserId,userId);
//        long count = sysUserDepartService.count(query);
//        if(count == 0){
//            //不存在，则新增部门用户关系
//            SysUserDepart sysUserDepart = new SysUserDepart(null,userId,departId);
//            sysUserDepartService.save(sysUserDepart);
//        }
//    }

    //========================end 应用低代码钉钉同步用户部门专用 ====================


}
