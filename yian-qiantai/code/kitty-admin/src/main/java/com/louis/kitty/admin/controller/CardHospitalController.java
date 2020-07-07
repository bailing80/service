package com.louis.kitty.admin.controller;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.louis.kitty.admin.model.CardHospital;
import com.louis.kitty.admin.model.CardHospitalDetails;
import com.louis.kitty.admin.sevice.CardHospitalService;
import com.louis.kitty.core.http.HttpResult;
import com.louis.kitty.core.page.PageRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 电子保修卡
 * 
 * @author hgliu
 * @date October 12, 2019
 */
@Slf4j
@RestController
@RequestMapping("cardhospital")
public class CardHospitalController {

	private static final Logger log = LoggerFactory.getLogger(CardHospitalController.class);

	@Autowired
	private CardHospitalService cardHospitalService;

	@PreAuthorize("hasAuthority('sys:cardhospital:add') AND hasAuthority('sys:cardhospital:edit')")
	@PostMapping(value = "/save")
	public HttpResult save(@RequestBody CardHospitalDetails record) {
		int result = cardHospitalService.save(record);
		if(1 == result)
			return HttpResult.ok("添加成功");
		else if(2 == result)
			return HttpResult.ok("添加失败，该客户已存在");
		else if(3 == result)
			return HttpResult.ok("编辑成功");
		else if(4 == result)
			return HttpResult.ok("编辑失败，该客户不存在");
		else 
			return HttpResult.ok("未知错误！");
	}

	@PreAuthorize("hasAuthority('sys:cardhospital:delete')")
	@PostMapping(value = "/delete")
	public HttpResult delete(@RequestBody List<CardHospital> records) {
		//log.info("Enter Controller delete function:");
		//log.info("size:" + records.size());
		//log.info("id:" + records.get(0).getId() + ",customerode:" + records.get(0).getCustomercode() + ",name:" + records.get(0).getName());
		return HttpResult.ok(cardHospitalService.delete(records));
	}

	@PreAuthorize("hasAuthority('sys:cardhospital:view')")
	@PostMapping(value = "/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		// log.info("列表查询");
		return HttpResult.ok(cardHospitalService.findPage(pageRequest));
	}

	@PreAuthorize("hasAuthority('sys:cardhospital:view')")
	@GetMapping(value = "/findByLable")
	public HttpResult findByLable(@RequestParam String name) {
		return HttpResult.ok(cardHospitalService.findByLable(name));
	}
	
	@PreAuthorize("hasAuthority('sys:cardhospital:getDetails')")
	@PostMapping(value = "/getDetails")
	public HttpResult getDetails(@RequestBody Long id) {
		log.info("Id:" + id);
		//return HttpResult.ok();
		return HttpResult.ok(cardHospitalService.getDetailsById(id));
	}

	@PreAuthorize("hasAuthority('sys:cardhospital:delete')")
	@PostMapping(value = "/batchExport")
	public HttpResult batchExport(@RequestBody List<CardHospital> records) {
		log.info("Enter Controller batchExport function:");
		log.info("size:" + records.size());
		//log.info("id:" + records.get(0).getId() + ",customerode:" + records.get(0).getCustomercode() + ",name:" + records.get(0).getName());
		return HttpResult.ok(cardHospitalService.batchExport(records));
	}
	
	@PreAuthorize("hasAuthority('sys:cardhospital:exportExcel')")
	@PostMapping(value = "/exportExcel")
	public HttpResult exportExcel(@RequestBody PageRequest pageRequest)
	{
		return HttpResult.ok(cardHospitalService.findAllByFilters(pageRequest)); 
	}

	@PreAuthorize("hasAuthority('sys:cardhospital:importExcel')")
	@PostMapping(value = "/importExcel")
	public HttpResult  importExcel(MultipartFile file)throws Exception
	{
  		log.info(" 导入 --file--");
		String fileName = file.getOriginalFilename();
		log.info(" --fileName--:"+fileName);
		String str=cardHospitalService.batchImport(fileName, file);
		log.info(" --file--end:"+str);
		return HttpResult.ok(str);
	}
}
