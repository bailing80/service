package com.louis.kitty.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.louis.kitty.admin.model.SysEngcontent;


public interface SysEngcontentMapper 
{
	List<SysEngcontent> selectList(@Param(value="status") String status);
}
