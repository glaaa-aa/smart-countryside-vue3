package com.qfy.modules.mp.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
//import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
//import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
//import me.chanjar.weixin.common.error.WxErrorException;
//import me.chanjar.weixin.mp.api.WxMpService;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.system.entity.SysThirdAccount;
import org.jeecg.modules.system.model.ThirdLoginModel;
import org.jeecg.modules.system.service.ISysThirdAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
* @Description: 学生信息表
* @Author: jeecg-boot
* @Date:   2022-09-29
* @Version: V1.0
*/
@Api(tags="微信公众号授权登录")
@RestController
@RequestMapping("/api/wxLogin")
@Slf4j
public class APIWxLoginController {
//    @Autowired
//    private WxMpService wxMpService;
//
//    @Value("${wx.mp.domain}")
//    private String domain;
//
//    @Autowired
//    private ISysBaseAPI sysBaseAPI;
//    @Autowired
//    private RedisUtil redisUtil;
//    @Autowired
//    private ISysThirdAccountService sysThirdAccountService;
//    /**
//     * 构建网页授权链接
//     * @return
//     */
//    @GetMapping(value = "/getUrl")
//    public String getUrl() {
//        return wxMpService.getOAuth2Service().buildAuthorizationUrl(domain +"/pages/index/main", "snsapi_userinfo","state");
//    }
//
//    /**
//     * 网页授权回调
//     * @param code
//     * @return
//     * @throws WxErrorException
//     */
//    @GetMapping(value = "/mpOauth")
//    public Result mpOauth(@RequestParam(name="code",required=true) String code){
//        try {
//            WxOAuth2AccessToken accessToken = wxMpService.getOAuth2Service().getAccessToken(code);
//            WxOAuth2UserInfo wxOAuth2UserInfo = wxMpService.getOAuth2Service().getUserInfo(accessToken,"zh_CN");
//            //根据OPenid 获取用户信息
//            SysThirdAccount account = sysThirdAccountService.getOneByThirdUserId(wxOAuth2UserInfo.getOpenid(),"wxmp");
//            if(account == null){
//                ThirdLoginModel model = new ThirdLoginModel();
//                model.setUsername(wxOAuth2UserInfo.getNickname());
//                model.setUuid(wxOAuth2UserInfo.getOpenid());
//                model.setSource("wxmp");
//                model.setAvatar(wxOAuth2UserInfo.getHeadImgUrl());
//                account = sysThirdAccountService.saveThirdUser(model);
//            }
//            JSONObject obj = new JSONObject();
//            Result result = new Result();
//            //用户登录信息
//            obj.put("userId", account.getId());
//            obj.put("openid", account.getThirdUserId());
//            // 生成token
//            String token = JwtUtil.sign(account.getThirdUserId(), account.getThirdUserId());
//            // 设置超时时间
//            redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
//            redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME*12/ 1000);
//            //token 信息
//            obj.put("token", token);
//            result.setResult(obj);
//            result.setSuccess(true);
//            result.setCode(200);
//            return result;
//        }catch (WxErrorException exception){
//            return Result.error(exception.getMessage());
//        }
//    }

}
