package com.qfy.modules.family.model;


import com.qfy.modules.family.entity.FamilyBase;
import com.qfy.modules.family.entity.FamilyBaseHouse;
import com.qfy.modules.family.entity.FamilyBaseProduction;
import com.qfy.modules.family.entity.FamilyBaseWater;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FamilyExport implements Serializable {
    private static final long serialVersionUID = 1L;

    @ExcelCollection(name="户基本信息", orderNum = "0")
    private List<FamilyBase> familyBaseList = new ArrayList<>();
    @ExcelCollection(name="住房", orderNum = "1")
    private List<FamilyBaseHouse> houseList = new ArrayList<>();
    @ExcelCollection(name="饮水安全", orderNum = "2")
    private List<FamilyBaseWater> waterList = new ArrayList<>();
    @ExcelCollection(name="产业", orderNum = "3")
    private List<FamilyBaseProduction> productionList = new ArrayList<>();
//    @ExcelCollection(name="家庭成员")
//    private List<FamilyMember> familyMemberList;

}
