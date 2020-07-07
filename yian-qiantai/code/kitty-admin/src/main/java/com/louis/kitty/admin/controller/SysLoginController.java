package com.louis.kitty.admin.controller;


import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.code.kaptcha.Producer;
import com.louis.kitty.admin.model.SysUser;
import com.louis.kitty.admin.security.JwtAuthenticatioToken;
import com.louis.kitty.admin.sevice.SysUserService;
import com.louis.kitty.admin.util.PasswordUtils;
import com.louis.kitty.admin.util.SecurityUtils;
import com.louis.kitty.admin.vo.LoginBean;
import com.louis.kitty.core.http.HttpResult;

/**
 * 登录控制器
 * @author earl
 * @date Oct 29, 2018
 */
@RestController
public class SysLoginController {

	@Autowired
	private Producer producer;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * 登录接口
	 */
	@PostMapping(value = "/login")
	public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
		String username = loginBean.getAccount();
		String password = loginBean.getPassword();		
		// 用户信息
		 SysUser user = sysUserService.findByName(username);

		// 账号不存在、密码错误
		if (user == null) {
			return HttpResult.error("Account does not exist");
		}
		
		if (!PasswordUtils.matches(user.getSalt(), password, user.getPassword())) {
			return HttpResult.error("Password error!");
		}

		// 账号锁定
		/*
		 * if (user.getStatus() == 0) { return HttpResult.error("账号已被锁定!"); }
		 */

		// 系统登录认证
		JwtAuthenticatioToken token = SecurityUtils.login(request, username, password, authenticationManager);
				
		return HttpResult.ok(token);
	}

}
