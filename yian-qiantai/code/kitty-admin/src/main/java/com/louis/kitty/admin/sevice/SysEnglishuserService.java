package com.louis.kitty.admin.sevice;


import com.louis.kitty.admin.model.SysEnglishuser;
import com.louis.kitty.core.service.CurdService;


public interface SysEnglishuserService extends CurdService<SysEnglishuser> 
{

	SysEnglishuser findByName(String phone);
	int updateByPrimaryKeySelective(SysEnglishuser record);
}
