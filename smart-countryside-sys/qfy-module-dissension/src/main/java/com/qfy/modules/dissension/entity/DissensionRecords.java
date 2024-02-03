package com.qfy.modules.dissension.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import com.qfy.modules.commons.entity.BaseEntity;
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
 * @Description: 矛盾纠纷处理记录表
 * @Author: jeecg-boot
 * @Date:   2023-12-26
 * @Version: V1.0
 */
@Data
@TableName("qfy_dissension_records")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qfy_dissension_records对象", description="矛盾纠纷处理记录表")
public class DissensionRecords extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**矛盾纠纷编号*/
	@Excel(name = "矛盾纠纷编号", width = 15)
    @ApiModelProperty(value = "矛盾纠纷编号")
    private String tensionCode;
	/**处理人员id*/
	@Excel(name = "处理人员id", width = 15)
    @ApiModelProperty(value = "处理人员id")
    private String uid;
	/**处理时间*/
	@Excel(name = "处理时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "处理时间")
    private Date handleDate;
	/**处理内容*/
	@Excel(name = "处理内容", width = 15)
    @ApiModelProperty(value = "处理内容")
    private String content;
	/**反馈结果*/
	@Excel(name = "反馈结果", width = 15)
    @ApiModelProperty(value = "反馈结果")
    private String results;
	/**相关材料附件(多个文件以逗号隔开)*/
	@Excel(name = "相关材料附件(多个文件以逗号隔开)", width = 15)
    @ApiModelProperty(value = "相关材料附件(多个文件以逗号隔开)")
    private String attachments;
	/**租户id*/
	@Excel(name = "租户id", width = 15)
    @ApiModelProperty(value = "租户id")
    private Integer tenantId;
    /**s1*/
    @Excel(name = "s1", width = 15)
    @ApiModelProperty(value = "s1")
    private String s1;
    /**s2*/
    @Excel(name = "s2", width = 15)
    @ApiModelProperty(value = "s2")
    private String s2;

}
