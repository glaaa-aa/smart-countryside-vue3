package com.qfy.modules.dissension.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.qfy.modules.hr.info.bean.SysOrgBean;
import com.qfy.modules.hr.info.bean.SysUserBean;
import com.qfy.modules.hr.info.service.CommonOpenService;
import com.qfy.modules.org.entity.SysOrg;
import com.qfy.modules.org.mapper.SysOrgMapper;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.mapper.SysUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonModuleService implements CommonOpenService {

    @Autowired
    private SysOrgMapper sysOrgMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysOrgBean getOrg(String orgCode) {
        SysOrg sysOrg = sysOrgMapper.queryByCode(orgCode);
        SysOrgBean bean = new SysOrgBean();
        BeanUtils.copyProperties(sysOrg,bean);
        return bean;
    }

    @Override
    public void savaUser(SysUserBean sysUserBean) {
        SysUser user=new SysUser();
        BeanUtils.copyProperties(sysUserBean,user);
        sysUserMapper.insert(user);
    }

    @Override
    public void updateByUser(SysUserBean sysUserBean) {
        SysUser userByName = sysUserMapper.getUserByName(sysUserBean.getUsername());
        userByName.setPhone(sysUserBean.getPhone());
        userByName.setRealname(sysUserBean.getRealname());
        userByName.setDistrictCode(sysUserBean.getDistrictCode());
        userByName.setDepartIds(sysUserBean.getDepartIds());
        sysUserMapper.updateById(userByName);
    }

    @Override
    public void deleteUser(String userName) {
        SysUser userByName = sysUserMapper.getUserByName(userName);
        if(ObjectUtils.isNotEmpty(userByName)){
            sysUserMapper.deleteById(userByName.getId());
        }
    }
}
