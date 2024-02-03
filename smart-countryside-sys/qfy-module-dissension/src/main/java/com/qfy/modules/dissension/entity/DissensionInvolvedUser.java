package com.qfy.modules.dissension.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.qfy.modules.commons.entity.BaseEntity;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 矛盾纠纷涉及人员表
 * @Author: jeecg-boot
 * @Date:   2023-12-26
 * @Version: V1.0
 */
@ApiModel(value="qfy_dissension_involved_user对象", description="矛盾纠纷涉及人员表")
@Data
@TableName("qfy_dissension_involved_user")
public class DissensionInvolvedUser extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**矛盾纠纷编号*/
    @ApiModelProperty(value = "矛盾纠纷编号")
    private String tensionCode;
	/**人员姓名*/
	@Excel(name = "人员姓名", width = 15)
    @ApiModelProperty(value = "人员姓名")
    private String realname;
	/**身份证号*/
	@Excel(name = "身份证号", width = 15)
    @ApiModelProperty(value = "身份证号")
    private String idCard;
	/**家庭地点*/
	@Excel(name = "家庭地点", width = 15)
    @ApiModelProperty(value = "家庭地点")
    private String address;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
    private String phone;
	
	/**租户id*/
	@Excel(name = "租户id", width = 15)
    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    /**性别*/
    @Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
    private Integer sex;
}
