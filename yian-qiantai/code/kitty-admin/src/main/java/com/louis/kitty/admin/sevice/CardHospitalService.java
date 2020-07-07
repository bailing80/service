package com.louis.kitty.admin.sevice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.louis.kitty.admin.model.CardHospital;
import com.louis.kitty.admin.model.CardHospitalDetails;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;
import com.louis.kitty.core.service.CurdService;

/**
 * 电子保修卡
 * 医院客户信息管理
 * @author hgliu
 * @date October 12, 2019
 */
public interface CardHospitalService extends CurdService<CardHospital> {

	List<CardHospital> findByLable(String lable);

	List<CardHospital> findMarPage();

	String batchImport(String fileName, MultipartFile file) throws Exception;

	List<CardHospitalDetails> findAllByFilters(PageRequest pageRequest);
	
	List<CardHospitalDetails> batchExport(List<CardHospital> records);

	int save(CardHospitalDetails record);
	
	CardHospitalDetails getDetailsById(Long id);
}
