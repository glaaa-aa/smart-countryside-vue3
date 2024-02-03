package com.qfy.modules.family.model;


import com.qfy.modules.family.entity.FamilyBase;
import com.qfy.modules.family.entity.FamilyBaseHouse;
import com.qfy.modules.family.entity.FamilyBaseProduction;
import com.qfy.modules.family.entity.FamilyBaseWater;
import com.qfy.modules.family.entity.FamilyMember;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FamilyBasePage implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private String id; //户id
    private String familyNumber; //户编号
    private String year; //年份

    private Boolean hasBase = false; //是否有权限
    private Boolean hasWater= false;
    private Boolean hasHouse= false;
    private Boolean hasProduction= false;


    @ExcelEntity(name="户信息")
    private FamilyBase familyBase;
    @ExcelEntity(name="住房信息")
    private FamilyBaseHouse familyHouse;
    @ExcelEntity(name="饮水安全信息")
    private FamilyBaseWater familyWater;
    @ExcelEntity(name="生产信息")
    private FamilyBaseProduction familyProduction;


    //家庭成员
    @ExcelCollection(name="家庭成员管理")
    @ApiModelProperty(value = "家庭成员管理")
    private List<FamilyMember> familyMemberList;

}
