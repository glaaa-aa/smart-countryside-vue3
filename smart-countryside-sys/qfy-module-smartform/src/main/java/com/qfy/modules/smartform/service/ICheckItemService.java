package com.qfy.modules.smartform.service;

import com.qfy.modules.smartform.entity.CheckItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 模板项
 * @Author: jeecg-boot
 * @Date:   2024-01-23
 * @Version: V1.0
 */
public interface ICheckItemService extends IService<CheckItem> {

    public  List<CheckItem> getCheckItemList(String surveyId);


    public void del1CheckItem(String quId);



    public  CheckItem addFillblank(CheckItem checkItem);




    public  CheckItem addPagetag(CheckItem checkItem);


    public  CheckItem addParagraph(CheckItem checkItem);

    public  CheckItem addUploadFile(CheckItem checkItem);

    public  CheckItem addDigit(CheckItem checkItem);




//    public List<CheckItem> findDetails(List<CheckItem> questions);

}
