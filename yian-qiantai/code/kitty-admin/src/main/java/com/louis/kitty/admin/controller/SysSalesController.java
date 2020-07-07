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

import com.louis.kitty.admin.model.SysSales;
import com.louis.kitty.admin.sevice.SysSalesService;
import com.louis.kitty.core.http.HttpResult;
import com.louis.kitty.core.page.PageRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 秋季活动
 * 
 * @author hgliu
 * @date October 6, 2019
 */
@Slf4j
@RestController
@RequestMapping("sales")
public class SysSalesController {

	private static final Logger log = LoggerFactory.getLogger(SysSalesController.class);

	@Autowired
	private SysSalesService salesService;

	@PreAuthorize("hasAuthority('sys:sales:add') AND hasAuthority('sys:sales:edit')")
	@PostMapping(value = "/save")
	public HttpResult save(@RequestBody SysSales record) {
		int result = salesService.save(record);
		if(1 == result)
			return HttpResult.ok("添加成功");
		else if(2 == result)
			return HttpResult.ok("添加失败，该销售人员已存在");
		else if(3 == result)
			return HttpResult.ok("编辑成功");
		else if(4 == result)
			return HttpResult.ok("编辑失败，该销售人员不存在");
		else 
			return HttpResult.ok("未知错误！");
	}

	@PreAuthorize("hasAuthority('sys:sales:delete')")
	@PostMapping(value = "/delete")
	public HttpResult delete(@RequestBody List<SysSales> records) {
		return HttpResult.ok(salesService.delete(records));
	}
	
	@PreAuthorize("hasAuthority('sys:sales:view')")
	@PostMapping(value = "/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		log.info("列表查询");
		return HttpResult.ok(salesService.findPage(pageRequest));
	}

	@PreAuthorize("hasAuthority('sys:sales:view')")
	@GetMapping(value = "/findByLable")
	public HttpResult findByLable(@RequestParam String name) {
		// log.info("根据销售人员名称条件查询");
		return HttpResult.ok(salesService.findByLable(name));
	}

	@PreAuthorize("hasAuthority('sys:sales:exportSalesInfo')")
	@PostMapping(value = "/exportSalesInfo")
	public HttpResult  exportSalesInfo(@RequestBody PageRequest pageRequest)
	{
		return HttpResult.ok(salesService.findByFilters(pageRequest)); 
	}

	@PreAuthorize("hasAuthority('sys:sales:importSalesInfo')")
	@PostMapping(value = "/importSalesInfo")
	public HttpResult  importSalesInfo(MultipartFile file)throws Exception
	{
  		log.info(" 导入 --file--");
  		String fileName = file.getOriginalFilename();
		log.info(" -        -fileName--:"+fileName);
		String str=salesService.batchImport(fileName, file);
		log.info(" --file--end:"+str);
		return HttpResult.ok(str);
	}
}
