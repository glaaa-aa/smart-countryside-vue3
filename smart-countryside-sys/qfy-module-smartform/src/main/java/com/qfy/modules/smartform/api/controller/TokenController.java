package com.qfy.modules.smartform.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.PasswordUtil;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author lanls
 * @version 1.0
 * @date 2022/4/23 21:50
 */
@RestController
@RequestMapping("/rest/tokens")
public class TokenController {

    public static final Logger logger = LoggerFactory.getLogger(TokenController.class);

    @Autowired
    private ISysBaseAPI baseAPI;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        logger.info("获取TOKEN[{}]" , username);
        // 验证
        if (StringUtils.isEmpty(username)) {
            return new ResponseEntity("用户账号不能为空!", HttpStatus.NOT_FOUND);
        }
        // 验证
        if (StringUtils.isEmpty(username)) {
            return new ResponseEntity("用户密码不能为空!", HttpStatus.NOT_FOUND);
        }

        try{
            // 验证用户名或者用户手机号码登录
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("username", username);
            wrapper.or();
            wrapper.eq("phone", username);
            LoginUser sysUser = baseAPI.queryByCustomParams(wrapper);
            if (null == sysUser){
                return new ResponseEntity("用户账号密码错误!", HttpStatus.NOT_FOUND);
            }

            // 验证登录密码
            String loginPwd = PasswordUtil.encrypt(username, password, sysUser.getSalt());
            if (!sysUser.getPassword().equals(loginPwd)){
                return new ResponseEntity("用户账号密码错误!", HttpStatus.NOT_FOUND);
            }
            request.setAttribute("RefreshToken",false);
            return tokenLogin(sysUser, request);
        }catch (Exception e){
            logger.error("用户登录异常：", e);
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据USER生成token，共用接口
     * @param user
     * @return
     */
    private ResponseEntity<?> tokenLogin(LoginUser user, HttpServletRequest request) throws Exception{
        if (user == null) {
            // 提示用户名或密码错误
            return new ResponseEntity("用户账号密码错误!", HttpStatus.NOT_FOUND);
        }

        // 限制用户的同一个账号只能同时在线1个，不能多设备同时登录在线。实现逻辑是：给token增加一个客户端UUID与token进行绑定；
//		String requestIp = IpUtil.getIpAddr(request);//获取客户端真实IP
//		String encoderIp = Md5Util.encoderByMd5(requestIp + IpUtil.ADDR_SALT);// IP使用MD5加密，并且加入盐，避免碰撞破解
//		logger.info("客户端认证登录IP【{}】,加密后【{}】",requestIp, encoderIp);
//        boolean refreshToken = (Boolean) request.getAttribute("RefreshToken");// 区分来自刷新token接口还是登录接口
//        String uuid = request.getHeader(JwtConstants.X_AUTH_UUID);
//        String encryptUUID = MD5Util.MD5Encode(uuid + JwtConstants.JWT_SECRET, "UTF-8");
//        logger.info("客户端认证登录UUID【{}】,加密后【{}】",uuid, encryptUUID);

        /**
         * jeecgboot使用的token缓存方式与jeecg不同，此处暂时无法实现同一用户只能在一台设备上登录，
         * 需考虑将拦截逻辑改到token拦截器或者过滤器中实现此功能
          */

//        String cacheToken = tokenManager.getToken(user.getUserName());
//        // 如果缓存中没有该用户相应的token，则判定为token已过期，本次请求为正常的刷新token或者用户登录认证获取token
//        if (refreshToken && !StringUtils.isBlank(cacheToken)){
//            Object cacheUUID = Jwts.parser().setSigningKey(JwtConstants.JWT_SECRET).parseClaimsJws(cacheToken).getHeader().get(JwtConstants.X_AUTH_UUID);
//            // 客户端请求Ip与缓存token中的授权IP不一致，判定为：该用户账号已在其它设备上登录
//            if (null != cacheUUID && !StringUtils.equals(encryptUUID,cacheUUID.toString())){
//                logger.error("认证失败，请求IP【{}】,缓存授权IP【{}】.", encryptUUID, cacheUUID);
//                return new ResponseEntity(Result.error("认证失败，该用户账号已在其它设备上登录！"), HttpStatus.FORBIDDEN);
//            }
//        }

//        Map<String,Object> header = new HashMap<>();
//        header.put(JwtConstants.X_AUTH_UUID, encryptUUID);//将客户端UUID写入token header，方便单个设备与用户token的绑定

        // 生成一个token，保存用户登录状态
        String token = JwtUtil.sign(user.getUsername(), user.getPassword());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("token", token);
        result.put("user", user);
        result.put("userId", user.getId());
        return new ResponseEntity(Result.OK(result), HttpStatus.OK);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    public Result logout(@PathVariable("username") String username, HttpServletRequest request) {
        logger.info("deleteToken[{}]" , username);
        // 验证
        if (StringUtils.isEmpty(username)) {
            return Result.error("用户账号，不能为空!");
        }
        try {
            String token = TokenUtils.getTokenByRequest(request);
            String tokenUsername = JwtUtil.getUsername(token);
            if (StringUtils.isNotBlank(tokenUsername) && tokenUsername.equals(username)){
                // 清除该token缓存
                redisUtil.del(CommonConstant.PREFIX_USER_TOKEN + token);
            }
        } catch (Exception e) {
            logger.error("退出登录失败，清除缓存异常：", e);
            return Result.error("销毁TOKEN失败");
        }
        return Result.OK();
    }

}
