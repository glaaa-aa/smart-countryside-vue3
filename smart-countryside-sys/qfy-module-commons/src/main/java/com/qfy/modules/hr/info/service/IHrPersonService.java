package com.qfy.modules.hr.info.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.hr.info.entity.HrPerson;
import org.jeecg.common.system.vo.LoginUser;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * @Description: 人员档案
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
public interface IHrPersonService extends IService<HrPerson> {

	/**
	 * 添加一对多
	 *
	 */
//	public void saveMain(HrPerson hrPerson,List<HrEdu> hrEduList,List<HrJob> hrJobList) ;
//
//	/**
//	 * 修改一对多
//	 *
//	 */
//	public void updateMain(HrPerson hrPerson,List<HrEdu> hrEduList,List<HrJob> hrJobList);

	/**
	 * 删除一对多
	 */
	public void delMain (String id);

	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);

//	List<SchUserImg> AddUserImg(Map<String, MultipartFile> fileMap, LoginUser loginUser);

	HrPerson AddUserImgByCode(MultipartFile file, String userId, LoginUser loginUser) throws Exception;

	/**
	 * 添加负责人员
	 * @param hrPerson
	 */
    void saveHrPerson(HrPerson hrPerson);

	void updateMain(HrPerson hrPerson);
}
