package com.louis.kitty.admin.sevice;


import java.util.List;

import com.louis.kitty.admin.model.SysEngcontent;
import com.louis.kitty.core.service.CurdService;


public interface SysEngcontentService extends CurdService<SysEngcontent> 
{

	List<SysEngcontent> selectList(String status);
}
