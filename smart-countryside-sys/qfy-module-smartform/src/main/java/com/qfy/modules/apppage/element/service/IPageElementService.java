package com.qfy.modules.apppage.element.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.apppage.element.entity.PageElement;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.vo.SelectTreeModel;

import java.util.List;

/**
 * @Description: 页面元素管理
 * @Author: jeecg-boot
 * @Date:   2024-01-25
 * @Version: V1.0
 */
public interface IPageElementService extends IService<PageElement> {

	/**根节点父ID的值*/
	public static final String ROOT_PID_VALUE = "0";
	
	/**树节点有子节点状态值*/
	public static final String HASCHILD = "1";
	
	/**树节点无子节点状态值*/
	public static final String NOCHILD = "0";

	/**
	 * 新增节点
	 *
	 * @param pageElement
	 */
	void addPageElement(PageElement pageElement);
	
	/**
   * 修改节点
   *
   * @param pageElement
   * @throws JeecgBootException
   */
	void updatePageElement(PageElement pageElement) throws JeecgBootException;
	
	/**
	 * 删除节点
	 *
	 * @param id
   * @throws JeecgBootException
	 */
	void deletePageElement(String id) throws JeecgBootException;

	  /**
	   * 查询所有数据，无分页
	   *
	   * @param queryWrapper
	   * @return List<PageElement>
	   */
    List<PageElement> queryTreeListNoPage(QueryWrapper<PageElement> queryWrapper);

	/**
	 * 【vue3专用】根据父级编码加载分类字典的数据
	 *
	 * @param parentCode
	 * @return
	 */
	List<SelectTreeModel> queryListByCode(String parentCode);

	/**
	 * 【vue3专用】根据pid查询子节点集合
	 *
	 * @param pid
	 * @return
	 */
	List<SelectTreeModel> queryListByPid(String pid);

}
