package com.qfy.modules.cms.mapper;

import com.qfy.modules.commons.model.TreeSelectModel;
import org.apache.ibatis.annotations.Param;
import com.qfy.modules.cms.entity.CmsSection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Description: 栏目管理
 * @Author: jeecg-boot
 * @Date:   2022-04-05
 * @Version: V1.0
 */
public interface CmsSectionMapper extends BaseMapper<CmsSection> {


	/**
	 * 根据父级ID查询树节点数据
	 * @param pid
	 * @param query
	 * @return
	 */
	public List<TreeSelectModel> queryListByPid(@Param("pid")  String pid, @Param("query") Map<String, String> query);

	/**
	 * 通过code查询分类字典表
	 * @param code
	 * @return
	 */
	@Select("SELECT ID FROM qfy_cms_section WHERE name_code = #{code,jdbcType=VARCHAR}")
	public String queryIdByCode(@Param("code")  String code);

	/**
	 * 编辑节点状态
	 * @param id
	 * @param status
	 */
	void updateTreeNodeStatus(@Param("id") String id,@Param("status") String status);


}
