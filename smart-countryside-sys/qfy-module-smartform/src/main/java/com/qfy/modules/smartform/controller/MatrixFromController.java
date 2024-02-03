package com.qfy.modules.smartform.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.smartform.entity.CheckItem;
import com.qfy.modules.smartform.entity.CheckItemGroup;
import com.qfy.modules.smartform.entity.CheckItemNumber;
import com.qfy.modules.smartform.entity.CheckList;
import com.qfy.modules.smartform.entity.InspectionPoint;
import com.qfy.modules.smartform.service.ICheckItemChoiceService;
import com.qfy.modules.smartform.service.ICheckItemGroupService;
import com.qfy.modules.smartform.service.ICheckItemNumberService;
import com.qfy.modules.smartform.service.ICheckItemService;
import com.qfy.modules.smartform.service.ICheckListService;
import com.qfy.modules.smartform.service.IInspectionPointService;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 矩阵滑动类
 *
 * @author lanls
 * @version 1.0
 * @date 2022/5/17 21:57
 */
@RestController
@RequestMapping("/smartform/matrix")
public class MatrixFromController {

    public static final Logger logger = LoggerFactory.getLogger(MatrixFromController.class);

    @Autowired
    private ISysBaseAPI sysBaseAPI;

    @Autowired
    private IInspectionPointService pointService;

    @Autowired
    private ICheckItemGroupService groupService;

    @Autowired
    private ICheckItemService itemService;

    @Autowired
    private ICheckItemChoiceService choiceService;

    @Autowired
    private ICheckItemNumberService numberService;

    @Autowired
    private ICheckListService checkListService;

//    @GetMapping("/report")
//    public Result<IPage<CheckItem>> report(@RequestParam("templateId") String templateId,
//                                           @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
//                                           @RequestParam(name = "pageSize", defaultValue = "1000") Integer pageSize){
//
//        QueryWrapper wrapper = new QueryWrapper();
//        wrapper.eq("template_id", templateId);
//        wrapper.orderByAsc("id");
//        List<CheckItemGroup> itemGroupList = groupService.list(wrapper);// 获取模板对应的容器 投票类表单，只有一个容器(表单项分组)
//        List<String> groupIds = itemGroupList.stream().map(CheckItemGroup::getId).collect(Collectors.toList());
//        wrapper.clear();
//        wrapper.in("chk_item_group_id", groupIds);
//        wrapper.orderByAsc("id");
//        wrapper.last("limit 1000");
//        Page<CheckItem> page = new Page<>(pageNo, pageSize);
////        IPage<CheckItem> pageList = itemService.page(page, wrapper);// 根据模板容器获取容器中全部填报表单项
//
//        List<CheckItem> checkItemList = itemService.list(wrapper);//pageList.getRecords();
//        page.setRecords(checkItemList);
//
//        // 提取姓名，通过真实姓名查找用户的职务
//        List<String> itemNames = checkItemList.stream().map(CheckItem::getName).collect(Collectors.toList());
//        List<LoginUser> userList = sysBaseAPI.queryUsersByRealname(itemNames.toArray(new String[itemNames.size()]));
//
//        for (CheckItem checkItem : checkItemList) {
//            // 人员职务
//            for (LoginUser loginUser : userList) {
//                if (StringUtils.equals(checkItem.getName(), loginUser.getRealname())){
//                    checkItem.setHelp(loginUser.getPost());
//                }
//            }
//
//            // 容器/分组名称
//            for (CheckItemGroup group : itemGroupList){
//                if (StringUtils.equals(group.getId(),checkItem.getChkItemGroupId())){
//                    checkItem.setItemGroupName(group.getName());
//                }
//            }
//        }
//
//        List<Integer> itemIds = checkItemList.stream().map(CheckItem::getId).collect(Collectors.toList());
//
//        wrapper.clear();
//        wrapper.in("chkid", itemIds);
//        List<CheckItemNumber> numberList = numberService.list(wrapper);
//
//        wrapper.clear();
//        wrapper.eq("template_id", templateId);
//        wrapper.orderByAsc("id");
//        List<InspectionPoint> pointList = pointService.list(wrapper);// 根据模板id获取投票结果明细
//        // 已提交投票结果,由于数据量太大，分页显示会特别多
//        if (CollectionUtils.isNotEmpty(pointList)){
//            int pages = pointList.size();
//            pageSize = checkItemList.size();
//            // 设置分页数
//            page.setTotal(pages * pageSize);
//            page.setSize(pageSize);
//
////            List<Integer> pointIds = pointList.stream().map(InspectionPoint::getId).collect(Collectors.toList());
//            Integer pointId = pointList.get(pageNo - 1).getId();// 每页显示一次评分结果
//            wrapper.clear();
//            wrapper.in("inspec_poi_id", pointId);// 默认只获取一次评分的数据
//            wrapper.in("chkid", itemIds);
//            List<CheckList> checkLists = checkListService.list(wrapper);
//            for (CheckItem checkItem : checkItemList) {
//                List<CheckList> list = checkLists.stream()
//                        .filter(checklist -> checklist.getChkid().intValue() == checkItem.getId().intValue() &&
//                                checklist.getInspecPoiId().intValue() == pointId.intValue())
//                        .collect(Collectors.toList());
//                List<CheckItemNumber> numbers = numberList.stream().filter(number ->
//                                                        number.getChkid().intValue() == checkItem.getId().intValue())
//                                                            .collect(Collectors.toList());
//                for (CheckList checkList : list) {
//                    for (CheckItemNumber number : numbers) {
//                        if (checkList.getChkid().intValue() == number.getChkid().intValue() &&
//                                StringUtils.equals(checkList.getItemName(),number.getItemName())){
//                            number.setCheckValue(checkList.getResults());
//                        }
//                    }
//                }
//                checkItem.setMatrixList(numbers);
//            }
//        } else {
//            for (CheckItem checkItem : checkItemList) {
//                List<CheckItemNumber> numbers = numberList.stream().filter(number ->
//                                                            number.getChkid().intValue() == checkItem.getId().intValue())
//                                                                    .collect(Collectors.toList());
//                for (CheckItemNumber number : numbers) {
//                    number.setCheckValue("0");
//                }
//                checkItem.setMatrixList(numbers);
//            }
//        }
//        return Result.OK(page);
//    }

//    /**
//     * 导出excel
//     *
//     * @param templateId
//     */
//    @RequestMapping(value = "/exportXls")
//    public void exportXls(@RequestParam("templateId") String templateId,
//                          HttpServletResponse response) {
//
//        QueryWrapper<CheckItemGroup> wrapper = new QueryWrapper<>();
//        wrapper.lambda().eq(CheckItemGroup::getTemplateId, templateId);
//        List<CheckItemGroup> groupList = groupService.list(wrapper);
//
//        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//        List<CheckItem> itemList = queryCheckItems(templateId);
//        if (CollectionUtils.isNotEmpty(itemList)){
//            // 表头列名
//            List<ExcelExportEntity> columns = new ArrayList<>();
//            // 默认第二列为姓名,第三列为职务
//            columns.add(new ExcelExportEntity("序号", "sort", 10));
//            columns.add(new ExcelExportEntity("姓名", "name", 15));
//            columns.add(new ExcelExportEntity("职务", "post", 15));
//            columns.add(new ExcelExportEntity("分组", "group", 15));
//            List<CheckItemNumber> matrixList = itemList.get(0).getMatrixList();
//            Integer length = matrixList.size();
//            // 添加动态列
//            for (int i = 0; i < length; i++) {
//                columns.add(new ExcelExportEntity(matrixList.get(i).getItemName(), "item" + i, 15));
//            }
//            columns.add(new ExcelExportEntity("总分", "total", 15));
//
//            // 添加数据
//            List<Map<String,Object>> dataSet = new ArrayList<>(itemList.size());
//            int sort = 1;
//            for (CheckItem item : itemList) {
//                Map<String,Object> data = new HashMap<>();
//                data.put("sort", sort);// 序号
//                data.put("name", item.getName()); // 姓名
//                data.put("post", item.getHelp()); // 职务
//                String groupName = groupList.stream().filter(itemGroup -> {
//                    return StringUtils.equals(itemGroup.getId(),item.getChkItemGroupId());
//                }).collect(Collectors.toList()).get(0).getName();
//                data.put("group", groupName);
//
//                List<CheckItemNumber> numberList = item.getMatrixList();
//                float total = 0f;
//                for (int j = 0; j < length; j++) {
//                    CheckItemNumber number = numberList.get(j);
//                    String value = StringUtils.isNotEmpty(number.getCheckValue()) ? number.getCheckValue() : "0";
//                    data.put("item" + j, value); // 选项评分
//                    total += Float.parseFloat(value);
//                }
//                data.put("total", total); // 单人次评分总分
//                dataSet.add(data);
//                sort ++;
//            }
//
//            ExportParams params = new ExportParams();
//            params.setTitle("季度考核评分明细表");
//            params.setSecondTitle("导出人:" + sysUser.getRealname());
//            params.setSheetName("季度考核评分明细");
//
//            List<Map<String,Object>> copyDataSet = Arrays.asList(new HashMap[dataSet.size()]);
//            Collections.copy(copyDataSet, dataSet);
//
//            Workbook workbook = ExcelExportUtil.exportExcel(params, columns, dataSet);// 第一个sheet 评分明细
//            createNextSheet(workbook,copyDataSet);// 第二个sheet 评分汇总
//            try {
//                response.setCharacterEncoding("UTF-8");
//                response.setHeader("content-Type", "application/vnd.ms-excel");
//                response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode("季度考核评分表汇总",
//                        "UTF-8"));
//                ServletOutputStream out = response.getOutputStream();
//                workbook.write(out);
//                out.flush();
//            } catch (IOException e) {
//                logger.error("导出报表错误：", e);
//            }
//        }
//    }

//    private List<CheckItem> queryCheckItems(String tplId){
//
//        QueryWrapper wrapper = new QueryWrapper();
//        wrapper.apply("chk_item_group_id in (select id from qfy_check_item_group where template_id = {0})", tplId);
//        wrapper.orderByAsc("id");
//        wrapper.last("limit 1000");
//        List<CheckItem> checkItemList = itemService.list(wrapper);
//
//        // 提取姓名，通过真实姓名查找用户的职务
//        List<String> itemNames = checkItemList.stream().map(CheckItem::getName).collect(Collectors.toList());
//        List<Integer> itemIds = checkItemList.stream().map(CheckItem::getId).collect(Collectors.toList());
//        List<LoginUser> userList = sysBaseAPI.queryUsersByRealname(itemNames.toArray(new String[itemNames.size()]));
//
//        for (CheckItem checkItem : checkItemList) {
//            for (LoginUser loginUser : userList) {
//                if (StringUtils.equals(checkItem.getName(), loginUser.getRealname())){
//                    checkItem.setHelp(loginUser.getPost());
//                }
//            }
//        }
//
//        wrapper.clear();
//        wrapper.in("chkid", itemIds);
//        List<CheckItemNumber> numberList = numberService.list(wrapper);
//
//        wrapper.clear();
//        wrapper.eq("template_id", tplId);
//        wrapper.orderByAsc("id");
//        List<InspectionPoint> pointList = pointService.list(wrapper);// 根据模板id获取投票结果明细
//
//        List<CheckItem> checkItems = new ArrayList<>();
//        List<String> items = new ArrayList<>();
//        // 已提交投票结果,由于数据量太大，分页显示会特别多
//        if (CollectionUtils.isNotEmpty(pointList)){
//
//            List<Integer> pointIds = pointList.stream().map(InspectionPoint::getId).collect(Collectors.toList());
//            wrapper.clear();
//            wrapper.in("inspec_poi_id", pointIds);// 默认只获取一次评分的数据
//            wrapper.in("chkid", itemIds);
//            List<CheckList> checkLists = checkListService.list(wrapper);
//            for (Integer pointId : pointIds) {
//                for (CheckItem checkItem : checkItemList) {
//                    CheckItem item = new CheckItem();
//                    item.setHelp(checkItem.getHelp());
//                    item.setId(checkItem.getId());
//                    item.setName(checkItem.getName());
//                    item.setChkItemGroupId(checkItem.getChkItemGroupId());
////                    Integer chkId = checkItem.getId();
//                    List<CheckList> list = checkLists.stream()
//                            .filter(checklist -> checklist.getChkid().intValue() == item.getId().intValue() &&
//                                    checklist.getInspecPoiId().intValue() == pointId.intValue())
//                            .collect(Collectors.toList());
//                    List<CheckItemNumber> numbers = numberList.stream().filter(
//                            number -> number.getChkid().intValue() == item.getId().intValue())
//                            .collect(Collectors.toList());
//                    for (CheckList checkList : list) {
//                        for (CheckItemNumber number : numbers) {
//                            if (checkList.getChkid().intValue() == number.getChkid().intValue() &&
//                                    StringUtils.equals(checkList.getItemName(),number.getItemName())){
//                                number.setCheckValue(checkList.getResults());
//                            }
//                        }
//                    }
//                    item.setMatrixList(numbers);
////                    logger.info("==>{}", JSONObject.toJSONString(item));
//                    /*
//                     * 此处代码出现奇怪的问题,不通直接使用List<checkItem> 来装载item,会导致数据出现重复,覆盖的问题.
//                     * 只能曲折处理,先把item转成JSON字符串,之后再将JSON字符串转化为CheckItem.
//                     */
////                    checkItems.add(item);
//                    items.add(JSONObject.toJSONString(item));
//                }
//            }
//            // 此处将前面JSON字符串再转成CheckItem
//            for (String json : items) {
//                CheckItem item = JSONObject.toJavaObject(JSON.parseObject(json),CheckItem.class);
//                checkItems.add(item);
//            }
//        } else {
//            for (CheckItem checkItem : checkItemList) {
//                List<CheckItemNumber> numbers = numberList.stream().filter(
//                        number -> number.getChkid().intValue() == checkItem.getId().intValue())
//                        .collect(Collectors.toList());
//                checkItem.setMatrixList(numbers);
//            }
//        }
//
//        return CollectionUtils.isNotEmpty(checkItems) ? checkItems : checkItemList;
//    }

    /**
     * 自定义第二个sheet 汇总
     * @param workbook
     * @param dataSet
     * @return: void
     * @author: lanls
     * @date: 2022/6/15 15:29
     * @description:
     */
    private void createNextSheet(Workbook workbook, List<Map<String, Object>> dataSet){

        // 表头列名
        List<ExcelExportEntity> columns = new ArrayList<>();
        columns.add(new ExcelExportEntity("姓名", "name", 15));
        columns.add(new ExcelExportEntity("职务", "post", 15));
        columns.add(new ExcelExportEntity("评分", "total", 15));

        ExportParams params = new ExportParams();
        params.setTitle("季度考核评分汇总");
        params.setSheetName("季度考核评分汇总");

        // 根据姓名进行分组
        Map<String,List<Map<String,Object>>> groupby =
                dataSet.stream().collect(Collectors.groupingBy(map->map.get("name").toString()));

        int size = groupby.size();
        // 计算分组平均分
        List<Map<String,Object>> newDataSet = new ArrayList<>(size);
        groupby.forEach((key,val)->{
            Double total = val.stream()
                            .collect(Collectors.averagingDouble(map->Double.parseDouble(map.get("total").toString())));
            Map<String,Object> data = new HashMap<>();
            data.put("name", val.get(0).get("name")); // 姓名
            data.put("post", val.get(0).get("post")); // 职务
            data.put("total", Double.parseDouble(String.format("%.2f",total))); // 总平均分
            newDataSet.add(data);
        });

        List<Map<String,Object>> sortedDataSet = newDataSet.stream()
                                                .sorted(Comparator.comparing(map->Double.parseDouble(map.get("total").toString())))
                                                .collect(Collectors.toList());
        Collections.reverse(sortedDataSet);
        (new ExcelExportService()).createSheetForMap(workbook, params,columns, sortedDataSet);// 第二个sheet 评分汇总

    }

}
