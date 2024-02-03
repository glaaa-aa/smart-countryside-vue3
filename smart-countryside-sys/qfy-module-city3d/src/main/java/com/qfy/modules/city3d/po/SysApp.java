package com.qfy.modules.city3d.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfy.modules.commons.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 前端应用
 * @Author: lanls
 * @Date:   2022-07-21
 * @Version: V1.0
 */
@Data
@TableName("sys_app")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="sys_app对象", description="前端应用")
public class SysApp extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8632634468427305765L;
    /**id*/
    @Excel(name = "id", width = 15)
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.ASSIGN_ID)
    private java.lang.String appId;
    /**名称*/
    @Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
    private java.lang.String appName;
    /**Logo*/
    @Excel(name = "Logo", width = 15)
    @ApiModelProperty(value = "Logo")
    private java.lang.String appLogo;
    /**UI模板*/
    @Excel(name = "UI模板", width = 15)
    @ApiModelProperty(value = "UI模板")
    private java.lang.String appUiMode;
    /**视角*/
    @Excel(name = "视角", width = 15)
    @ApiModelProperty(value = "视角")
    private java.lang.String appPos;
    /**地图影像*/
    @Excel(name = "地图影像", width = 15)
    @ApiModelProperty(value = "地图影像")
    private java.lang.String appImage;
    /**地图地形*/
    @Excel(name = "地图地形", width = 15)
    @ApiModelProperty(value = "地图地形")
    private java.lang.String appTerrain;
}
