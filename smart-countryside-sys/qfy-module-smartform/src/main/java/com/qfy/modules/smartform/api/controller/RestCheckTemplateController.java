package com.qfy.modules.smartform.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qfy.modules.smartform.entity.CheckTemplate;
import com.qfy.modules.smartform.service.ICheckTemplateService;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.vo.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lanls
 * @date 2021.05.23
 * @version v1.0
 * @Desc 检查模板API服务
 */
@RestController
@RequestMapping("/api/checkTemplate")
public class RestCheckTemplateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestCheckTemplateController.class);

	@Autowired
	private ICheckTemplateService checkTemplateService;

//	@GetMapping(value = "/details")
//	public Result queryCheckTemplate(@RequestParam("id") String id) {
//
//		if (StringUtils.isBlank(id)) {
//			return Result.error("请示失败，缺少必要的参数！");
//		}
//
//		Map<String, Object> resultMap = null;
//		try {
//			resultMap = checkTemplateService.queryById(id);
//			if (null == resultMap) {
//				resultMap = Collections.emptyMap();
//			}
//		} catch (Exception e) {
//			LOGGER.error("获取模板数据失败：", e);
//			return Result.error("获取模板数据失败.");
//		}
//		return Result.OK(resultMap);
//	}

	/**
//	 * 获取未绑定巡检点的模板列表
//	 *
//	 * @param page 页码
//	 * @param pageSize 显示条数
//	 * @return 响应报文
//	 */
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public Result queryCheckTemplatePage(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer page,
//										 @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
//											) {
//
//		LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//		try {
//			QueryWrapper<CheckTemplate> wrapper = new QueryWrapper<>();
//			wrapper.apply("a.id NOT IN (SELECT DISTINCT c.template_id FROM qfy_inspection_point c LEFT JOIN " +
//					"qfy_inspection d ON c.inspection_id = d.id WHERE d.create_by = {0})", user.getUsername());
//			//暂时屏蔽部门权限过滤
////			if (null != user && StringUtils.isNotBlank(user.getOrgCode())){
////				wrapper.eq("a.sys_org_code", user.getOrgCode());
////			}
//			Date today = new Date();
//			wrapper.apply("DATE_FORMAT({0},'%Y-%m-%d %H:%i:%s') >= a.start_time", today);
//			wrapper.apply("DATE_FORMAT({0},'%Y-%m-%d %H:%i:%s') <= a.end_time", today);
//			List<CheckTemplate> checkTemplateList = checkTemplateService.queryByPage(wrapper);
//			return Result.OK(checkTemplateList);
//		} catch (Exception e) {
//			LOGGER.error("获取模板数据失败：", e);
//			return Result.error("获取模板数据失败.");
//		}
//	}

}
