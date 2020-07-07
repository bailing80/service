package com.louis.kitty.admin.sevice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.louis.kitty.admin.model.SysSales;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.service.CurdService;

/**
 * 客户管理管理
 * @author earl
 * @date august 8, 2019
 */
public interface SysSalesService extends CurdService<SysSales> {

	/**
	 * 根据名称查询
	 * @param lable
	 * @return
	 */
	List<SysSales> findByLable(String lable);


	List<SysSales> findMarPage();


	String batchImport(String fileName, MultipartFile file) throws Exception;

	SysSales findByName(String name);

	List<SysSales> findByFilters(PageRequest pageRequest);

}
