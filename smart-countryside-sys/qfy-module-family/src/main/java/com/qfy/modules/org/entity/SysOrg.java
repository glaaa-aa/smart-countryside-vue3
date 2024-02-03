package com.qfy.modules.org.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfy.modules.commons.entity.BaseEntity;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: qfy_sys_organization
 * @Author: jeecg-boot
 * @Date:   2022-06-01
 * @Version: V1.0
 */
@Data
@TableName("qfy_sys_organization")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_sys_organization对象", description="qfy_sys_organization")
public class SysOrg extends BaseEntity implements Serializable,Comparable<SysOrg> {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
    /**tenantId*/
    @Excel(name = "tenantId", width = 15)
    @ApiModelProperty(value = "tenantId")
    private Integer tenantId;
	/**父级节点*/
	@Excel(name = "父级节点", width = 15)
    @ApiModelProperty(value = "父级节点")
    private String pid;
	/**父级编码*/
	@Excel(name = "父级编码", width = 15)
    @ApiModelProperty(value = "父级编码")
    private String pcode;
	/**类型名称*/
	@Excel(name = "类型名称", width = 15)
    @ApiModelProperty(value = "类型名称")
    private String name;
	/**类型编码*/
	@Excel(name = "类型编码", width = 15)
    @ApiModelProperty(value = "类型编码")
    private String code;
	/**层级*/
	@Excel(name = "层级", width = 15)
    @ApiModelProperty(value = "层级")
    private Integer orgLevel;
	/**排序*/
	@Excel(name = "排序", width = 15)
    @ApiModelProperty(value = "排序")
    private Integer departOrder;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
    private String description;
	/**是否有子节点*/
	@Excel(name = "是否有子节点", width = 15)
    @ApiModelProperty(value = "是否有子节点")
    private String hasChild;

    @Override
    public int compareTo(SysOrg o) {
        //比较条件我们定的是按照code的长度升序
        // <0：当前对象比传入对象小。
        // =0：当前对象等于传入对象。
        // >0：当前对象比传入对象大。
        int	 s = this.code.length() - o.code.length();
        return s;
    }

    @Override
    public String toString() {
        return "SysCategory [code=" + code + ", name=" + name + "]";
    }
}
