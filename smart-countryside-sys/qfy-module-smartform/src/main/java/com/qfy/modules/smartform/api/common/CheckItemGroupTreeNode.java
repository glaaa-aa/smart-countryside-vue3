package com.qfy.modules.smartform.api.common;

import com.qfy.modules.smartform.entity.CheckItem;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * 检查项分组的树形结构
 */
@Data
public class CheckItemGroupTreeNode {

    private String id;
    private String name;
    private String parentid;
    private Integer sort;
    private List<CheckItemGroupTreeNode> children = new ArrayList<>();
    private List<CheckItem> checkItemList = new ArrayList<>();
}
