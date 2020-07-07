package com.louis.kitty.admin.sevice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.louis.kitty.admin.model.SysExdel;
import com.louis.kitty.admin.model.SysMarket;
import com.louis.kitty.admin.model.SysClientAndExpress;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.service.CurdService;

/**
 * 快递管理
 * @author huigai
 * @date august 22, 2019
 */
public interface SysExdelService extends CurdService<SysExdel> {

	List<SysExdel> findByLable(String lable);

	List<SysExdel> findMarPage();
	
	int query();
	
	List<SysClientAndExpress> leadout();
	
	int syncMarketToExdel();

	String batchImport(String fileName, MultipartFile file) throws Exception;
	
	List<SysExdel> exportExp(PageRequest pageRequest);

}
