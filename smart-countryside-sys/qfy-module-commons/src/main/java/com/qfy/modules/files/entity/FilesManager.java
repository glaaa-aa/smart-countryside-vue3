package com.qfy.modules.files.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Description: 上传文件管理
 * @Author: jeecg-boot
 * @Date:   2022-03-01
 * @Version: V1.0
 */
@Data
@TableName("oss_files_manager")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="oss_files_manager对象", description="上传文件管理")
public class FilesManager extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "多租户")
    private Integer tenantId;
	/**部门编码*/
    @ApiModelProperty(value = "部门编码")
    private String sysOrgCode;
	/**文件图标*/
	@Excel(name = "文件图标", width = 15)
    @ApiModelProperty(value = "文件图标")
    private String icon;
    /**文件名*/
    @Excel(name = "文件旧名称", width = 15)
    @ApiModelProperty(value = "文件旧名称")
    private String oldName;
	/**文件名*/
	@Excel(name = "文件名", width = 15)
    @ApiModelProperty(value = "文件名")
    private String name;
	/**文件大小(KB)*/
	@Excel(name = "文件大小(KB)", width = 15)
    @ApiModelProperty(value = "文件大小(KB)")
    private Integer size;
	/**文件格式 dir:目录,doc:文档,excel：表格,pdf：PDF文档,image:图片，txt：文本文件*/
	@Excel(name = "文件格式", width = 15)
    @ApiModelProperty(value = "文件格式")
    private String format;
	/**文件缩略图*/
	@Excel(name = "文件缩略图", width = 15)
    @ApiModelProperty(value = "文件缩略图")
    private String thumbnail;
	/**文件路径*/
	@Excel(name = "文件路径", width = 15)
    @ApiModelProperty(value = "文件路径")
    private String fpath;
	/**文件访问路径*/
	@Excel(name = "文件访问路径", width = 15)
    @ApiModelProperty(value = "文件访问路径")
    private String furl;
	/**上级目录*/
	@Excel(name = "上级目录", width = 15)
    @ApiModelProperty(value = "上级目录")
    private String folderId;

    @ApiModelProperty(value = "人员ID")
    private String userId;

    @ApiModelProperty(value = "文件权限")
    private String fileAuth;

    //用于显示
    @TableField(exist = false)
    private String filePath;

    public String getFilePath() {
        return furl;
    }

}
