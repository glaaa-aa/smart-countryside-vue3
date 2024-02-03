package com.qfy.modules.smartform.entity;

import java.io.Serializable;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * TODO
 *
 * @author lanli
 * @version 1.0
 * @date 2022/5/8 10:23
 */
@Data
public class VoteCheckItem implements Serializable {

    private static final long serialVersionUID = 5978925308791678641L;

    @Excel(name = "姓名", width = 15)
    private String name;


}
