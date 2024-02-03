package com.qfy.modules.hr.info.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeecg.weibo.exception.BusinessException;
import com.qfy.modules.hr.info.bean.SysOrgBean;
import com.qfy.modules.hr.info.bean.SysUserBean;
import com.qfy.modules.hr.info.entity.HrPerson;
import com.qfy.modules.hr.info.mapper.HrPersonMapper;
import com.qfy.modules.hr.info.service.CommonOpenService;
import com.qfy.modules.hr.info.service.IHrPersonService;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.system.vo.SysDepartModel;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.PasswordUtil;
import org.jeecg.common.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * @Description: 人员档案
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
@Service
public class HrPersonServiceImpl extends ServiceImpl<HrPersonMapper, HrPerson> implements IHrPersonService {
	@Value(value = "${jeecg.path.upload}")
	private String uploadpath;

	@Autowired
	private HrPersonMapper hrPersonMapper;
	@Autowired
	private ISysBaseAPI sysBaseAPI;
	@Autowired
	private CommonOpenService openService;

//	@Override
//	@Transactional
//	public void saveMain(HrPerson hrPerson, List<HrEdu> hrEduList,List<HrJob> hrJobList) {
//		hrPerson.setDelFlag("0");
//		hrPerson.setZaizhi("0");
//
//		//获取部门
//		if(StringUtils.isNotEmpty(hrPerson.getOrgId())){
//			SysDepartModel departModel = sysBaseAPI.selectAllById(hrPerson.getOrgId());
//			if(departModel != null ){
//				hrPerson.setBmmc(departModel.getDepartName());
//				hrPerson.setOrgCode(departModel.getOrgCode());
//			}
//		}
//
//		hrPersonMapper.insert(hrPerson);
//		if(hrEduList!=null && hrEduList.size()>0) {
//			for(HrEdu entity:hrEduList) {
//				//外键设置
//				entity.setEmpid(hrPerson.getId());
//				hrEduMapper.insert(entity);
//			}
//		}
//		if(hrJobList!=null && hrJobList.size()>0) {
//			for(HrJob entity:hrJobList) {
//				//外键设置
//				entity.setEmpid(hrPerson.getId());
//				hrJobMapper.insert(entity);
//			}
//		}
//	}
//
//	@Override
//	@Transactional
//	public void updateMain(HrPerson hrPerson,List<HrEdu> hrEduList,List<HrJob> hrJobList) {
//
//		//获取部门
//		if(StringUtils.isNotEmpty(hrPerson.getOrgId())){
//			SysDepartModel departModel = sysBaseAPI.selectAllById(hrPerson.getOrgId());
//			if(departModel != null ){
//				hrPerson.setBmmc(departModel.getDepartName());
//				hrPerson.setOrgCode(departModel.getOrgCode());
//			}
//		}
//
//		hrPersonMapper.updateById(hrPerson);
//
//		//1.先删除子表数据
//		hrEduMapper.deleteByMainId(hrPerson.getId());
//		hrJobMapper.deleteByMainId(hrPerson.getId());
//
//		//2.子表数据重新插入
//		if(hrEduList!=null && hrEduList.size()>0) {
//			for(HrEdu entity:hrEduList) {
//				//外键设置
//				entity.setEmpid(hrPerson.getId());
//				hrEduMapper.insert(entity);
//			}
//		}
//		if(hrJobList!=null && hrJobList.size()>0) {
//			for(HrJob entity:hrJobList) {
//				//外键设置
//				entity.setEmpid(hrPerson.getId());
//				hrJobMapper.insert(entity);
//			}
//		}
//	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		HrPerson person = getById(id);
		LambdaUpdateWrapper<HrPerson> updateWrapper = new UpdateWrapper<HrPerson>().lambda();
		updateWrapper.set(HrPerson::getDelFlag, 1).eq(HrPerson::getId, id);
		baseMapper.update(new HrPerson(), updateWrapper);

		openService.deleteUser(person.getPersonNo());
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			HrPerson person = getById(id);
			hrPersonMapper.deleteById(id);//需要同步删除设备人员
		}
	}

	/**
	 * 多图片上传，实现人员图片批量导入
	 * 1、按照文件依次解析图片，并保存在服务器
	 * 2、根据文件名（员工号_姓名）添加表关联。
	 * @param loginUser
	 */
//	@Override
//	public List<SchUserImg> AddUserImg(Map<String, MultipartFile> fileMap, LoginUser loginUser) {
//		if(loginUser == null){
//			return null;
//		}
//
//		//文件存储相对路径，每天建立一个目录
//		String dateStr = DateUtils.date2Str(DateUtils.yyyyMMdd.get());
//		String bizPath = "face/"+dateStr+"/"; //相对路径
//		File files = new File(uploadpath + File.separator + bizPath);
//		if (!files.exists()) {
//			files.mkdirs();// 创建文件根目录
//		}
//		//先保存图片，避免长时间连接造成内存溢出。并缓存图片集对象
//		List<SchUserImg> _list = new ArrayList<>();//上传的头像合集
//		List<SchUserImgLog> _loglist = new ArrayList<>();//上传的头像合集
//		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
//			MultipartFile file = entity.getValue();// 获取上传文件对象
//			try {
//				String orgName = file.getOriginalFilename();// 获取文件路径全部信息
//				//文件名称
//				String fileName = orgName.substring(orgName.lastIndexOf("/")+1 );//获取文件名称
//				String name = fileName.substring(0, fileName.lastIndexOf(".")); //获取文件夹名称无后缀
//				String[] _arr = name.split("_");
//				String _userId = _arr[0].toUpperCase().replaceAll(" ","");
//				String _userName = _arr[0];
//				if(_arr.length == 2){
//					_userName = _arr[1].replaceAll(" ","");
//				}
//				//保存路径
//				String savePath = files.getPath() + File.separator + fileName;
//				FileCopyUtils.copy(file.getBytes(), new File(savePath));
////                // 判断 _userId 身份证号是否正确 如果不正确 加错误日志
//				if (StringUtils.isEmpty(_userId)){
//					// 添加错误日志
//					SchUserImgLog log = new SchUserImgLog();
//					log.setImgName(fileName);
//					log.setResultMsg("图片名称格式错误");
//					log.setCreateTime(new Date());
//					log.setUserName(_userName);
//					log.setUserId(_userId);
//					log.setUserType("人员");
//					log.setOrgCode(loginUser.getOrgCode());
//					log.setResultCode("-1");//身份证号错误
//					//userImgLogService.saveUserImgLog(log);
//					_loglist.add(log);
//				}else{
//					SchUserImg img = new SchUserImg();
//					img.setImgUrl("/"+bizPath+fileName);
//					img.setSavePath(savePath);
//					img.setUserName(_userName);
//					img.setDelFlag(0);
//					img.setUserNo(_userId);
//					//img.setUserType("1");
//					img.setOrgCode(loginUser.getOrgCode());
////                    img.setImgCode("data:image/jpeg;base64,"+ImgToBase64.ImageToBase64(savePath));
////                    img.setImgCode(null);
//					_list.add(img);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				log.error(e.getMessage(), e);
//			} finally {
//				try {
//					file.getInputStream().close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		for(SchUserImg userImg : _list){
//			long count = hrPersonMapper.updateFace(userImg.getImgUrl(), userImg.getUserNo());
//			if(count<=0){
//				SchUserImgLog log = new SchUserImgLog();
//				log.setImgName(userImg.getImgUrl());
//				log.setResultMsg("人员不存在或图片命名错误");
//				log.setCreateTime(new Date());
//				log.setUserName(userImg.getUserName());
//				log.setUserId(userImg.getUserNo());
//				log.setUserType("人员");
//				log.setOrgCode(loginUser.getOrgCode());
//				log.setResultCode("-1");//身份证号错误
//				//userImgLogService.saveUserImgLog(log);
//				_loglist.add(log);
//			}else{
//				userImg.setMsg("上传成功");
//			}
//		}
//		//通过线程同步信息到设备，
////		ExecutorService exs = Executors.newCachedThreadPool();
////		FaceToBase64Util.FaceToBase64Thread thread = new FaceToBase64Util.FaceToBase64Thread();
////		thread.setImgs(_list);
////		thread.setLoginUser(loginUser);
////		exs.submit(thread);
//		return _list;
//	}

	@Override
	public HrPerson AddUserImgByCode(MultipartFile file,String userId, LoginUser loginUser) throws Exception{
		HrPerson user = hrPersonMapper.selectByCardNo(userId);
		if (user == null){
			user = getById(userId);
		}
		if(user == null){
			throw new BusinessException("未查到用户信息");
		}

		//文件存储相对路径，每天建立一个目录
		String dateStr = DateUtils.date2Str(DateUtils.yyyyMMdd.get());
		String bizPath = "face/"+dateStr+"/"; //相对路径
		File files = new File(uploadpath + File.separator + bizPath);
		if (!files.exists()) {
			files.mkdirs();// 创建文件根目录
		}
		try {
			//文件名称
			String fileName = user.getCardNo() +"_"+user.getCname()+".jpg";
			//保存路径
			String savePath = files.getPath() + File.separator + fileName;
			FileCopyUtils.copy(file.getBytes(), new File(savePath));
			hrPersonMapper.updateFace("/"+bizPath+fileName,user.getCardNo());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} finally {
			try {
				file.getInputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return user;
	}


	@Override
	@Transactional
	public void saveHrPerson(HrPerson hrPerson) {

		SysUserBean byUser = new SysUserBean();
		SysOrgBean org = openService.getOrg(hrPerson.getOrgCode());
		if(ObjectUtils.isNotEmpty(org)){
			hrPerson.setOrgName(org.getOrgName());
			hrPerson.setC1(org.getDescription());
			byUser.setDistrictCode(hrPerson.getOrgCode());
		}
		SysDepartModel departModel = sysBaseAPI.getDepartByCode(hrPerson.getDepartCode());
		if(ObjectUtils.isNotEmpty(departModel)){
			hrPerson.setDepartName(departModel.getDepartName());
			byUser.setDepartIds(departModel.getId());
		}


		byUser.setUsername(hrPerson.getPersonNo());
		byUser.setRealname(hrPerson.getCname());
		String salt = oConvertUtils.randomGen(8);
		byUser.setSalt(salt);
		String passwordEncode = PasswordUtil.encrypt(byUser.getUsername(), "123456", salt);
		byUser.setPassword(passwordEncode);
		byUser.setDelFlag(CommonConstant.DEL_FLAG_0);
		byUser.setActivitiSync(CommonConstant.ACT_SYNC_1);
		byUser.setStatus(1);
		byUser.setUserIdentity(60);
		byUser.setUserType(1);
		byUser.setSortOrder(100);
		openService.savaUser(byUser);

		this.save(hrPerson);
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(HrPerson hrPerson) {
		SysUserBean sysUserBean=new SysUserBean();
		sysUserBean.setUsername(hrPerson.getPersonNo());
		sysUserBean.setRealname(hrPerson.getCname());
		sysUserBean.setDistrictCode(hrPerson.getOrgCode());
		sysUserBean.setPhone(sysUserBean.getPhone());

		SysOrgBean org = openService.getOrg(hrPerson.getOrgCode());
		if(ObjectUtils.isNotEmpty(org)){
			hrPerson.setOrgName(org.getOrgName());
			hrPerson.setC1(org.getDescription());
		}
		SysDepartModel departModel = sysBaseAPI.getDepartByCode(hrPerson.getDepartCode());
		if(ObjectUtils.isNotEmpty(departModel)){
			hrPerson.setDepartName(departModel.getDepartName());
			sysUserBean.setDepartIds(departModel.getId());
		}
		openService.updateByUser(sysUserBean);
		this.updateById(hrPerson);

	}


}
