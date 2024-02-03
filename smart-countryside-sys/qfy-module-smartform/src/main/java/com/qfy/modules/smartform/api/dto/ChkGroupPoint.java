/**
 * Copyright (C), 2016-2019,壮志山人
 * FileName: ChkGroupPoint
 * Author:   Administrator
 * Date:     2019/9/22 10:57
 * Description:
 * History:
 * <author>          <time>                <version>       <desc>
 * 马强              2019/9/22 10:57          版本号           描述
 */
package com.qfy.modules.smartform.api.dto;

import com.qfy.modules.smartform.entity.CheckItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * 巡检项分组（表单容器）
 * ChkGroupPoint
 * FileName: ChkGroupPoint
 * Author:   Administrator
 * Date:     2019/9/22 10:57
 * Description:
 */
@Data
public class ChkGroupPoint implements Serializable {

    private static final long serialVersionUID = 2049605505611226289L;

    private Integer id;
    private String name;
    private List<CheckItem> checkItemList = new ArrayList<CheckItem>();
    /**选项值LIST*/
    private List<CheckListDto> checkListDtoList;

//    public List<CheckItem> getCheckItemList() {
//        return checkItemList;
//    }
//    public void setCheckItemList(List<CheckItem> checkItemList) {
//        this.checkItemList = checkItemList;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<CheckListDto> getCheckListDtoList() {
//        return checkListDtoList;
//    }
//
//    public void setCheckListDtoList(List<CheckListDto> checkListDtoList) {
//        this.checkListDtoList = checkListDtoList;
//    }
}
