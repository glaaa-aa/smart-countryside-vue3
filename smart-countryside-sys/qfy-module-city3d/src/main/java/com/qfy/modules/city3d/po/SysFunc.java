package com.qfy.modules.city3d.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfy.modules.commons.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 地图应用菜单权限
 * @Author: lanls
 * @Date:   2022-07-21
 * @Version: V1.0
 */
@Data
@TableName("sys_func")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="sys_func对象", description="地图应用菜单权限")
public class SysFunc extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -8376728184813788523L;

    /**funcId*/
    @Excel(name = "funcId", width = 15)
    @ApiModelProperty(value = "funcId")
    @TableId(type = IdType.ASSIGN_ID)
    private java.lang.String funcId;
    /**funcUrl*/
    @Excel(name = "funcUrl", width = 15)
    @ApiModelProperty(value = "funcUrl")
    private java.lang.String funcUrl;
    /**funcIotName*/
    @Excel(name = "funcIotName", width = 15)
    @ApiModelProperty(value = "funcIotName")
    private java.lang.String funcIotName;
    /**funcIotImg*/
    @Excel(name = "funcIotImg", width = 15)
    @ApiModelProperty(value = "funcIotImg")
    private java.lang.String funcIotImg;
    /**funcType*/
    @Excel(name = "funcType", width = 15)
    @ApiModelProperty(value = "funcType")
    private java.lang.String funcType;
    /**funcSrc*/
    @Excel(name = "funcSrc", width = 15)
    @ApiModelProperty(value = "funcSrc")
    private java.lang.String funcSrc;
    /**funcDefaultName*/
    @Excel(name = "funcDefaultName", width = 15)
    @ApiModelProperty(value = "funcDefaultName")
    private java.lang.String funcDefaultName;
    /**funcPid*/
    @Excel(name = "funcPid", width = 15)
    @ApiModelProperty(value = "funcPid")
    @Dict(dictTable = "sys_func", dicCode = "func_id", dicText = "func_default_name")
    private java.lang.String funcPid;
    /**funcLevel*/
    @Excel(name = "funcLevel", width = 15)
    @ApiModelProperty(value = "funcLevel")
    private java.lang.String funcLevel;
    /**funcIcon*/
    @Excel(name = "funcIcon", width = 15)
    @ApiModelProperty(value = "funcIcon")
    private java.lang.String funcIcon;
    private Integer sort;

    @TableField(exist = false)
    private String roleId;

    @TableField(exist = false)
    private List<SysFunc> children;
}
