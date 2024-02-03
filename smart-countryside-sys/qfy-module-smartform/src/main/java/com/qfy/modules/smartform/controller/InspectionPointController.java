package com.qfy.modules.smartform.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.smartform.entity.CheckItem;
import com.qfy.modules.smartform.entity.CheckItemChoice;
import com.qfy.modules.smartform.entity.CheckItemGroup;
import com.qfy.modules.smartform.entity.CheckList;
import com.qfy.modules.smartform.entity.InspectionPoint;
import com.qfy.modules.smartform.service.ICheckItemChoiceService;
import com.qfy.modules.smartform.service.ICheckItemGroupService;
import com.qfy.modules.smartform.service.ICheckItemService;
import com.qfy.modules.smartform.service.ICheckListService;
import com.qfy.modules.smartform.service.ICheckTemplateService;
import com.qfy.modules.smartform.service.IInspectionPointService;
import com.qfy.modules.smartform.vo.VoteVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

 /**
 * @Description: 巡检结果详情
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Api(tags="巡检结果详情")
@RestController
@RequestMapping("/smartform/inspectionPoint")
@Slf4j
public class InspectionPointController extends JeecgController<InspectionPoint, IInspectionPointService> {

	@Autowired
	private ICheckTemplateService checkTemplateService;

	@Autowired
	private IInspectionPointService inspectionPointService;

	@Autowired
	private ICheckItemGroupService checkItemGroupService;

	@Autowired
	private ICheckListService checkListService;

	@Autowired
	private ICheckItemService checkItemService;

	@Autowired
	private ICheckItemChoiceService itemChoiceService;

	/**
	 * 分页列表查询
	 *
	 * @param inspectionPoint
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "巡检结果详情-分页列表查询")
	@ApiOperation(value="巡检结果详情-分页列表查询", notes="巡检结果详情-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(InspectionPoint inspectionPoint,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<InspectionPoint> queryWrapper = QueryGenerator.initQueryWrapper(inspectionPoint, req.getParameterMap());
		Page<InspectionPoint> page = new Page<InspectionPoint>(pageNo, pageSize);
		IPage<InspectionPoint> pageList = inspectionPointService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

//	 /**
//	  * 分页列表查询
//	  *
//	  * @param templateId
//	  * @param pageNo
//	  * @param pageSize
//	  * @return
//	  */
//	 @AutoLog(value = "巡检结果详情-分页列表查询")
//	 @ApiOperation(value="巡检结果详情-分页列表查询", notes="巡检结果详情-分页列表查询")
//	 @GetMapping(value = "/report/list")
//	 public Result<?> pageList(@RequestParam(name = "templateId") String templateId,
//									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
//		 // 获取模板
////		 CheckTemplate template = checkTemplateService.getById(templateId);
//
//		 QueryWrapper wrapper = new QueryWrapper<>();
//		 wrapper.eq("template_id", templateId);
//		 wrapper.orderByAsc("id");
//		 // 查询该模板下的全部表单填报结果明细列表
//		 List<InspectionPoint> pointList = inspectionPointService.list(wrapper);
//		 if (CollectionUtils.isNotEmpty(pointList)){
//
//			// 查询模板下的全部表单项分组（容器）
//			List<CheckItemGroup> itemGroupList = checkItemGroupService.list(wrapper);
//			// 获取分组id集合
//			List<String> groupIds = itemGroupList.stream().map(CheckItemGroup::getId).collect(Collectors.toList());
//			wrapper.clear();
//			wrapper.in("chk_item_group_id", groupIds);
//			wrapper.orderByAsc("id");
//			// 分页查询该模板下的全部表单项
//			Page<CheckItem> page = new Page<CheckItem>(pageNo, pageSize);
//			IPage<CheckItem> pageList = checkItemService.page(page, wrapper);
//			List<CheckItem> checkItemList = pageList.getRecords();
//
//			List<Integer> itemIds = checkItemList.stream().map(CheckItem::getId).collect(Collectors.toList());
//			wrapper.clear();
//			wrapper.in("chkid", itemIds);
//			List<CheckItemChoice> checkItemChoices = itemChoiceService.list(wrapper);
//
//			List<VoteVO> records = new ArrayList<>();
//			for (InspectionPoint point : pointList) {
//				// 获取该模板的表单填报结果
//				wrapper.clear();
//				wrapper.eq("inspec_poi_id", point.getId());
//				// 查询全部的表单填报结果
//				List<CheckList> checkLists = checkListService.list(wrapper);
//				checkItemList.stream().forEach(item->{
//					VoteVO vote = new VoteVO();
//					BeanUtil.copyProperties(item, vote, false);
//					vote.setInspectPoiId(point.getId());
//					List<CheckItemChoice> choiceList = checkItemChoices.stream()
//														.filter(choice->choice.getChkid() == item.getId())
//														.collect(Collectors.toList());
//
//					if (CollectionUtils.isNotEmpty(choiceList)){
//						vote.setChoiceList(choiceList);
//						for (CheckItemChoice choice : choiceList) {
//							for (CheckList checkList : checkLists) {
//								if (choice.getChkid().intValue() == checkList.getChkid().intValue()){
//									choice.setIsdefault(1);// 设置默认值
//								}
//							}
//						}
//					}
//					records.add(vote);
//				});
//			}
//			pageList.getRecords().clear();
//			pageList.getRecords().addAll(records);
//			return Result.OK(pageList);
//		 } else {
//			 return Result.OK();
//		 }
//	 }

	/**
	 *   添加
	 *
	 * @param inspectionPoint
	 * @return
	 */
	@AutoLog(value = "巡检结果详情-添加")
	@ApiOperation(value="巡检结果详情-添加", notes="巡检结果详情-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody InspectionPoint inspectionPoint) {
		inspectionPointService.save(inspectionPoint);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param inspectionPoint
	 * @return
	 */
	@AutoLog(value = "巡检结果详情-编辑")
	@ApiOperation(value="巡检结果详情-编辑", notes="巡检结果详情-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody InspectionPoint inspectionPoint) {
		inspectionPointService.updateById(inspectionPoint);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "巡检结果详情-通过id删除")
	@ApiOperation(value="巡检结果详情-通过id删除", notes="巡检结果详情-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		inspectionPointService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "巡检结果详情-批量删除")
	@ApiOperation(value="巡检结果详情-批量删除", notes="巡检结果详情-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.inspectionPointService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "巡检结果详情-通过id查询")
	@ApiOperation(value="巡检结果详情-通过id查询", notes="巡检结果详情-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		InspectionPoint inspectionPoint = inspectionPointService.getById(id);
		if(inspectionPoint==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(inspectionPoint);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param inspectionPoint
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, InspectionPoint inspectionPoint) {
        return super.exportXls(request, inspectionPoint, InspectionPoint.class, "巡检结果详情");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, InspectionPoint.class);
    }

}
