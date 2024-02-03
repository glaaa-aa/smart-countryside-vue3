package com.qfy.modules.city3d.controller;

import com.qfy.modules.city3d.po.SysUser;
import com.qfy.modules.city3d.service.UserService;
import com.qfy.modules.city3d.util.CharacterUtils;
import com.qfy.modules.city3d.util.Res;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController extends CrossOriginController {
	@Autowired
	private UserService userService;

	@RequestMapping("/getUser")
	public @ResponseBody List<SysUser> getUser() throws Exception {
		List<SysUser> result = userService.getUser();
		return result;
	}

	@RequestMapping("/insert")
	public @ResponseBody int insert(@RequestBody SysUser sysUser) throws Exception{
		CharacterUtils characterUtils = new CharacterUtils();
		String uid = characterUtils.getUUID();
		sysUser.setUserId(uid);
		int result = userService.insert(sysUser);
		return result;
	}

	@RequestMapping("/update")
	public @ResponseBody int updateByPrimaryKey(@RequestBody SysUser sysUser) throws Exception{
		int result = userService.updateByPrimaryKey(sysUser);
		return result;
	}

	@RequestMapping("/delete")
	public @ResponseBody int deleteByPrimaryKey(@RequestBody SysUser sysUser) throws Exception{
		int result = userService.deleteByPrimaryKey(sysUser.getUserId());
		return result;
	}

	@RequestMapping("/login")
    public @ResponseBody Object login(@RequestBody Map param, HttpSession session) {
    	String loginName = (String) param.get("loginName");
        String pwd = (String) param.get("pwd");
        if (StringUtils.isNotEmpty(loginName) && StringUtils.isNotEmpty(pwd)) {
        	Map res = new HashMap();
        	SysUser sysUser = userService.findByLoginNameAndPwd(loginName, pwd);
            if (sysUser != null) {
                res.put("token", sysUser.getRole());
                res.put("userName", sysUser.getUserName());
                res.put("userDep", sysUser.getUserDep());
                res.put("role", sysUser.getRole());
                res.put("app", sysUser.getApp());
                session.setAttribute("LOGIN_USER", sysUser);
                return Res.success(res);
            }else{
            	List<SysUser> sysUsers = userService.findByLoginName(loginName);
            	if(sysUsers.size() != 0){
            		 return Res.fail("login_fail", "密码错误");
            	}else{
            		 return Res.fail("login_fail", "登录名不存在");
            	}
            }
        }
        return Res.fail("login_fail", "登录失败");
    }
}
