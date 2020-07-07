package com.louis.kitty.admin.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
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

import com.louis.kitty.admin.model.SysMarket;
import com.louis.kitty.admin.sevice.SysMarketService;
import com.louis.kitty.core.http.HttpResult;
import com.louis.kitty.core.page.PageRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 秋季活动
 * 
 * @author earl
 * @date august 8, 2019
 */
@Slf4j
@RestController
@RequestMapping("mark")
public class SysMarketController {

	private static final Logger log = LoggerFactory.getLogger(SysMarketController.class);

	@Autowired
	private SysMarketService marketService;

	/**
	 * 
	 * @param record
	 * @return
	 */
	@PreAuthorize("hasAuthority('sys:mark:add') AND hasAuthority('sys:mark:edit')")
	@PostMapping(value = "/save")
	public HttpResult save(@RequestBody SysMarket record) {
		int result = marketService.save(record);
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

	@PreAuthorize("hasAuthority('sys:mark:delete')")
	@PostMapping(value = "/delete")
	public HttpResult delete(@RequestBody List<SysMarket> records) {
		return HttpResult.ok(marketService.delete(records));
	}
	
	@PostMapping(value = "/getHospitalNames")
	public HttpResult getHospitalNames(@RequestBody String name) {
		log.info("Test ---- name:" + name);
		return HttpResult.ok(marketService.getHospitalNames(name));
	}
	
	@PostMapping(value = "/getSalesNames")
	public HttpResult getSalesNames() {
		return HttpResult.ok(marketService.getSalesNames());
	}

	@PreAuthorize("hasAuthority('sys:mark:view')")
	@PostMapping(value = "/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		// log.info("列表查询");
		return HttpResult.ok(marketService.findPage(pageRequest));
	}
	@PreAuthorize("hasAuthority('sys:mark:nameDuplicateCheck')")
	@PostMapping(value = "/nameDuplicateCheck")
	public HttpResult nameDuplicateCheck(@RequestBody PageRequest pageRequest) {
		log.info("姓名去重");
		return HttpResult.ok(marketService.nameDuplicateCheck(pageRequest));
	}
	@PreAuthorize("hasAuthority('sys:mark:telDuplicateCheck')")
	@PostMapping(value = "/telDuplicateCheck")
	public HttpResult telDuplicateCheck(@RequestBody PageRequest pageRequest) {
		log.info("手机号去重");
		return HttpResult.ok(marketService.telDuplicateCheck(pageRequest));
	}

	@PreAuthorize("hasAuthority('sys:mark:view')")
	@GetMapping(value = "/findByLable")
	public HttpResult findByLable(@RequestParam String name) {
		// log.info("根据医院名称条件查询");
		return HttpResult.ok(marketService.findByLable(name));
	}

	@PreAuthorize("hasAuthority('sys:mark:leadout')")
	@PostMapping(value = "/leadGout")
	public HttpResult  leadGout(@RequestBody PageRequest pageRequest)
	{
		return HttpResult.ok(marketService.findByFilters(pageRequest)); 
	}

	@PreAuthorize("hasAuthority('sys:mark:leadout')")
	@PostMapping(value = "/leadgin")
	//@RequestMapping(value = "/leadgin", method = RequestMethod.POST)
	public HttpResult  leadgin(MultipartFile file)throws Exception
	{
  		log.info(" 导入 --file--");
		String fileName = file.getOriginalFilename();
		log.info(" -        -fileName--:"+fileName);
		//log.info(" -          -file--:"+file);
		String str=marketService.batchImport(fileName, file);
		log.info(" --file--end:"+str);
		return HttpResult.ok(str);
	}
}
