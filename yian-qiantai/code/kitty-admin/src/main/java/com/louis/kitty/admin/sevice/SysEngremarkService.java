package com.louis.kitty.admin.sevice;


import com.louis.kitty.admin.model.SysEngremark;
import com.louis.kitty.core.service.CurdService;


public interface SysEngremarkService extends CurdService<SysEngremark> 
{

	int save(SysEngremark record);

	
}
