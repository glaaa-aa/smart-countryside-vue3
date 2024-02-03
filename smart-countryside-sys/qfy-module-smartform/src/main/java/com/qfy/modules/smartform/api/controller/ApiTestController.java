package com.qfy.modules.smartform.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: Controller
 * @Description: 二维码表
 * @author onlineGenerator
 * @date 2019-04-17 15:53:21
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/api/test")
public class ApiTestController {
	private static final Logger logger = LoggerFactory.getLogger(ApiTestController.class);

//	@Autowired
//	private SystemService systemService;
//
//	@RequestMapping(params = "test")
//	@ResponseBody
//	public AjaxJson todayPlan(HttpServletRequest request) {
//
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//		return j;
//	}
}
