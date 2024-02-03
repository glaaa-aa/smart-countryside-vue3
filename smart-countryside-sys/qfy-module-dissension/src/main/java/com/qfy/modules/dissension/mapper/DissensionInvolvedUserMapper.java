package com.qfy.modules.dissension.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfy.modules.dissension.entity.DissensionInvolvedUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 矛盾纠纷涉及人员表
 * @Author: jeecg-boot
 * @Date:   2023-12-26
 * @Version: V1.0
 */
public interface DissensionInvolvedUserMapper extends BaseMapper<DissensionInvolvedUser> {

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
   * @return List<QfyDissensionInvolvedUser>
   */
	public List<DissensionInvolvedUser> selectByMainId(@Param("mainId") String mainId);
}
