package com.qfy.modules.api.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * 行政区划、组织机构实体对象
 *
 * @author lanls
 * @version 1.0
 * @date 2023/11/20 9:28
 */
@Data
public class AdminDivision implements java.io.Serializable{
    private static final long serialVersionUID = -4256708170011194545L;
    private String id;
    private String name;
    private String code;
    private String pcode;
    private List<AdminDivision> children = new ArrayList<>();
}
