package com.qfy.modules.wxmini.controller;

import com.qfy.modules.wxmini.vo.WxLoginVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CacheConstant;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.system.entity.SysThirdAccount;
import org.jeecg.modules.system.service.ISysThirdAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author maqiang
 * @since 2022-06-12
 */
@RestController
@RequestMapping("/api/thirdApp")
@Api(tags="用户登录")
@Slf4j
public class APILoginController {
	@Autowired
	private ISysThirdAccountService sysThirdAccountService;
	@Autowired
	private RedisUtil redisUtil;
	/**
	 * 账号退出
	 * @param wxLoginVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goOut/{thirdType}", method = RequestMethod.POST)
	@ResponseBody
	public synchronized Result goOut(@RequestBody WxLoginVo wxLoginVo, HttpServletRequest request) throws Exception {
		if(StringUtils.isEmpty(wxLoginVo.getUsername())){
			return Result.error("参数错误");
		}
		SysThirdAccount account = sysThirdAccountService.getOneBySysUserId(wxLoginVo.getUsername(),wxLoginVo.getThirdType());
		if(account == null){
			return Result.error("账号错误");
		}
		sysThirdAccountService.removeById(account.getId());
		//用户退出逻辑
		String token = request.getHeader(CommonConstant.X_ACCESS_TOKEN);

		log.info(" 用户名:  "+account.getRealname()+",退出成功！ ");
		//清空用户登录Token缓存
		redisUtil.del(CommonConstant.PREFIX_USER_TOKEN + token);
		//清空用户登录Shiro权限缓存
		redisUtil.del(CommonConstant.PREFIX_USER_SHIRO_CACHE + account.getId());
		//清空用户的缓存信息（包括部门信息），例如sys:cache:user::<username>
			redisUtil.del(String.format("%s::%s", CacheConstant.SYS_USERS_CACHE, account.getSerialNo()));
		//调用shiro的logout
		SecurityUtils.getSubject().logout();

		return Result.ok();
	}


}
