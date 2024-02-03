package com.qfy.modules.org.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qfy.modules.commons.model.TreeSelectModel;
import com.qfy.modules.org.entity.SysOrg;
import com.qfy.modules.org.mapper.SysOrgMapper;
import com.qfy.modules.org.service.ISysOrgService;
import org.jeecg.common.constant.FillRuleConstant;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.util.FillRuleUtil;
import org.jeecg.common.util.oConvertUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: qfy_sys_organization
 * @Author: jeecg-boot
 * @Date:   2022-06-01
 * @Version: V1.0
 */
@Service
public class SysOrgServiceImpl extends ServiceImpl<SysOrgMapper, SysOrg> implements ISysOrgService {


    @Override
    public void addSysOrg(SysOrg sysOrg) {
        String categoryCode = "";
        String categoryPid = ISysOrgService.ROOT_PID_VALUE;
        String parentCode = null;

        sysOrg.setPcode("0");
        if(oConvertUtils.isNotEmpty(sysOrg.getPid())){
            categoryPid = sysOrg.getPid();

            //PID 不是根节点 说明需要设置父节点 hasChild 为1
            if(!ISysOrgService.ROOT_PID_VALUE.equals(categoryPid)){
                SysOrg parent = baseMapper.selectById(categoryPid);
                parentCode = parent.getCode();
                if(parent!=null && !"1".equals(parent.getHasChild())){
                    parent.setHasChild("1");
                    baseMapper.updateById(parent);
                }
                sysOrg.setPcode(parentCode);
            }
        }
        JSONObject formData = new JSONObject();
        formData.put("pid",categoryPid);
        categoryCode = (String) FillRuleUtil.executeRule(FillRuleConstant.FAMILY_ORG,formData);
        sysOrg.setCode(categoryCode);
        sysOrg.setPid(categoryPid);
        baseMapper.insert(sysOrg);
    }

    @Override
    public void updateSysOrg(SysOrg sysOrg) {
        if(oConvertUtils.isEmpty(sysOrg.getPid())){
            sysOrg.setPid(ISysOrgService.ROOT_PID_VALUE);
            sysOrg.setPcode("0");
        }else{
            //如果当前节点父ID不为空 则设置父节点的hasChild 为1
            SysOrg parent = baseMapper.selectById(sysOrg.getPid());
            sysOrg.setPcode(parent.getCode());
            if(parent!=null && !"1".equals(parent.getHasChild())){
                parent.setHasChild("1");
                baseMapper.updateById(parent);
            }
        }
        baseMapper.updateById(sysOrg);
    }

    @Override
    public List<TreeSelectModel> queryListByCode(String pcode) throws JeecgBootException {
        String pid = ROOT_PID_VALUE;
        if(oConvertUtils.isNotEmpty(pcode)) {
            List<SysOrg> list = baseMapper.selectList(new LambdaQueryWrapper<SysOrg>().eq(SysOrg::getCode, pcode));
            if(list==null || list.size() ==0) {
                throw new JeecgBootException("该编码【"+pcode+"】不存在，请核实!");
            }
            if(list.size()>1) {
                throw new JeecgBootException("该编码【"+pcode+"】存在多个，请核实!");
            }
            pid = list.get(0).getId();
        }
        return baseMapper.queryListByPid(pid,null);
    }

    @Override
    public List<TreeSelectModel> queryListByPid(String pid) {
        if(oConvertUtils.isEmpty(pid)) {
            pid = ROOT_PID_VALUE;
        }
        return baseMapper.queryListByPid(pid,null);
    }

    @Override
    public List<TreeSelectModel> queryListByPid(String pid, Map<String, String> condition) {
        if(oConvertUtils.isEmpty(pid)) {
            pid = ROOT_PID_VALUE;
        }
        return baseMapper.queryListByPid(pid,condition);
    }

    @Override
    public SysOrg queryByCode(String code) {
        return baseMapper.queryByCode(code);
    }

    @Override
    public String queryIdByCode(String code) {
        return baseMapper.queryIdByCode(code);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSysOrg(String ids) {
        String allIds = this.queryTreeChildIds(ids);
        String pids = this.queryTreePids(ids);
        //1.删除时将节点下所有子节点一并删除
        this.baseMapper.deleteBatchIds(Arrays.asList(allIds.split(",")));
        //2.将父节点中已经没有下级的节点，修改为没有子节点
        if(oConvertUtils.isNotEmpty(pids)){
            LambdaUpdateWrapper<SysOrg> updateWrapper = new UpdateWrapper<SysOrg>()
                    .lambda()
                    .in(SysOrg::getId,Arrays.asList(pids.split(",")))
                    .set(SysOrg::getHasChild,"0");
            this.update(updateWrapper);
        }
    }

    /**
     * 查询节点下所有子节点
     * @param ids
     * @return
     */
    private String queryTreeChildIds(String ids) {
        //获取id数组
        String[] idArr = ids.split(",");
        StringBuffer sb = new StringBuffer();
        for (String pidVal : idArr) {
            if(pidVal != null){
                if(!sb.toString().contains(pidVal)){
                    if(sb.toString().length() > 0){
                        sb.append(",");
                    }
                    sb.append(pidVal);
                    this.getTreeChildIds(pidVal,sb);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 查询需修改标识的父节点ids
     * @param ids
     * @return
     */
    private String queryTreePids(String ids) {
        StringBuffer sb = new StringBuffer();
        //获取id数组
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            if(id != null){
                SysOrg category = this.baseMapper.selectById(id);
                //根据id查询pid值
                String metaPid = category.getPid();
                //查询此节点上一级是否还有其他子节点
                LambdaQueryWrapper<SysOrg> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(SysOrg::getPid,metaPid);
                queryWrapper.notIn(SysOrg::getId,Arrays.asList(idArr));
                List<SysOrg> dataList = this.baseMapper.selectList(queryWrapper);
                if((dataList == null || dataList.size()==0) && !Arrays.asList(idArr).contains(metaPid)
                        && !sb.toString().contains(metaPid)){
                    //如果当前节点原本有子节点 现在木有了，更新状态
                    sb.append(metaPid).append(",");
                }
            }
        }
        if(sb.toString().endsWith(",")){
            sb = sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 递归 根据父id获取子节点id
     * @param pidVal
     * @param sb
     * @return
     */
    private StringBuffer getTreeChildIds(String pidVal,StringBuffer sb){
        LambdaQueryWrapper<SysOrg> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysOrg::getPid,pidVal);
        List<SysOrg> dataList = baseMapper.selectList(queryWrapper);
        if(dataList != null && dataList.size()>0){
            for(SysOrg category : dataList) {
                if(!sb.toString().contains(category.getId())){
                    sb.append(",").append(category.getId());
                }
                this.getTreeChildIds(category.getId(), sb);
            }
        }
        return sb;
    }

    @Override
    public List<String> loadDictItem(String ids) {
        return this.loadDictItem(ids, true);
    }

    @Override
    public List<String> loadDictItem(String ids, boolean delNotExist) {
        String[] idArray = ids.split(",");
        LambdaQueryWrapper<SysOrg> query = new LambdaQueryWrapper<>();
        query.in(SysOrg::getId, Arrays.asList(idArray));
        // 查询数据
        List<SysOrg> list = super.list(query);
        // 取出name并返回
        List<String> textList;
        // update-begin--author:sunjianlei--date:20210514--for：新增delNotExist参数，设为false不删除数据库里不存在的key ----
        if (delNotExist) {
            textList = list.stream().map(SysOrg::getName).collect(Collectors.toList());
        } else {
            textList = new ArrayList<>();
            for (String id : idArray) {
                List<SysOrg> res = list.stream().filter(i -> id.equals(i.getId())).collect(Collectors.toList());
                textList.add(res.size() > 0 ? res.get(0).getName() : id);
            }
        }
        // update-end--author:sunjianlei--date:20210514--for：新增delNotExist参数，设为false不删除数据库里不存在的key ----
        return textList;
    }
}
