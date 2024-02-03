package com.qfy.modules.files.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qfy.modules.commons.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: 文件夹管理
 * @Author: jeecg-boot
 * @Date:   2022-02-19
 * @Version: V1.0
 */
@Data
@TableName("oss_file_folder")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="oss_file_folder对象", description="文件夹管理")
public class FileFolder extends BaseEntity implements Serializable {

	/**主键id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键id")
	private String id;
	@ApiModelProperty(value = "多租户")
	private Integer tenantId;
	/**文件夹名称*/
	@Excel(name = "文件夹名称", width = 15)
    @ApiModelProperty(value = "文件夹名称")
	private String folderName;
	/**文件夹编码*/
	@Excel(name = "文件夹编码", width = 15)
    @ApiModelProperty(value = "文件夹编码")
	private String folderCode;
	/**上级id*/
	@Excel(name = "上级id", width = 15)
    @ApiModelProperty(value = "上级id")
	private String pid;
	/**上级编码 暂时不用 当文件夹移动的时候容易出错*/
	@Excel(name = "上级编码", width = 15)
    @ApiModelProperty(value = "上级编码")
	private String pCode;
	/**文件夹路径*/
	@Excel(name = "文件夹路径", width = 15)
    @ApiModelProperty(value = "文件夹路径")
	private String folderUrl;
	/**关联业务数据id或者编码*/
	@Excel(name = "关联业务数据id或者编码", width = 15)
    @ApiModelProperty(value = "关联业务数据id或者编码")
	private String refCode;
	/**关联业务数据类型*/
	@Excel(name = "关联业务数据类型", width = 15)
    @ApiModelProperty(value = "关联业务数据类型")
	private String refType;

	@ApiModelProperty(value = "是否虚拟路径")
	private boolean izXuniPath;

	@ApiModelProperty(value = "人员ID")
	private String userId;

	@ApiModelProperty(value = "人员ID")
	private String projectId;

}
