package com.qfy.modules.smartform.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfy.modules.smartform.api.common.AjaxJson;
import com.qfy.modules.smartform.api.common.DataGrid;
import com.qfy.modules.smartform.api.common.ValidTokenUtil;
import com.qfy.modules.smartform.api.dto.InspectionDto;
import com.qfy.modules.smartform.entity.*;
import com.qfy.modules.smartform.service.*;
import com.xkcoding.http.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: Controller
 * @Description: 智能表单接口
 * @author onlineGenerator
 * @date 2019-04-17 15:53:21
 * @version V1.0
 *
 */
@Api(tags="智能表单接口API对外接口")
@RestController
@RequestMapping("/api/surveyDesign")
public class ApiSurveyDesignController {

	private static final Logger logger = LoggerFactory.getLogger(ApiSurveyDesignController.class);

	@Autowired
	private ICheckTemplateService checkTemplateService;

	@Autowired
	private ICheckItemService checkItemService;

	@Autowired
	private ICheckItemRadioService checkItemRadioService;

	@Autowired
	private ICheckItemCheckboxService checkItemCheckboxService;

	@Autowired
	private ICheckItemMultiFillblankService checkItemMultiFillblankService;

	@Autowired
	private ICheckItemOrderbyService checkItemOrderbyService;


	@Autowired
	private ICheckItemScoreService checkItemScoreService;



	@Autowired
	private ICheckItemLogicService checkItemLogicService;



	@ApiOperation("智能表单获取详情接口")
	@RequestMapping(value = "/surveyAll", method = RequestMethod.POST)
	public Result surveyAll(@RequestParam String surveyId, @RequestParam String sid) {
		Result result = new Result();
		CheckTemplate checkTemplate = checkTemplateService.buildSurvey(surveyId,sid);
		result.setResult(checkTemplate);
		return result;
	}
	@ApiOperation("智能表单单据保存addSurvey")
	@RequestMapping(value = "/addSurvey", method = RequestMethod.POST)
	public Result addSurvey(@RequestBody CheckTemplate checkTemplate) {
		Result result = new Result();
		checkTemplateService.saveOrUpdate(checkTemplate);
		result.setResult(checkTemplate);
		return result;
	}

	@ApiOperation("智能表单数据删除题deleteCheckItem")
	@RequestMapping(value = "/deleteCheckItem", method = RequestMethod.POST)
	public Result deleteCheckItem(@RequestParam String quId) {
		Result result = new Result();
		checkItemService.del1CheckItem(quId);
		return result;
	}

	@ApiOperation("智能表单数据保存单选题Radios")
	@RequestMapping(value = "/addRadios", method = RequestMethod.POST)
	public Result Radios(@RequestBody CheckItem checkItem) {
		Result result = new Result();
		CheckItem entity=checkItemRadioService.addRadios(checkItem);
		result.setResult(entity);
		return result;
	}

	@ApiOperation("智能表单数据删除单选题选择项Radios")
	@RequestMapping(value = "/deleteRadios", method = RequestMethod.POST)
	public Result deleteRadios(@RequestParam String quItemId) {
		Result result = new Result();
		checkItemRadioService.deleteRadios(quItemId);
		return result;
	}


	@ApiOperation("智能表单数据保存填空题Fillblank")
	@RequestMapping(value = "/addFillblank", method = RequestMethod.POST)
	public Result addFillblank(@RequestBody CheckItem checkItem) {
			Result result = new Result();
			CheckItem entity=checkItemService.addFillblank(checkItem);
			result.setResult(entity);
			return result;
	}

	@ApiOperation("智能表单数据保存多选题Checkbox")
	@RequestMapping(value = "/addCheckbox", method = RequestMethod.POST)
	public Result addCheckbox(@RequestBody CheckItem checkItem) {
		Result result = new Result();
		CheckItem entity=checkItemCheckboxService.addCheckbox(checkItem);
		result.setResult(entity);
		return result;
	}



	@ApiOperation("智能表单数据删除多选题选择项Checkbox")
	@RequestMapping(value = "/deleteCheckbox", method = RequestMethod.POST)
	public Result deleteCheckbox(@RequestParam String quItemId) {
		Result result = new Result();
		checkItemCheckboxService.deleteCheckbox(quItemId);
		return result;
	}



	@ApiOperation("智能表单数据保存多行填空题MultiFillblank")
	@RequestMapping(value = "/addMultiFillblank", method = RequestMethod.POST)
	public Result addMultiFillblank(@RequestBody CheckItem checkItem) {
		Result result = new Result();
		CheckItem entity=checkItemMultiFillblankService.addMultiFillblank(checkItem);
		result.setResult(entity);
		return result;
	}

	@ApiOperation("智能表单数据删除多行填空题选择项MultiFillblank")
	@RequestMapping(value = "/deleteMultiFillblank", method = RequestMethod.POST)
	public Result deleteMultiFillblank(@RequestParam String quItemId) {
		Result result = new Result();
		checkItemMultiFillblankService.deleteMultiFillblank(quItemId);
		return result;
	}



	@ApiOperation("智能表单数据保排序题addOrderby")
	@RequestMapping(value = "/addOrderby", method = RequestMethod.POST)
	public Result addOrderby(@RequestBody CheckItem checkItem) {
		Result result = new Result();
		CheckItem entity=checkItemOrderbyService.addOrderby(checkItem);
		result.setResult(entity);
		return result;
	}


	@ApiOperation("智能表单数据删除排序题选择项Orderby")
	@RequestMapping(value = "/deleteOrderby", method = RequestMethod.POST)
	public Result deleteOrderby(@RequestParam String quItemId) {
		Result result = new Result();
		checkItemOrderbyService.deleteOrderby(quItemId);
		return result;
	}



	@ApiOperation("智能表单数据保存分页Pagetag")
	@RequestMapping(value = "/addPagetag", method = RequestMethod.POST)
	public Result addPagetag(@RequestBody CheckItem checkItem) {
		Result result = new Result();
		CheckItem entity=checkItemService.addPagetag(checkItem);
		result.setResult(entity);
		return result;
	}


	@ApiOperation("智能表单数据保存分段Paragraph")
	@RequestMapping(value = "/addParagraph", method = RequestMethod.POST)
	public Result addParagraph(@RequestBody CheckItem checkItem) {
		Result result = new Result();
		CheckItem entity=checkItemService.addParagraph(checkItem);
		result.setResult(entity);
		return result;
	}

	@ApiOperation("智能表单数据保评分题Score")
	@RequestMapping(value = "/addScore", method = RequestMethod.POST)
	public Result addScore(@RequestBody CheckItem checkItem) {
		Result result = new Result();
		CheckItem entity=checkItemScoreService.addScore(checkItem);
		result.setResult(entity);
		return result;
	}


	@ApiOperation("智能表单数据删除评分题选择项Score")
	@RequestMapping(value = "/deleteScore", method = RequestMethod.POST)
	public Result deleteScore(@RequestParam String quItemId) {
		Result result = new Result();
		checkItemScoreService.deleteScore(quItemId);
		return result;
	}

	@ApiOperation("智能表单数据保上传文件题UploadFile")
	@RequestMapping(value = "/addUploadFile", method = RequestMethod.POST)
	public Result addUploadFile(@RequestBody CheckItem checkItem) {
		Result result = new Result();
		CheckItem entity=checkItemService.addUploadFile(checkItem);
		result.setResult(entity);
		return result;
	}

	@ApiOperation("智能表单数据删除题逻辑Logic")
	@RequestMapping(value = "/deleteLogic", method = RequestMethod.POST)
	public Result deleteLogic(@RequestParam String id) {
		Result result = new Result();
		checkItemLogicService.delectLogic(id);
		return result;
	}




	@ApiOperation("智能表单数据保存数字题Digit")
	@RequestMapping(value = "/addDigit", method = RequestMethod.POST)
	public Result addDigit(@RequestBody CheckItem checkItem) {
		Result result = new Result();
		CheckItem entity=checkItemService.addDigit(checkItem);
		result.setResult(entity);
		return result;
	}




}
