package com.louis.kitty.admin.sevice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.louis.kitty.admin.model.SysHospital;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.service.CurdService;

/**
 * 客户管理管理
 * @author earl
 * @date august 8, 2019
 */
public interface SysHospitalService extends CurdService<SysHospital> {

	/**
	 * 根据名称查询
	 * @param lable
	 * @return
	 */
	List<SysHospital> findByLable(String lable);


	List<SysHospital> findMarPage();


	String batchImport(String fileName, MultipartFile file) throws Exception;

	SysHospital findByName(String name);

	List<SysHospital> findByFilters(PageRequest pageRequest);

}
