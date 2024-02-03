package com.qfy.modules.family.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qfy.modules.family.entity.FamilyMember;
import com.qfy.modules.family.model.FamilyBaseVo;

import java.util.List;

/**
 * @Description: 家庭成员管理
 * @Author: jeecg-boot
 * @Date:   2022-06-02
 * @Version: V1.0
 */
public interface IFamilyMemberService extends IService<FamilyMember> {


    /**
     * 添加一对多
     *
     * @param familyBaseVo
     * @param familyMemberList
     */
    public void saveMain(FamilyBaseVo familyBaseVo, List<FamilyMember> familyMemberList) ;

    /**
     * 修改一对多
     *
     * @param familyBase
     * @param familyMemberList
     */
//    public void updateMain(FamilyBaseVo familyBaseVo,List<FamilyMember> familyMemberList);

    /**
     * 通过主表id查询子表数据
     *
     * @param mainId 主表id
     * @return List<FamilyMember>
     */
    public List<FamilyMember> selectByMainId(String mainId);

    List<FamilyMember> selectByGroupByIdCard(String fimalyNumber);
}
