package com.qfy.modules.smartform.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.smartform.entity.CheckItem;
import com.qfy.modules.smartform.entity.CheckItemChoice;
import com.qfy.modules.smartform.entity.CheckItemGroup;
import com.qfy.modules.smartform.entity.CheckItemNumber;
import com.qfy.modules.smartform.entity.CheckList;
import com.qfy.modules.smartform.entity.CheckTemplate;
import com.qfy.modules.smartform.entity.InspectionPoint;
import com.qfy.modules.smartform.entity.VoteFormReport;
import com.qfy.modules.smartform.service.ICheckItemChoiceService;
import com.qfy.modules.smartform.service.ICheckItemGroupService;
import com.qfy.modules.smartform.service.ICheckItemNumberService;
import com.qfy.modules.smartform.service.ICheckItemService;
import com.qfy.modules.smartform.service.ICheckListService;
import com.qfy.modules.smartform.service.ICheckTemplateService;
import com.qfy.modules.smartform.service.IInspectionPointService;
import com.qfy.modules.smartform.service.IVoteFormService;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.vo.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 投票类表单
 *
 * @author lanls
 * @version 1.0
 * @date 2022/5/13 9:59
 */
@RestController
@RequestMapping("/smartform/vote")
public class VoteFormController extends JeecgController<VoteFormReport, IVoteFormService> {

    public static final Logger logger = LoggerFactory.getLogger(VoteFormController.class);

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
    @Autowired
    private ICheckTemplateService templateService;

//    @GetMapping("/report")
//    public Result<IPage<CheckItem>> report(@RequestParam("templateId") String templateId,
//                            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
//                            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
//
//        QueryWrapper wrapper = new QueryWrapper();
//        wrapper.eq("template_id", templateId);
//        wrapper.orderByAsc("id");
//
//        List<CheckItemGroup> itemGroupList = groupService.list(wrapper);// 获取模板对应的容器 投票类表单，只有一个容器(表单项分组)
//        List<String> groupIds = itemGroupList.stream().map(CheckItemGroup::getId).collect(Collectors.toList());
//        wrapper.clear();
//        wrapper.in("chk_item_group_id", groupIds);
//        wrapper.orderByAsc("id");
//        Page<CheckItem> page = new Page<>(pageNo, pageSize);
//        IPage<CheckItem> pageList = itemService.page(page, wrapper);// 根据模板容器获取容器中全部填报表单项
//
//        List<CheckItem> checkItemList = pageList.getRecords();
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
//            // 容器/分组名称
//            for (CheckItemGroup group : itemGroupList){
//                if (StringUtils.equals(group.getId(),checkItem.getChkItemGroupId())){
//                    checkItem.setItemGroupName(group.getName());
//                }
//            }
//        }
//
//        List<Integer> itemIds = checkItemList.stream().map(CheckItem::getId).collect(Collectors.toList());
//        wrapper.clear();
//        wrapper.in("chkid", itemIds);
//        List<CheckItemChoice> choiceList = choiceService.list(wrapper);
//
//        wrapper.clear();
//        wrapper.in("chkid", itemIds);
//        List<CheckItemNumber> numberList = numberService.list(wrapper);
//
//        wrapper.clear();
//        wrapper.eq("template_id", templateId);
//        wrapper.orderByAsc("id");
//        List<InspectionPoint> pointList = pointService.list(wrapper);// 根据模板id获取投票结果明细
//        // 已提交投票结果
//        if (CollectionUtils.isNotEmpty(pointList)){
//            List<Integer> pointIds = pointList.stream().map(InspectionPoint::getId).collect(Collectors.toList());
//            wrapper.clear();
//            wrapper.in("inspec_poi_id", pointIds);
//            wrapper.in("chkid", itemIds);
//            List<CheckList> checkLists = checkListService.list(wrapper);
//            for (CheckItem checkItem : checkItemList) {
//                // 获取表单项的选项列表
//                List<CheckItemChoice> choices = choiceList.stream().filter(
//                        choice->choice.getChkid().intValue() == checkItem.getId().intValue())
//                                                                    .collect(Collectors.toList());
//
//                for (CheckItemChoice itemChoice : choices) {
//                    // 获取表单项的选项结果
//                    List<CheckList> lists = checkLists.stream()
//                            .filter(checkList -> checkList.getChkid().intValue() == itemChoice.getChkid().intValue() &&
//                                    StringUtils.equals(itemChoice.getLabel(), checkList.getResults()))
//                                                        .collect(Collectors.toList());
//                    itemChoice.setVoteCount(CollectionUtils.isNotEmpty(lists) ? lists.size() : 0);// 统计选项结果数量,即投票次数
//                }
//                checkItem.setChoiceList(choices);
//            }
//        } else {
//
//            for (CheckItem checkItem : checkItemList) {
//                // 获取表单项的选项列表
//                List<CheckItemChoice> choices = choiceList.stream()
//                                            .filter(choice->choice.getChkid().intValue() == checkItem.getId().intValue())
//                                                            .collect(Collectors.toList());
//                checkItem.setChoiceList(choices);
//            }
//        }
//        return Result.OK(pageList);
//    }

//    /**
//     * 导出excel
//     *
//     * @param templateId
//     */
//    @RequestMapping(value = "/exportXls")
//    public void exportXls(@RequestParam("templateId") String templateId,
//                                  HttpServletResponse response) {
//
//        CheckTemplate template = templateService.getById(templateId);
//
//        QueryWrapper<CheckItemGroup> wrapper = new QueryWrapper<>();
//        wrapper.lambda().eq(CheckItemGroup::getTemplateId, templateId);
//        List<CheckItemGroup> groupList = groupService.list(wrapper);
//        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//        Result result = report(templateId, 1, 10000);
//        IPage<CheckItem> pageList = (IPage<CheckItem>)result.getResult();
//        List<CheckItem> itemList = pageList.getRecords();
//        if (CollectionUtils.isNotEmpty(itemList)){
//            // 表头列名
//            List<ExcelExportEntity> columns = new ArrayList<>();
//            // 默认第二列为姓名,第三列为职务
//            columns.add(new ExcelExportEntity("序号", "sort", 10));
//            columns.add(new ExcelExportEntity("姓名", "name", 15));
//            columns.add(new ExcelExportEntity("职务", "post", 15));
//            columns.add(new ExcelExportEntity("分组", "group", 15));
//
//            List<CheckItemChoice> choiceList = itemList.get(0).getChoiceList();
//            Integer length = choiceList.size();
//            // 添加动态列
//            for (int i = 0; i < length; i++) {
//                columns.add(new ExcelExportEntity(choiceList.get(i).getLabel(), "choice" + i, 15));
//            }
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
//                List<CheckItemChoice> choices = item.getChoiceList();
//                for (int j = 0; j < length; j++) {
//                    data.put("choice" + j, choices.get(j).getVoteCount()); // 选票数量
//                }
//                dataSet.add(data);
//                sort ++;
//            }
//
//            ExportParams params = new ExportParams();
//            params.setTitle(template.getName());
//            params.setSecondTitle("导出人:" + sysUser.getRealname());
//            params.setSheetName(template.getName());
//            final Workbook workbook = ExcelExportUtil.exportExcel(params, columns, dataSet);
//            try {
//                response.setCharacterEncoding("UTF-8");
//                response.setHeader("content-Type", "application/vnd.ms-excel");
//                response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(template.getName(), "UTF-8"));
//                ServletOutputStream out = response.getOutputStream();
//                workbook.write(out);
//                out.flush();
//            } catch (IOException e) {
//                logger.error("导出报表错误：", e);
//            }
//        }
//    }
}
