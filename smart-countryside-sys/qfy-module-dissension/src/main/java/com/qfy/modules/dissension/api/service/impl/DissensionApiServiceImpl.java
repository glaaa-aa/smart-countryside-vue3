package com.qfy.modules.dissension.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qfy.modules.commons.entity.SystemUser;
import com.qfy.modules.commons.mapper.SystemUserMapper;
import com.qfy.modules.dissension.api.service.IDissensionApiService;
import com.qfy.modules.dissension.api.vo.DissensionDetailsResult;
import com.qfy.modules.dissension.entity.*;
import com.qfy.modules.dissension.mapper.*;
import com.qfy.modules.hr.info.entity.HrPerson;
import com.qfy.modules.hr.info.mapper.HrPersonMapper;
import com.qfy.modules.org.entity.SysOrg;
import com.qfy.modules.org.mapper.SysOrgMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.FillRuleUtil;
import org.jeecg.common.util.PasswordUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.mapper.SysUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * @Description: 矛盾纠纷
 * @Author: jeecg-boot
 * @Date:   2023-12-26
 * @Version: V1.0
 */
@Service
public class DissensionApiServiceImpl extends ServiceImpl<DissensionMapper, Dissension> implements IDissensionApiService {

	@Autowired
	private DissensionMapper dissensionMapper;
	@Autowired
	private DissensionInvolvedUserMapper dissensionInvolvedUserMapper;
	@Autowired
	private AndonRepairMoManMapper andonRepairMoManMapper;
	@Autowired
	private HrPersonMapper hrPersonMapper;
	@Autowired
	private SystemUserMapper userMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysOrgMapper orgMapper;
	@Autowired
	private AndonRepairMoMapper repairMoMapper;
	@Autowired
	private AndonRepairMoLogMapper moLogMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(Dissension dissension, List<DissensionInvolvedUser> dissensionInvolvedUserList) {
		SysOrg sysOrg = orgMapper.queryByCode(dissension.getOrgCode());
		if (ObjectUtils.isNotEmpty(sysOrg)) {
			dissension.setOrgName(sysOrg.getName());
			dissension.setOrgFullName(sysOrg.getDescription());
		}

		String code = FillRuleUtil.executeRule("dissension_code_rule", null).toString();
		dissension.setCode(code);
		String realnameArry = ""; //涉及人员
		String realname = "";
		if (dissensionInvolvedUserList != null && dissensionInvolvedUserList.size() > 0) {
			for (DissensionInvolvedUser entity : dissensionInvolvedUserList) {

				//外键设置
				realname += entity.getRealname() + ",";
				entity.setTensionCode(dissension.getCode());
				dissensionInvolvedUserMapper.insert(entity);
			}
		}

		if (StringUtils.isNotEmpty(realname)) {
			realnameArry = realname.substring(0, realname.lastIndexOf(",")); //去掉涉及人员最后一个，
		}

		dissension.setState("1");
		dissension.setPersons(realnameArry);
		dissension.setNumbers(dissensionInvolvedUserList.size());

		AndonRepairMoLog moLog = new AndonRepairMoLog();
		moLog.setS2(dissension.getDataFiles());
		moLog.setMoCode(dissension.getCode());
		moLog.setOptContent(dissension.getContent());
		moLog.setHandleDate(dissension.getAcceptDate());
//		moLog.setOptId(dissension.getRespPersons());
		moLog.setS1("已提交调解信息");
		moLogMapper.insert(moLog);
		dissensionMapper.insert(dissension);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(Dissension dissension,List<DissensionInvolvedUser> dissensionInvolvedUserList,List<AndonRepairMoMan> andonRepairMoManList) {
		SysOrg sysOrg = orgMapper.queryByCode(dissension.getOrgCode());
		if(ObjectUtils.isNotEmpty(sysOrg)){
			dissension.setOrgName(sysOrg.getName());
			dissension.setOrgFullName(sysOrg.getDescription());
		}

		//1.先删除子表数据
		dissensionInvolvedUserMapper.deleteByMainId(dissension.getCode());
		andonRepairMoManMapper.deleteByMainId(dissension.getCode());

		String realnameArry=""; //涉及人员 及 负责人员
		String realname="";
		//2.子表数据重新插入
		if(dissensionInvolvedUserList!=null && dissensionInvolvedUserList.size()>0) {
			for(DissensionInvolvedUser entity:dissensionInvolvedUserList) {
				//外键设置
				realname+=entity.getRealname()+",";
				entity.setTensionCode(dissension.getCode());
				dissensionInvolvedUserMapper.insert(entity);

				QueryWrapper<HrPerson> queryWrapper=new QueryWrapper<>();
				queryWrapper.eq("phone",entity.getPhone());
				HrPerson hrPerson2 = hrPersonMapper.selectOne(queryWrapper);
				if(ObjectUtils.isEmpty(hrPerson2)){
					HrPerson person=new HrPerson();
					person.setCname(entity.getRealname());
					person.setCardNo(entity.getPhone());
					person.setPhone(entity.getPhone());
					person.setPersonNo(entity.getPhone());
					person.setSex(entity.getSex());
					person.setIdcard(entity.getIdCard());
					hrPersonMapper.insert(person);
				}
			}
		}

		if(StringUtils.isNotEmpty(realname)){
			realnameArry= realname.substring(0, realname.lastIndexOf(",")); //去掉涉及人员最后一个，
			dissension.setPersons(realnameArry);
		}
		dissension.setNumbers(dissensionInvolvedUserList.size());
		dissensionMapper.updateById(dissension);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		Dissension dissension = dissensionMapper.selectById(id);
		dissensionInvolvedUserMapper.deleteByMainId(dissension.getCode());
		andonRepairMoManMapper.deleteByMainId(dissension.getCode());
		dissensionMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			dissensionInvolvedUserMapper.deleteByMainId(id.toString());
			andonRepairMoManMapper.deleteByMainId(id.toString());
			dissensionMapper.deleteById(id);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void feedback(Dissension dissension, List<AndonRepairMoMan> andonRepairMoManList) throws Exception {
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		SysOrg sysOrg = orgMapper.queryByCode(dissension.getOrgCode());
		if (ObjectUtils.isNotEmpty(sysOrg)) {
			dissension.setOrgName(sysOrg.getName());
			dissension.setOrgFullName(sysOrg.getDescription());
		}

		String personNo = "";
		HrPerson byPersonNo = hrPersonMapper.selectByPersonNo(dissension.getRespPersons());
		AndonRepairMoMan responsibleUser = new AndonRepairMoMan();
		if (ObjectUtils.isNotEmpty(byPersonNo)) {
			responsibleUser.setMoCode(dissension.getCode());
			responsibleUser.setPhone(byPersonNo.getPhone());
			responsibleUser.setDutyMan(byPersonNo.getCname());
			responsibleUser.setRemarks("主要负责人");
			personNo = byPersonNo.getPersonNo();
			responsibleUser.setPrinciPal(1);
			andonRepairMoManList.add(0,responsibleUser);
		}

		AndonRepairMoMan andonRepairMoMan = andonRepairMoManMapper.selectByPrincipal(dissension.getCode());

		String realnameArry = ""; //负责人员
		String realname = "";
		AndonRepairMo andonRepairMo = new AndonRepairMo();
		if (andonRepairMoManList != null && andonRepairMoManList.size() > 0) {
			for (AndonRepairMoMan entity : andonRepairMoManList) {
				SystemUser byUser = null;
				HrPerson hrPerson2 = null;
				if (personNo != "") {
					byUser = userMapper.getUserByName(personNo);
					QueryWrapper<HrPerson> queryWrapper = new QueryWrapper<>();
					queryWrapper.eq("person_no", personNo);
					hrPerson2 = hrPersonMapper.selectOne(queryWrapper);
					personNo = "";
				} else {
					QueryWrapper<HrPerson> queryWrapper = new QueryWrapper<>();
					queryWrapper.eq("phone", entity.getPhone());
					hrPerson2 = hrPersonMapper.selectOne(queryWrapper);
					if (ObjectUtils.isNotEmpty(hrPerson2)) {
						byUser = userMapper.getUserByName(hrPerson2.getPersonNo());
					} else {
						byUser = userMapper.getUserByName(entity.getPhone());
					}

				}

				if (ObjectUtils.isEmpty(byUser)) {
					byUser = new SystemUser();
					byUser.setUsername(entity.getPhone());
					byUser.setRealname(entity.getDutyMan());
					byUser.setPhone(entity.getPhone());
					String salt = oConvertUtils.randomGen(8);
					byUser.setSalt(salt);
					String passwordEncode = PasswordUtil.encrypt(byUser.getUsername(), "123456", salt);
					byUser.setPassword(passwordEncode);
					byUser.setDelFlag(CommonConstant.DEL_FLAG_0);
					byUser.setActivitiSync(CommonConstant.ACT_SYNC_1);
					byUser.setStatus(1);
					byUser.setUserIdentity(60);
					SysUser sysUser = new SysUser();
					sysUser.setUserType(1);
					sysUser.setSortOrder(100);
					BeanUtils.copyProperties(byUser, sysUser);
					sysUserMapper.insert(sysUser);
				}

				if (ObjectUtils.isEmpty(hrPerson2)) {
					HrPerson person = new HrPerson();
					person.setCname(entity.getDutyMan());
					person.setCardNo(entity.getPhone());
					person.setPhone(entity.getPhone());
					person.setPersonNo(entity.getPhone());
					hrPersonMapper.insert(person);
				}
				//外键设置
				if (StringUtils.isEmpty(entity.getId())) {
					entity.setDutyManId(byUser.getUsername());
					entity.setMoCode(dissension.getCode());
					andonRepairMoManMapper.insert(entity);
				}

				realname += entity.getDutyMan() + ",";

				if (entity.getPrinciPal()!= null) {
					andonRepairMo.setMoCode(dissension.getCode());
					andonRepairMo.setNotifier(entity.getDutyManId());
					andonRepairMo.setNotifierName(entity.getDutyMan());
					andonRepairMo.setNotifierPhone(entity.getPhone());
					andonRepairMo.setDutyMan(loginUser.getRealname());
					andonRepairMo.setDutyManId(loginUser.getUsername());
					andonRepairMo.setShenpTime(dissension.getAcceptDate());
					andonRepairMo.setWeixType(dissension.getProtocolState());
					andonRepairMo.setWeixState(dissension.getState());
					andonRepairMo.setCriticalLevel(dissension.getEmergency());
					andonRepairMo.setShenpResult(dissension.getResults());
					andonRepairMo.setRemarks(dissension.getRemarks());
					andonRepairMo.setAbnFiles(dissension.getAttachments());
				}
			}
		}

		//去重
		if (StringUtils.isNotEmpty(realname)) {
			List<String> names = Arrays.asList(realname.split(","));
			Set<String> uniqueNames = new HashSet<>(names);
			List<String> deduplicated = new ArrayList<>(uniqueNames);
			for (String s : deduplicated) {
				realnameArry += s+" ";
			}
			dissension.setS2(realnameArry);
		}
		//根据矛盾纠纷编号查询该纠纷的负责人 并防止重复添加负责人
		if (ObjectUtils.isNotEmpty(andonRepairMoMan)) andonRepairMoManMapper.deleteById(andonRepairMoMan.getId());
		andonRepairMo.setS1(dissension.getS2());
		Long count = repairMoMapper.selectCount(new QueryWrapper<AndonRepairMo>().eq("mo_code", dissension.getCode()));
		if(count==0){
			andonRepairMo.setS2("第1次调节信息");
		}else {
			andonRepairMo.setS2("第"+ (count+1) +"次调节信息");

		}
		//新增调节信息
		repairMoMapper.insert(andonRepairMo);

		HrPerson hrPerson = hrPersonMapper.selectOne(new QueryWrapper<HrPerson>().eq("person_no", dissension.getRespPersons()));

		dissension.setS1(hrPerson.getCname());

		AndonRepairMoLog moLog = new AndonRepairMoLog();
		moLog.setMoCode(dissension.getCode());
		moLog.setOptContent(dissension.getContent());
		moLog.setOptId(hrPerson.getId());
		moLog.setOptName(hrPerson.getCname());
		moLog.setHandleDate(dissension.getHappenDate());
		moLog.setResultMsg(dissension.getResults());
		moLog.setS1("调解员" + hrPerson.getCname() + "受理，请保持手机畅通");
		moLog.setS2(dissension.getAttachments());
		moLogMapper.insert(moLog);
		dissensionMapper.updateById(dissension);
	}


	@Override
	public List<DissensionDetailsResult> getDissensionDetails(Dissension dissension) {
		List<DissensionDetailsResult> detailsResultList=new ArrayList<>();
		QueryWrapper<Dissension> queryWrapper = new QueryWrapper<>();

		LoginUser sysUser=(LoginUser) SecurityUtils.getSubject().getPrincipal();

		if (sysUser.getUserIdentity()==20) {
			queryWrapper.like("org_code",sysUser.getDistrictCode());
		}else if(sysUser.getUserIdentity()==40){
			queryWrapper.like("org_code",sysUser.getDistrictCode());
		}else if(sysUser.getUserIdentity()==60){
			queryWrapper.and(wrapper -> wrapper.eq("resp_persons", sysUser.getUsername()).or(wrapper2 -> wrapper2.eq("create_by", sysUser.getUsername())));
		}

		if (dissension.getState() != null && dissension.getState() != "") {
			queryWrapper.eq("state", dissension.getState());
		}

		queryWrapper.eq("del_flag",0);
		queryWrapper.orderByDesc("happen_date");
		List<Dissension> dissensionsList = dissensionMapper.selectList(queryWrapper);

		for (Dissension d : dissensionsList) {
			DissensionDetailsResult detailsResult= new DissensionDetailsResult();
			BeanUtils.copyProperties(d,detailsResult);
			AndonRepairMoMan responsibleUser = andonRepairMoManMapper.selectByPrincipal(d.getCode());
			if(responsibleUser!=null){
				detailsResult.setPhone(responsibleUser.getPhone());
			}
			detailsResultList.add(detailsResult);
		}
		return detailsResultList;
	}
}
