package com.qfy.modules.dissension.service;

import com.qfy.modules.dissension.entity.AndonRepairMoMan;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 纠纷负责处理人员表
 * @Author: jeecg-boot
 * @Date:   2023-12-26
 * @Version: V1.0
 */
public interface AndonRepairMoManService extends IService<AndonRepairMoMan> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<QfyDissensionResponsibleUser>
	 */
	public List<AndonRepairMoMan> selectByMainId(String mainId);

	public AndonRepairMoMan selectByPrincipal(String code);
}
