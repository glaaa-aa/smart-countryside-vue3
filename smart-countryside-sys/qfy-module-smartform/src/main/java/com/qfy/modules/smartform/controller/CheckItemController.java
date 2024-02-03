package com.qfy.modules.smartform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.smartform.entity.CheckItem;
import com.qfy.modules.smartform.entity.CheckItemChoice;
import com.qfy.modules.smartform.entity.CheckItemNumber;
import com.qfy.modules.smartform.service.ICheckItemChoiceService;
import com.qfy.modules.smartform.service.ICheckItemNumberService;
import com.qfy.modules.smartform.service.ICheckItemService;
import com.qfy.modules.smartform.utils.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: 检查项
 * @Author: jeecg-boot
 * @Date:   2022-04-08
 * @Version: V1.0
 */
@Api(tags="检查项")
@RestController
@RequestMapping("/smartform/checkItem")
@Slf4j
public class CheckItemController extends JeecgController<CheckItem, ICheckItemService> {
	@Resource
	private ICheckItemService checkItemService;

	@Resource
	private ICheckItemChoiceService choiceService;

	@Resource
	private ICheckItemNumberService numberService;

	@Value("${custom.config.tpl-id}")
	private String tplId;

	/**
	 * 分页列表查询
	 *
	 * @param checkItem
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "检查项-分页列表查询")
	@ApiOperation(value="检查项-分页列表查询", notes="检查项-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CheckItem checkItem,
								   @RequestParam(name = "tplId", required = false) String tplId,
								   @RequestParam(name = "pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name = "pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CheckItem> queryWrapper = QueryGenerator.initQueryWrapper(checkItem, req.getParameterMap());
		if (StringUtils.isNotEmpty(tplId)){
			queryWrapper.inSql("chk_item_group_id",
					"select id from qfy_check_item_group WHERE template_id = '" + tplId + "'");
		} else {
			queryWrapper.inSql("chk_item_group_id",
					"select id from qfy_check_item_group WHERE template_id = '" + this.tplId + "'");
		}
		Page<CheckItem> page = new Page<CheckItem>(pageNo, pageSize);
		IPage<CheckItem> pageList = checkItemService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

//	/**
//	 *   添加
//	 *
//	 * @param checkItem
//	 * @return
//	 */
//	@AutoLog(value = "检查项-添加")
//	@ApiOperation(value="检查项-添加", notes="检查项-添加")
//	@PostMapping(value = "/add")
//	public Result<?> add(@RequestBody CheckItem checkItem) {
//		// 新增时，设置默认值
//		if (null == checkItem.getId()){
//			checkItem.setChkmethod("other");
//			checkItem.setHelp("");
//			checkItem.setItemtype("other");
//			checkItem.setBgrade(0);
//		}
//
//		checkItemService.saveOrUpdate(checkItem);
//		List<CheckItemChoice> choiceList = checkItem.getChoiceList();
//		if (CollectionUtils.isNotEmpty(choiceList)){
//			int size = choiceList.size();
//			for (int i = 0; i < size; i++) {
//				CheckItemChoice choice = choiceList.get(i);
//				choice.setChkid(checkItem.getId());
//				choiceService.saveOrUpdate(choice);
//			}
//		}
//
//		if (null != checkItem.getCheckItemNumber()){
//			CheckItemNumber number = checkItem.getCheckItemNumber();
//			number.setChkid(checkItem.getId());
//			number.setItemName(checkItem.getName());
//			numberService.saveOrUpdate(number);
//		}
//		return Result.OK("添加成功！");
//	}

	/**
	 *  编辑
	 *
	 * @param checkItem
	 * @return
	 */
	@AutoLog(value = "检查项-编辑")
	@ApiOperation(value="检查项-编辑", notes="检查项-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CheckItem checkItem) {
		checkItemService.updateById(checkItem);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "检查项-通过id删除")
	@ApiOperation(value="检查项-通过id删除", notes="检查项-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		checkItemService.removeById(id);
		QueryWrapper<CheckItemChoice> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(CheckItemChoice::getChkid, id);
		choiceService.remove(wrapper);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "检查项-批量删除")
	@ApiOperation(value="检查项-批量删除", notes="检查项-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.checkItemService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "检查项-通过id查询")
	@ApiOperation(value="检查项-通过id查询", notes="检查项-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CheckItem checkItem = checkItemService.getById(id);
		if(checkItem==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(checkItem);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param checkItem
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CheckItem checkItem) {
        return super.exportXls(request, checkItem, CheckItem.class, "检查项");
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
        return super.importExcel(request, response, CheckItem.class);
    }

//	/**
//	 * 通过excel导入数据
//	 *
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/importVote", method = RequestMethod.POST)
//	public Result<?> importVote(HttpServletRequest request) {
//
//		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//		Result<List<CheckItem>> result = new Result<>();
//		String formType = request.getParameter("formtype");
//		String groupId = request.getParameter("groupId");
//		MultipartFile file = null;
//		try {
//			List<CheckItem> checkItemList = new ArrayList<>();
//			for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
//				long start = System.currentTimeMillis();
//				file = entity.getValue();// 获取上传文件对象
//				String fileName = file.getOriginalFilename();
//				List<String[]> cells = ExcelUtil.parseExcel(file.getInputStream(),
//						fileName.substring(fileName.lastIndexOf(".") + 1),0);
//				if (CollectionUtils.isNotEmpty(cells)){
//					// 数字类矩阵滑动条
//					if (StringUtils.equals("matrix", formType)){
//						handleMatrix(checkItemList,formType,groupId, cells);
//					} else {
//						handleChoice(checkItemList, formType, groupId, cells);
//					}
//				}
//
//				log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
//				result.setResult(checkItemList);
//				result.success("文件导入成功！数据行数：" + checkItemList.size());
//				return result;
//			}
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			result.error500("文件导入失败:" + e.getMessage());
//		} finally {
//			try {
//				if (null != file){
//					file.getInputStream().close();
//				}
//			} catch (IOException e) {
//				log.error("文件关闭异常：", e);
//			}
//		}
//		return result;
//	}

//	/**
//	 * 单选
//	 * @param checkItemList
//	 * @param formType
//	 * @param groupId
//	 * @param cells
//	 */
//	private void handleChoice(List<CheckItem> checkItemList,String formType,
//								String groupId, List<String[]> cells){
//		String[] headers = cells.get(0);// 将第一行第二列起的表头作为选项的列表
//		String[] choices = Arrays.copyOfRange(headers, 1, headers.length);
//		int length = choices.length;
//		List<CheckItemChoice> itemChoiceList = new ArrayList<>(length);// 测评表、投票类(单选、多选)
//		// 生成表单项,从第二行开始
//		cells.remove(0);
//		for (String[] cell : cells) {
//			String name = cell[0];
//			CheckItem item = new CheckItem();
//			item.setItemtype("other");
//			item.setHelp("");
//			item.setChkmethod("other");
//			item.setBrequired(1);// 必填
//			item.setFormtype(formType);
//			item.setDefaultVal(choices[0]);
//			item.setName(name);
//			item.setBgrade(0);
//			item.setChkItemGroupId(groupId);
//			checkItemService.save(item);
//			checkItemList.add(item);
//			itemChoiceList.clear();
//			//生成单选、多选表单项
//			for (int i = 0; i < length; i++) {
//				CheckItemChoice choice = new CheckItemChoice();
//				choice.setIsdefault(i == 0 ? 1 : 0);
//				choice.setChkid(item.getId());
//				choice.setIsvalid(1);
//				choice.setLabel(choices[i]);
//				choice.setScore(i + 1d);
//				choice.setPriority(i + 1);
//				itemChoiceList.add(choice);
//			}
//			choiceService.saveBatch(itemChoiceList);
//		}
//	}

//	/**
//	 * 数字矩阵滑动条
//	 * @param checkItemList
//	 * @param formType
//	 * @param groupId
//	 * @param cells
//	 */
//	private void handleMatrix(List<CheckItem> checkItemList, String formType, String groupId, List<String[]> cells){
//		String[] headers = cells.get(0);// 将第一行第二列起的表头作为选项的列表
//		String[] choices = Arrays.copyOfRange(headers, 1, headers.length);
//		int length = choices.length;
//		List<CheckItemNumber> matrixList = new ArrayList<>(length); // 评分表，数字类矩阵滑动条
//		// 生成表单项,从第二行开始
//		cells.remove(0);
//		for (String[] cell : cells) {
//			String name = cell[0];
//			CheckItem item = new CheckItem();
//			item.setItemtype("other");
//			item.setHelp("");
//			item.setChkmethod("other");
//			item.setBrequired(1);// 必填
//			item.setFormtype(formType);
//			item.setDefaultVal(name);
//			item.setName(name);
//			item.setBgrade(0);
//			item.setChkItemGroupId(groupId);
//			checkItemService.save(item);
//			checkItemList.add(item);
//			matrixList.clear();
//			//生成单选、多选表单项
//			for (int i = 0; i < length; i++) {
//				CheckItemNumber number = new CheckItemNumber();
//				String[] titles = choices[i].split("@");
//				number.setItemName(titles[0]);
//				number.setChkid(item.getId());
//				number.setDeccount(BigDecimal.valueOf(1));
//				number.setLlvalidnumb(0d);
//				number.setUlvalidnumb(Double.parseDouble(titles[1]));
//				number.setLlqualified(0d);
//				number.setUlqualified(Double.parseDouble(titles[1]));
//				matrixList.add(number);
//			}
//			numberService.saveBatch(matrixList);
//		}
//	}

}
