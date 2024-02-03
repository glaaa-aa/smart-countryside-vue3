package com.qfy.modules.smartform.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: Controller
 * @Description: 设备信息
 * @author onlineGenerator
 * @date 2019-04-17 15:53:21
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/api/deviceController")
public class ApiDeviceController {
	private static final Logger logger = LoggerFactory.getLogger(ApiDeviceController.class);

//
//	@Autowired
//	private SystemService systemService;
//	@Autowired
//	private DeviceInfoServiceI deviceInfoService;
//
//	/**
//	 * 设备详情
//	 * @param request
//	 * @param serialno
//	 * @return
//	 */
//	@RequestMapping(params = "details")
//	@ResponseBody
//	public AjaxJson scanPointDetail(HttpServletRequest request, @RequestParam(required = true) String serialno){
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "访问成功";
//
//		String token = ValidTokenUtil.checkReq(request);
//		if(StringUtil.isEmpty(token)){
//			j.setMsg("API认证失败");
//			j.setSuccess(false);
//			return j;
//		}
//		Map<String, Object> resultMap = deviceInfoService.details(serialno);
//
//		j.setAttributes(resultMap);
//		return j;
//	}

}
