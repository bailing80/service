package com.louis.kitty.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.louis.kitty.admin.model.SysEngcontent;
import com.louis.kitty.admin.model.SysEngremark;
import com.louis.kitty.admin.model.SysUser;
import com.louis.kitty.admin.sevice.SysEngcontentService;
import com.louis.kitty.admin.sevice.SysEnglishuserService;
import com.louis.kitty.admin.sevice.SysEngremarkService;
import com.louis.kitty.admin.sevice.SysUserService;
import com.louis.kitty.admin.util.PasswordUtils;
import com.louis.kitty.core.http.HttpResult;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@RestController
@RequestMapping("englishuser")
public class SysEnglishuserController {
	
	/**
	 * 用户登录
	 */
	@Autowired 
	private SysEnglishuserService sysEnglishuserService;
	/**
	 * 留言
	 */
	@Autowired 
	private  SysEngremarkService sysEngremarkService;
	
	/**
	 * 1:彩页 2:视频
	 */
	
	@Autowired 
	private SysEngcontentService sysEngcontentService;
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 用户注册
	 * @param record
	 * @return
	 */
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody SysUser record) 
	{
		if(sysUserService.findByName(record.getPhone()) != null) {
			return HttpResult.error("User already exists!");
		}	
	
		if(sysUserService.findByEmail(record.getEmail()) != null) {
			return HttpResult.error("Mailbox already exists!");
		}		
		else {
			
			
			String salt = PasswordUtils.getSalt();
			String password = PasswordUtils.encode(record.getPassword(), salt);
			record.setSalt(salt);
			record.setPassword(password);
			record.setName(record.getPhone());
			record.setStatus((byte)1);
			
		}
		return HttpResult.ok(sysUserService.save(record));
	}
	/**
	 * 用户留言
	 * @param record
	 * @return
	 */
	@PostMapping(value="/remarksave")
	public HttpResult remarksave(@RequestBody SysEngremark record) 
	{
	
		return HttpResult.ok(sysEngremarkService.save(record));
	}
	/**
	 * 彩页，视频
	 * @param status
	 * @return
	 */
	@PostMapping(value="/selectcontent")
	public HttpResult selectcontent(@RequestParam SysEngcontent status) 
	{
	
		return HttpResult.ok(sysEngcontentService.selectList(status.getStatus()));
	}
	
	
	@PostMapping(value="/updatesave")
	public HttpResult updatesave(@RequestBody SysUser record) 
	{
		return HttpResult.ok(sysUserService.updateUser(record));
	}
	
}
