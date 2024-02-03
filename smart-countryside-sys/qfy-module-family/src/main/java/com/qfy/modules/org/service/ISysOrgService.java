package com.qfy.modules.org.service;

import com.qfy.modules.commons.model.TreeSelectModel;
import com.qfy.modules.org.entity.SysOrg;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.exception.JeecgBootException;

import java.util.List;
import java.util.Map;

/**
 * @Description: qfy_sys_organization
 * @Author: jeecg-boot
 * @Date:   2022-06-01
 * @Version: V1.0
 */
public interface ISysOrgService extends IService<SysOrg> {

    /**根节点父ID的值*/
    public static final String ROOT_PID_VALUE = "0";

    /**
     * 添加分类字典
     * @param sysOrg
     */
    void addSysOrg(SysOrg sysOrg);

    /**
     * 修改分类字典
     * @param sysOrg
     */
    void updateSysOrg(SysOrg sysOrg);

    /**
     * 根据父级编码加载分类字典的数据
     * @param pcode
     * @return
     * @throws JeecgBootException
     */
    public List<TreeSelectModel> queryListByCode(String pcode) throws JeecgBootException;

    /**
     * 根据pid查询子节点集合
     * @param pid
     * @return
     */
    public List<TreeSelectModel> queryListByPid(String pid);

    /**
     * 根据pid查询子节点集合,支持查询条件
     * @param pid
     * @param condition
     * @return
     */
    public List<TreeSelectModel> queryListByPid(String pid, Map<String,String> condition);

    /**
     * 根据code查询id
     * @param code
     * @return
     */
    public SysOrg queryByCode(String code);

    /**
     * 根据code查询id
     * @param code
     * @return
     */
    public String queryIdByCode(String code);

    /**
     * 删除节点时同时删除子节点及修改父级节点
     * @param ids
     */
    void deleteSysOrg(String ids);

    /**
     * 分类字典控件数据回显[表单页面]
     *
     * @param ids
     * @return
     */
    List<String> loadDictItem(String ids);

    /**
     * 分类字典控件数据回显[表单页面]
     *
     * @param ids
     * @param delNotExist 是否移除不存在的项，设为false如果某个key不存在数据库中，则直接返回key本身
     * @return
     */
    List<String> loadDictItem(String ids, boolean delNotExist);
}
