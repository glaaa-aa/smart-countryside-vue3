package com.qfy.modules.smartform.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 巡检路线-巡检点关系表
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Data
@TableName("qfy_check_route_point")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_check_route_point对象", description="巡检路线-巡检点关系表")
public class CheckRoutePoint implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.Integer id;
	/**巡检路线id*/
	@Excel(name = "巡检路线id", width = 15)
    @ApiModelProperty(value = "巡检路线id")
    private java.lang.Integer chkRouteId;
	/**巡检点id*/
	@Excel(name = "巡检点id", width = 15)
    @ApiModelProperty(value = "巡检点id")
    private java.lang.Integer chkPoiId;
	/**数据排序*/
	@Excel(name = "数据排序", width = 15)
    @ApiModelProperty(value = "数据排序")
    private java.lang.Integer sortno;
}
