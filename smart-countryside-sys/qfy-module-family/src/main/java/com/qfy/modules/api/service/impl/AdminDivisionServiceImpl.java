package com.qfy.modules.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qfy.modules.api.domain.AdminDivision;
import com.qfy.modules.api.service.IAdminDivisionService;
import com.qfy.modules.org.entity.SysOrg;
import com.qfy.modules.org.mapper.SysOrgMapper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.jeecg.common.api.vo.Result;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author lanls
 * @version 1.0
 * @date 2023/11/20 9:10
 */
@Service
public class AdminDivisionServiceImpl implements IAdminDivisionService {

    @Resource
    private SysOrgMapper sysorgmapper;

    @Override
    public Result getDivisionList(String code, String parentId) {
        QueryWrapper<SysOrg> wrapper = new QueryWrapper<>();
        if (code != null && !code.isEmpty()) {
            wrapper.likeRight("code", code);
        }

        String pid = parentId;
        if (parentId != null && !parentId.isEmpty() && !parentId.equals("0")) {
            wrapper.and(o -> {
                o.eq("pid", pid);
                o.or();
                o.likeRight("pcode", pid);
            });
        }
        wrapper.orderByAsc("code");
        List<SysOrg> list = sysorgmapper.selectList(wrapper);
        List<AdminDivision> divisionList = new ArrayList<>();
        recursion(list, divisionList, parentId);
        return Result.OK(divisionList);
    }

    /**
     * 通过递归方式遍历给定的 SysOrg 列表，将满足指定 parentId 的子节点组织机构信息存入给定的 AdminDivision 列表中
     *
     * @param list SysOrg 列表，存储了组织机构的信息
     * @param divisionList AdminDivision 列表，用于存储递归过程中找到的组织机构信息
     * @param parentId 父节点组织机构的 id，只遍历具有该父节点的子节点组织机构
     */
    private void recursion(List<SysOrg> list, List<AdminDivision> divisionList, String parentId) {
        for (SysOrg sysOrg : list) {
            if (sysOrg.getPid().equals(parentId)) {
                AdminDivision division = new AdminDivision();
                division.setId(sysOrg.getId());
                division.setCode(sysOrg.getCode());
                division.setName(sysOrg.getName());
                division.setPcode(sysOrg.getPcode());
                List<AdminDivision> children = new ArrayList<>();
                division.setChildren(children);
                divisionList.add(division);
                recursion(list, children, division.getId());
            }
        }
    }
}
