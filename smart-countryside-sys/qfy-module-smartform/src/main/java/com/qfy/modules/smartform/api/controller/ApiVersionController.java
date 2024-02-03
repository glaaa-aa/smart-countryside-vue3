package com.qfy.modules.smartform.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: Controller
 * @Description: 版本更新
 * @author onlineGenerator
 * @date 2019-04-17 15:53:21
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/api/versionController")
public class ApiVersionController {

	private static final Logger logger = LoggerFactory.getLogger(ApiVersionController.class);

//	@Autowired
//	private SystemService systemService;
//
//	@RequestMapping(params = "verjson")
//	@ResponseBody
//	public AjaxJson verjson(HttpServletRequest request, HttpServletResponse response) {
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
//
//
//		CriteriaQuery cq = new CriteriaQuery(VersionUpdateEntity.class);
//		cq.setCurPage(1);
//		cq.setPageSize(1);
//		cq.addOrder("verCode", SortDirection.desc);
//
//		List<VersionUpdateEntity> list = this.systemService.getListByCriteriaQuery(cq, false);
//		if(list != null && list.size()>0){
//			Map<String, Object> updateInfo = new HashMap<>();
//			VersionUpdateEntity entity = list.get(0);
//			updateInfo.put("appName", entity.getAppName());
//			updateInfo.put("apkName", entity.getApkName());
//			updateInfo.put("patchName", entity.getPatchName());
//			updateInfo.put("verName", entity.getVerName());
//			updateInfo.put("remark", entity.getRemark());
//
//			String url = ResourceUtil.getBasePath()+ "/" + entity.getAttchId();
//			updateInfo.put("url", url);
//			updateInfo.put("verCode", entity.getVerCode());
//			j.setAttributes(updateInfo);
//
//		}
//
//		j.setMsg(message);
//		return j;
//	}

}
