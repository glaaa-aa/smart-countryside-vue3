package com.qfy.modules.dissension.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.dissension.entity.AndonRepairMoMan;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 纠纷负责处理人员表
 * @Author: jeecg-boot
 * @Date:   2023-12-26
 * @Version: V1.0
 */
@Repository
public interface AndonRepairMoManMapper extends BaseMapper<AndonRepairMoMan> {

	/**
	 * 通过主表id删除子表数据
	 *
	 * @param mainId 主表id
	 * @return boolean
	 */
	public boolean deleteByMainId(@Param("mainId") String mainId);

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId 主表id
   * @return List<QfyDissensionResponsibleUser>
   */
	public List<AndonRepairMoMan> selectByMainId(@Param("mainId") String mainId);

	/**
	 * 查询矛盾纠纷主要负责人
	 *@param code 主表code
	 */
	public AndonRepairMoMan selectByPrincipal(@Param("code") String code);
}
