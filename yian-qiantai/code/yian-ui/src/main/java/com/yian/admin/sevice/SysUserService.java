package com.yian.admin.sevice;


import com.yian.admin.model.SysUser;
import com.louis.kitty.core.service.CurdService;

/**
 * 用户管理
 * @author earl
 * @date Oct 29, 2018
 */
public interface SysUserService extends CurdService<SysUser> {

	SysUser findByName(String username);



}
