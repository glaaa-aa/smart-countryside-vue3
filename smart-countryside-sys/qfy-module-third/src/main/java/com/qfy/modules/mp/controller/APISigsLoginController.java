package com.qfy.modules.mp.controller;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.config.sign.util.HttpsHelper;
import org.jeecg.modules.system.entity.SysThirdAccount;
import org.jeecg.modules.system.model.ThirdLoginModel;
import org.jeecg.modules.system.service.ISysThirdAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;

/**
* @Description: qfy_feedback
* @Author: 马强
* @Date:   2021-11-28
* @Version: V1.0
*/
@Api(tags="意见反馈")
@RestController
@RequestMapping("/api/sigs")
@Slf4j
public class APISigsLoginController {

   @Autowired
   private ISysBaseAPI sysBaseAPI;
   @Autowired
   private ISysThirdAccountService sysThirdAccountService;
   @Autowired
   private RedisUtil redisUtil;

    @Value("${oauth2.domain}")
    private String domain;

    @Value("${oauth2.third_main}")
    private String third_main;
    @Value("${oauth2.client_id}")
    private String client_id;
    @Value("${oauth2.client_secret}")
    private String client_secret;

    /**
     * 学校SIGS OAuth2登录
     * @return
     */
    @ResponseBody
    @GetMapping("/oAuth2")
    public Result getUrl(@RequestParam(name="thirdUserId") String thirdUserId, HttpServletResponse response) throws Exception {
        if(StringUtils.isNotEmpty(thirdUserId)){
            SysThirdAccount account = sysThirdAccountService.getOneByThirdUserId(thirdUserId, "sigs");
            if(account != null){
                Map obj = handleAccount(account);
                return Result.ok(obj);
            }
        }

        String redirectUri = domain + "/pages/index/main";
        StringBuffer builder = new StringBuffer();
        builder.append(third_main);
        builder.append("/sso/oauth/authorize?response_type=").append("code");
        builder.append("&client_id=").append(client_id);
        builder.append("&redirect_uri=").append(URLEncoder.encode(redirectUri));
        String url = builder.toString();
        return Result.ok(url);
    }

    /**
     * 学校SIGS OAuth2登录
     * @return
     */
    @ResponseBody
    @GetMapping("/oAuth/getUserByCode")
    public Result getUserByCode(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String code = request.getParameter("code");

        String redirectUri = third_main + "/jeecg-boot/api/oAuth/getUser";
        StringBuffer buffer = new StringBuffer();
        buffer.append(third_main);
        buffer.append("/sso/oauth/accessToken");
        buffer.append("?client_id=").append(client_id);
        buffer.append("&client_secret=").append(client_secret);
        buffer.append("&grant_type=authorization_cod"); //固定值
        buffer.append("&code=").append(code);
        buffer.append("&redirect_uri=").append(URLEncoder.encode(redirectUri));

        String result1 = HttpsHelper.httpsPost(buffer.toString(),null,null);
        JSONObject tokenJson = JSONObject.fromObject(result1);

        Map<String,String> params = new HashMap<>();
        params.put("access_token",tokenJson.getString("access_token"));
        Map<String,String> header = new HashMap<>();
        header.put("Content-Type","application/x-www-form-urlencoded");
        String result2 = HttpsHelper.httpsPostMap(third_main + "/sso/oauth/userInfo",params,header );
       log.info("登录账号==>"+result2);
        JSONObject userInfo = JSONObject.fromObject(result2);
        if(userInfo.getInt("ret") == 0 && userInfo.get("uid") != null){
            SysThirdAccount account = sysThirdAccountService.getOneByThirdUserId(userInfo.get("uid").toString(), "sigs");
            if (account == null) {
                ThirdLoginModel model = new ThirdLoginModel();
                model.setUsername(userInfo.get("name")==null?null: userInfo.getString("name"));
                model.setThirdUserId(userInfo.getString("uid"));
                model.setUuid(userInfo.get("personId") == null?model.getThirdUserId(): userInfo.getString("personId"));
//                model.setUserType(userInfo.get("personType")==null?null: userInfo.getString("personType"));
                model.setSource("sigs");
                account = this.sysThirdAccountService.saveThirdUser(model);
            }
            Map obj = handleAccount(account);
            return Result.ok(obj);
        }else {
            return Result.error(userInfo.getString("msg"));
        }

    }


    private Map handleAccount(SysThirdAccount account){
        Map<String,Object> obj = new HashMap<>();
        obj.put("account", account);
        String token = JwtUtil.sign(account.getThirdUserId(), account.getThirdUserId());
        obj.put("token",token);
        this.redisUtil.set("prefix_user_token_" + token, token);
        this.redisUtil.expire("prefix_user_token_" + token, 43200L);
        return  obj;
    }

}
