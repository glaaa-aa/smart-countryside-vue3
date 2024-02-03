package com.qfy.modules.wxmini.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.alibaba.fastjson.JSONObject;
import com.qfy.modules.wxmini.vo.WxLoginVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.PasswordUtil;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.system.entity.SysTenant;
import org.jeecg.modules.system.entity.SysThirdAccount;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.service.ISysTenantService;
import org.jeecg.modules.system.service.ISysThirdAccountService;
import org.jeecg.modules.system.service.ISysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author maqiang
 * @since 2022-06-12
 */
@RestController
@RequestMapping("/sys/thirdApp")
@Api(tags = "用户登录")
@Slf4j
public class ThirdAppLoginController {
    @Autowired
    private ISysBaseAPI sysBaseAPI;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysTenantService sysTenantService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private WxMaService wxMaService;
    @Autowired
    private ISysThirdAccountService sysThirdAccountService;

    /**
     * 用于登录和注册, 获取openid 用于绑定账号
     *
     * @param wxLoginVo .code
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getThirdId/{thirdType}", method = RequestMethod.POST)
    @ResponseBody
    public synchronized Result getThirdId(@RequestBody WxLoginVo wxLoginVo) throws Exception {
        if (StringUtils.isEmpty(wxLoginVo.getCode())) {
            return Result.error("授权请求错误");
        }
        Result result = new Result();
        // code换取session
        WxMaJscode2SessionResult session = (WxMaJscode2SessionResult) redisUtil.get(wxLoginVo.getCode());
        if (session == null) {
            session = wxMaService.getUserService().getSessionInfo(wxLoginVo.getCode());
            redisUtil.expire(wxLoginVo.getCode(), 60 * 60);
            redisUtil.set(wxLoginVo.getCode(), session);
        }

        log.info("code换取session：{}", session);
        if (session == null || StringUtils.isEmpty(session.getSessionKey())) {
            return Result.error("微信code获取信息发生错误");
        }
        result.setResult(session);
        return result;
    }


    /**
     * 根据 账号和密码 验证登录，并绑定openid
     *
     * @param wxLoginVo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/wxLogin/{thirdType}", method = RequestMethod.POST)
    @ResponseBody
    public synchronized Result wxLoginByUserName(@RequestBody WxLoginVo wxLoginVo, HttpServletRequest request) throws Exception {
        if (StringUtils.isEmpty(wxLoginVo.getOpenid())) {
            return Result.error("授权请求错误");
        }
        if (wxLoginVo.getUsername() == null || wxLoginVo.getPassword() == null) {
            return Result.error("账号或者密码为空");
        }

        //1. 校验用户是否有效
        SysUser sysUser = sysUserService.getUserByName(wxLoginVo.getUsername());

        Result result = sysUserService.checkUserIsEffective(sysUser);
        if (!result.isSuccess()) {
            return result;
        }

//		if(sysUser.getTenantId().intValue() != tenantId){
//			return Result.error("小程序和账号关联错误");
//		}

        //2. 校验用户名或密码是否正确
        String userpassword = PasswordUtil.encrypt(wxLoginVo.getUsername(), wxLoginVo.getPassword(), sysUser.getSalt());
        String syspassword = sysUser.getPassword();
        if (!syspassword.equals(userpassword)) {
            result.error500("用户名或密码错误");
            return result;
        }

        //查看第三方账号
        SysThirdAccount account = sysThirdAccountService.getOneBySysUserId(sysUser.getId(), wxLoginVo.getThirdType());
        if (account != null) {
            return Result.error("该账号已经被绑定， 不能重复登录绑定");
        }

        account = new SysThirdAccount();
        account.setSysUserId(sysUser.getId());
        account.setThirdType(wxLoginVo.getThirdType());
        account.setThirdUserId(wxLoginVo.getOpenid());
        account.setThirdUserUuid(wxLoginVo.getOpenid());
        account.setPhone(sysUser.getPhone());
        account.setRealname(sysUser.getRealname());
        account.setSerialNo(sysUser.getUsername());
        account.setStatus(1);
        account.setDelFlag(0);
        account.setUserType(sysUser.getUserType());
        account.setAvatar(sysUser.getAvatar());
//			account.setAvatar("dgr_mini/touxiang.png");
        sysThirdAccountService.save(account);

        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(sysUser, loginUser);

        loginUser.setThirdType(account.getThirdType());
        loginUser.setThirdUserId(account.getThirdUserId());

        loginUser.setTenantName(getTenant(sysUser.getTenantId()).getName());
        JSONObject obj = new JSONObject();
        //用户登录信息
        obj.put("userInfo", loginUser);
        // 生成token
        String token = JwtUtil.sign(sysUser.getUsername(), syspassword);
        // 设置超时时间
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME * 2 / 1000);
        //token 信息
        obj.put("token", token);
        result.setResult(obj);
        result.setSuccess(true);
        result.setCode(200);
        return result;
    }

    private SysTenant getTenant(Integer tenantId) {
        SysTenant tenant = sysTenantService.getById(tenantId);
        return tenant;
    }


    /**
     * 用于登录和注册,绑定openId和手机号phone
     *
     * @param wxLoginVo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/bindAccount/{thirdType}", method = RequestMethod.POST)
    @ResponseBody
    public synchronized Result bindAccount(HttpServletRequest request, @RequestBody WxLoginVo wxLoginVo) throws Exception {
        if (StringUtils.isEmpty(wxLoginVo.getCode())) {
            return Result.error("授权请求错误");
        }
        // code换取session
        WxMaJscode2SessionResult session = (WxMaJscode2SessionResult) redisUtil.get(wxLoginVo.getCode());
        if (session == null) {
            session = wxMaService.getUserService().getSessionInfo(wxLoginVo.getCode());
        }
        log.info("code换取session：{}", session);
        if (session == null || StringUtils.isEmpty(session.getSessionKey())) {
            return Result.error("微信code获取信息发生错误");
        }
        //获取手机号
        WxMaPhoneNumberInfo phoneNumberInfo = wxMaService.getUserService().getPhoneNoInfo(session.getSessionKey(), wxLoginVo.getEncryptedData(), wxLoginVo.getIv());
        if (phoneNumberInfo == null) {
            return Result.error("获取手机号错误");
        }

        SysThirdAccount account = sysThirdAccountService.getOneByThirdUserId(session.getOpenid(), wxLoginVo.getThirdType());
        if (account == null) {
            //获取租户id 使用租户前缀
            String tenantId = request.getHeader("tenant-id");
            String prefix = "Def";
            if (StringUtils.isNotEmpty(tenantId)) {
                SysTenant tenant = sysTenantService.getById(Integer.parseInt(tenantId));
                prefix = tenant.getPreCode();
            }

            account = new SysThirdAccount();
            String serialno = sysThirdAccountService.getNextserial(prefix);
            account.setThirdType(wxLoginVo.getThirdType());
            account.setThirdUserId(session.getOpenid());
            account.setThirdUserUuid(session.getUnionid());
            account.setPhone(phoneNumberInfo.getPhoneNumber());
            account.setRealname(phoneNumberInfo.getPhoneNumber());
            account.setSerialNo(serialno);
            account.setStatus(1);
            account.setDelFlag(0);
            account.setAvatar("dgr_mini/touxiang.png");
            sysThirdAccountService.save(account);
        }

        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(account.getSerialNo());
        loginUser.setId(null);
        loginUser.setPassword(account.getSerialNo());
        loginUser.setPhone(account.getPhone());
        loginUser.setRealname(account.getPhone());
        loginUser.setThirdType(wxLoginVo.getThirdType()); //
        loginUser.setThirdUserId(account.getThirdUserId()); //此参数作为唯一判断 是否注册account
        JSONObject obj = new JSONObject();
        //用户登录信息
        obj.put("userInfo", loginUser);
        // 生成token
        String token = JwtUtil.sign(loginUser.getUsername(), loginUser.getPassword());
        // 设置超时时间
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME * 2 / 1000);

        // 记录微信小程序登录日志
//		baseCommonService.addLog("微信登录",CommonConstant.LOG_TYPE_1, null,loginUser);

        //token 信息
        obj.put("token", token);
        return Result.ok(obj);
    }


    /**
     * h5登录 根据 账号和密码 验证登录
     *
     * @param wxLoginVo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mlogin", method = RequestMethod.POST)
    @ResponseBody
    public synchronized Result mlogin(@RequestBody WxLoginVo wxLoginVo, HttpServletRequest request) throws Exception {

        if (wxLoginVo.getUsername() == null || wxLoginVo.getPassword() == null) {
            return Result.error("账号或者密码为空");
        }

        //1. 校验用户是否有效
        SysUser sysUser = sysUserService.getUserByName(wxLoginVo.getUsername());
        Result result = sysUserService.checkUserIsEffective(sysUser);
        if (!result.isSuccess()) {
            return result;
        }

        //2. 校验用户名或密码是否正确
        String userpassword = PasswordUtil.encrypt(wxLoginVo.getUsername(), wxLoginVo.getPassword(), sysUser.getSalt());
        String syspassword = sysUser.getPassword();
        if (!syspassword.equals(userpassword)) {
            result.error500("用户名或密码错误");
            return result;
        }

        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(sysUser, loginUser);
        loginUser.setTenantName(getTenant(sysUser.getTenantId()).getName());
        JSONObject obj = new JSONObject();
        //用户登录信息
        obj.put("userInfo", loginUser);
        // 生成token
        String token = JwtUtil.sign(sysUser.getUsername(), syspassword);
        // 设置超时时间
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME * 2 / 1000);
        //token 信息
        obj.put("token", token);
        result.setResult(obj);
        result.setSuccess(true);
        result.setCode(200);
        return result;
    }


    /**
     * token重新刷新
     *
     * @param wxLoginVo
     * @return
     * @throws Exception
     */
//	@SuppressWarnings("unchecked")
    @RequestMapping(value = "/refreshToken/{thirdType}", method = RequestMethod.POST)
    @ResponseBody
    public Result refreshToken(@RequestBody WxLoginVo wxLoginVo) throws Exception {

        JSONObject obj = new JSONObject();
        Result result = new Result();
        LoginUser loginUser = null;
        if (StringUtils.isNotEmpty(wxLoginVo.getCode()) && wxLoginVo.getThirdType() != null && wxLoginVo.getThirdType().equals("weixin")) {
            // code换取session
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(wxLoginVo.getCode());
            log.info("code换取session：{}", session);

            if (session == null || StringUtils.isEmpty(session.getSessionKey())) {
                return Result.error("微信code获取信息发生错误");
            }
            //根据OPenid 获取用户信息
            loginUser = sysBaseAPI.queryByThirdId(session.getOpenid(), wxLoginVo.getThirdType());


        } else if (wxLoginVo != null && StringUtils.isNotEmpty(wxLoginVo.getUsername())) {
            loginUser = sysBaseAPI.getUserByUserName(wxLoginVo.getUsername());
        }

        //code=-1 作为未注册判断
        if (loginUser == null || loginUser.getId() == null) {
            return Result.error(-1, "未注册，请先注册");
        }

        loginUser.setTenantName(getTenant(loginUser.getTenantId()).getName());

        //用户登录信息
        obj.put("userInfo", loginUser);
        // 生成token
        String token = JwtUtil.sign(loginUser.getUsername(), loginUser.getPassword());
        // 设置超时时间
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME * 2 / 1000);

        // 记录微信小程序登录日志
//		baseCommonService.addLog("微信登录",CommonConstant.LOG_TYPE_1, null,loginUser);
        obj.put("token", token);
        result.setResult(obj);
        result.setSuccess(true);
        result.setCode(200);
        return result;
    }

}
