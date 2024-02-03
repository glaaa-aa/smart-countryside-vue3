package com.qfy.modules.cms.service;

import com.qfy.modules.cms.entity.CmsSection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.commons.model.TreeSelectModel;
import org.jeecg.common.exception.JeecgBootException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.common.util.oConvertUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description: 栏目管理
 * @Author: jeecg-boot
 * @Date:   2022-04-05
 * @Version: V1.0
 */
public interface ICmsSectionService extends IService<CmsSection> {

	/**根节点父ID的值*/
	public static final String ROOT_PID_VALUE = "0";

	/**树节点有子节点状态值*/
	public static final String HASCHILD = "1";

	/**树节点无子节点状态值*/
	public static final String NOCHILD = "0";

	/**新增节点*/
	void addCmsSection(CmsSection cmsSection);

	/**修改节点*/
	void updateCmsSection(CmsSection cmsSection) throws JeecgBootException;

	/**删除节点*/
	void deleteCmsSection(String id) throws JeecgBootException;

	/**查询所有数据，无分页*/
    List<CmsSection> queryTreeListNoPage(QueryWrapper<CmsSection> queryWrapper);



	/**
	 * 根据code查询id
	 * @param code
	 * @return
	 */
	public String queryIdByCode(String code);
	/**
	 * 根据pid查询子节点集合,支持查询条件
	 * @param pid
	 * @param condition
	 * @return
	 */
	public List<TreeSelectModel> queryListByPid(String pid, Map<String, String> condition);
}
