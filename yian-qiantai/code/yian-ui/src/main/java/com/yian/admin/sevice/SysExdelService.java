package com.yian.admin.sevice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yian.admin.model.SysExdel;
import com.yian.admin.model.SysMarket;
import com.yian.admin.model.SysClientAndExpress;
import com.louis.kitty.core.service.CurdService;

/**
 * 快递管理
 * @author huigai
 * @date august 22, 2019
 */
public interface SysExdelService extends CurdService<SysExdel> {

	List<SysExdel> findByLable(String lable);

	List<SysExdel> findMarPage();
	
	int queryExpInfo(SysExdel exdel);
	
	List<SysClientAndExpress> leadout();
	
	void syncMarketToExdel();

	String batchImport(String fileName, MultipartFile file) throws Exception;

}
