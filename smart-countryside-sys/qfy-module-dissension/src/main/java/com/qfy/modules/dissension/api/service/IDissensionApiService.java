package com.qfy.modules.dissension.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.dissension.api.vo.DissensionDetailsResult;
import com.qfy.modules.dissension.entity.Dissension;
import com.qfy.modules.dissension.entity.DissensionInvolvedUser;
import com.qfy.modules.dissension.entity.AndonRepairMoMan;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description: 矛盾纠纷
 * @Author: jeecg-boot
 * @Date:   2023-12-26
 * @Version: V1.0
 */
public interface IDissensionApiService extends IService<Dissension> {

	/**
	 * 添加一对多
	 *
	 * @param dissension
	 * @param dissensionInvolvedUserList
	 */
	public void saveMain(Dissension dissension, List<DissensionInvolvedUser> dissensionInvolvedUserList) ;

	/**
	 * 修改一对多
	 *
   * @param dissension
   * @param dissensionInvolvedUserList
   * @param andonRepairMoManList
	 */
	public void updateMain(Dissension dissension,List<DissensionInvolvedUser> dissensionInvolvedUserList,List<AndonRepairMoMan> andonRepairMoManList);

	/**
	 * 删除一对多
	 *
	 * @param id
	 */
	public void delMain (String id);

	/**
	 * 批量删除一对多
	 *
	 * @param idList
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);

	/**
	 * 分派人员
	 * @param dissension
	 * @param andonRepairMoManList
	 * @throws Exception
	 */
	public void feedback(Dissension dissension, List<AndonRepairMoMan> andonRepairMoManList) throws Exception;


	/**
	 * API 矛盾纠纷-列表查询
	 */
	List<DissensionDetailsResult> getDissensionDetails(Dissension dissension);

}
