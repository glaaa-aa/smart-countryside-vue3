package com.qfy.modules.smartform.api.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class CheckListDto implements Serializable {

    private static final long serialVersionUID = 2653155835863028297L;
    /**表单*/
    private Integer chkid;
    /**检查项名称*/
    private String itemName;
    /**检查结果*/
    private String results;
    /**类型*/
    private String type;
    /**是否合格*/
    private Integer isvalid;
    /**检查得分*/
    private Double score;
    /**备注*/
    private String remark;
    /**图片集合*/
    private String attachId;
}
