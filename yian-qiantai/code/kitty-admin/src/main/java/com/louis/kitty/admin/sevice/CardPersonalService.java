package com.louis.kitty.admin.sevice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.louis.kitty.admin.model.CardPersonal;
import com.louis.kitty.admin.model.CardPersonalDetails;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;
import com.louis.kitty.core.service.CurdService;

/**
 * 电子保修卡
 * 个人客户信息管理
 * @author hgliu
 * @date October 12, 2019
 */
public interface CardPersonalService extends CurdService<CardPersonal> {

	List<CardPersonal> findByLable(String lable);

	List<CardPersonal> findMarPage();

	String batchImport(String fileName, MultipartFile file) throws Exception;

	List<CardPersonalDetails> findAllByFilters(PageRequest pageRequest);
	
	List<CardPersonalDetails> batchExport(List<CardPersonal> records);

	int save(CardPersonalDetails record);
	
	CardPersonalDetails getDetailsById(Long id);
	

}
