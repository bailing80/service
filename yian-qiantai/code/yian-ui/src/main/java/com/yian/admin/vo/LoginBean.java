package com.yian.admin.vo;

import com.yian.admin.model.SysMarket;

/**
 * 登录接口封装对象
 * @author earl
 * @date august 5, 2019
 */
public class LoginBean extends SysMarket { 

	private String account;
	private String password;
	private String captcha;
	    
	private Long id;
	
    private String hospitalName;
    
   
    
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	
	
}
