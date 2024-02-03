package com.qfy.modules.apppage.element.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: 元素权限管理
 * @Author: jeecg-boot
 * @Date:   2024-01-25
 * @Version: V1.0
 */
@Data
@TableName("qfy_app_page_element_auth")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_app_page_element_auth对象", description="元素权限管理")
public class PageElementAuth implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**租户id*/
	@Excel(name = "租户id", width = 15)
    @ApiModelProperty(value = "租户id")
    private Integer tenantId;
	/**元素ID*/
	@Excel(name = "元素ID", width = 15)
    @ApiModelProperty(value = "元素ID")
    private Integer elementId;
	/**行政区划权限*/
	@Excel(name = "行政区划权限", width = 15)
    @ApiModelProperty(value = "行政区划权限")
    private String orgCode;
	/**组织机构权限*/
	@Excel(name = "组织机构权限", width = 15, dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
	@Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    @ApiModelProperty(value = "组织机构权限")
    private String departCode;
	/**角色权限*/
	@Excel(name = "角色权限", width = 15)
    @ApiModelProperty(value = "角色权限")
    private String roleCode;
	/**用户身份*/
	@Excel(name = "用户身份", width = 15)
    @ApiModelProperty(value = "用户身份")
    private Integer userIdentity;
	/**权限控制类型*/
	@Excel(name = "权限控制类型", width = 15)
    @ApiModelProperty(value = "权限控制类型")
    private String authType;
}
