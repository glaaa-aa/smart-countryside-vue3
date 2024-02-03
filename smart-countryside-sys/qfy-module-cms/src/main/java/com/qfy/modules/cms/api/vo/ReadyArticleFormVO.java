package com.qfy.modules.cms.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * TODO
 *
 * @author lanls
 * @version 1.0
 * @date 2021/11/17 15:46
 */
@Data
@ApiModel(value = "文章阅读表单实体")
public class ReadyArticleFormVO {

    @ApiModelProperty(value = "文章id", required = true)
    private Integer artId;// 文章id
    @ApiModelProperty(value = "操作类型 1：开始学习，2：暂停学习，3：完成学习，4：客户端记录开始学习到结束的全部时间", dataType = "Integer", required = true)
    private Integer optType;
    @ApiModelProperty(value = "阅读时长")
    private Long duration; // 阅读时长
    @ApiModelProperty(value = "开始阅读时间 yyyy-MM-dd HH:mm, optType=4时必填")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private String startTime; // 开始阅读时间
    @ApiModelProperty(value = "结束阅读时间 yyyy-MM-dd HH:mm, optType=4时必填")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private String endTime; // 结束阅读时间
}
