package com.qfy.modules.smartform.vo;

import com.qfy.modules.smartform.entity.CheckItem;
import java.io.Serializable;
import lombok.Data;

/**
 * 投票类-表单项实体
 *
 * @author lanli
 * @version 1.0
 * @date 2022/5/6 17:40
 */
@Data
public class VoteVO extends CheckItem implements Serializable {

    private static final long serialVersionUID = 9005980581457516567L;

    private Integer inspectPoiId;

    private String submitter;

    private String submitterPhone;
}
