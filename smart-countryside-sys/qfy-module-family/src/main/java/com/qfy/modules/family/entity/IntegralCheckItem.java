package com.qfy.modules.family.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 积分项
 * @author lanls
 * @version v1.0.0
 * @since 2023/8/30 10:58
 */
@Data
public class IntegralCheckItem implements Serializable {

    private static final long serialVersionUID = -5242924191819324036L;

    private Integer id;
    private String name;
    private Double value;
    private String title;
    private String text;
}
