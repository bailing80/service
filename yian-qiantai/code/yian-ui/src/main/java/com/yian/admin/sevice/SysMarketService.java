package com.yian.admin.sevice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yian.admin.model.SysMarket;
import com.yian.admin.model.SysSalesman;
import com.louis.kitty.core.service.CurdService;

/**
 * 客户管理管理
 * @author earl
 * @date august 8, 2019
 */
public interface SysMarketService extends CurdService<SysMarket> {

	/**
	 * 根据名称查询
	 * @param lable
	 * @return
	 */
	List<SysMarket> findByLable(String lable);


	List<SysMarket> findMarPage();


	String batchImport(String fileName, MultipartFile file) throws Exception;

	String vueSave(SysMarket record);
	
	List<SysSalesman> getSalesNames();
}
