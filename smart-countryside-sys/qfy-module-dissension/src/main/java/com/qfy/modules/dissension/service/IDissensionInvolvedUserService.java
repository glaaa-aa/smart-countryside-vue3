package com.qfy.modules.dissension.service;

import com.qfy.modules.dissension.entity.DissensionInvolvedUser;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 矛盾纠纷涉及人员表
 * @Author: jeecg-boot
 * @Date:   2023-12-26
 * @Version: V1.0
 */
public interface IDissensionInvolvedUserService extends IService<DissensionInvolvedUser> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<QfyDissensionInvolvedUser>
	 */
	public List<DissensionInvolvedUser> selectByMainId(String mainId);
}
