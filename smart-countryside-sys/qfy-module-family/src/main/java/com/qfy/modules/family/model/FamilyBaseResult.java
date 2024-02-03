package com.qfy.modules.family.model;

import com.qfy.modules.family.entity.FamilyBase;
import com.qfy.modules.family.entity.FamilyBaseHouse;
import com.qfy.modules.family.entity.FamilyBaseProduction;
import com.qfy.modules.family.entity.FamilyBaseWater;
import com.qfy.modules.family.entity.FamilyMember;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author lanls
 * @version 1.0
 * @description:
 * @date 2022/8/8 16:03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FamilyBaseResult implements Serializable {

    private static final long serialVersionUID = 2749075184491935791L;

    private FamilyBase familyBase;
    private FamilyBaseHouse house;
    private FamilyBaseWater water;
    private FamilyBaseProduction production;
    private List<FamilyMember> members;
}
