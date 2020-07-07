package com.louis.kitty.admin.sevice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.louis.kitty.admin.model.SysHospital;
import com.louis.kitty.admin.model.SysMarket;
import com.louis.kitty.admin.model.SysSales;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;
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
	
	PageResult nameDuplicateCheck(PageRequest pageRequest);
	
	PageResult telDuplicateCheck(PageRequest pageRequest);

	String batchImport(String fileName, MultipartFile file) throws Exception;

	List<SysMarket> findByFilters(PageRequest pageRequest);

	List<SysSales> getSalesNames();
	
	List<SysHospital> getHospitalNames(String keyName);
}
