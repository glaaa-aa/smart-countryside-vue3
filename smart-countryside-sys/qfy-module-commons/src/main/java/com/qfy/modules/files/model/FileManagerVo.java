package com.qfy.modules.files.model;

import com.baomidou.mybatisplus.annotation.TableField;
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
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="oss_files_manager对象", description="上传文件管理")
public class FileManagerVo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
    @ApiModelProperty(value = "主键")
    private String id; //可能是文件id 可能时间文件夹id
    /**是否为文件夹*/
    private boolean izFolder; //true：是


	/**文件图标*/
	@Excel(name = "文件图标", width = 15)
    @ApiModelProperty(value = "文件图标")
    private String icon;
    /**文件名*/
    @Excel(name = "文件旧名称", width = 15)
    @ApiModelProperty(value = "文件旧名称")
    private String fileName;
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
    /**上级id*/
    @Excel(name = "上级id", width = 15)
    @ApiModelProperty(value = "上级id")
    private String pid;
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

    //用于显示
    @TableField(exist = false)
    private String filePath;

    public String getFilePath() {
        return furl;
    }


}
