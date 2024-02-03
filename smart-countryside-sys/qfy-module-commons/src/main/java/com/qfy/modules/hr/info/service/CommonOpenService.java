package com.qfy.modules.hr.info.service;


import com.qfy.modules.hr.info.bean.SysOrgBean;
import com.qfy.modules.hr.info.bean.SysUserBean;

public interface CommonOpenService {

    /**
     * 根据编号获取 qfy_sys_organization 数据
     * @param orgCode
     * @return
     */
    public SysOrgBean getOrg(String orgCode);

    /**
     * 添加用户
     * @param sysUserBean
     */
    public void savaUser(SysUserBean sysUserBean);

    void updateByUser(SysUserBean sysUserBean);

    void deleteUser(String userName);
}
