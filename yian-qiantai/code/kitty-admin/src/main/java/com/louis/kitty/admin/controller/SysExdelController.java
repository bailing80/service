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

import com.louis.kitty.admin.model.SysExdel;
import com.louis.kitty.admin.sevice.SysExdelService;
import com.louis.kitty.admin.model.SysMarket;
import com.louis.kitty.core.http.HttpResult;
import com.louis.kitty.core.page.PageRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 冬季活动
 * 
 * @author huigai
 * @date august 20, 2019
 */
@Slf4j
@RestController
@RequestMapping("exdel")
public class SysExdelController {

	private static final Logger log = LoggerFactory.getLogger(SysExdelController.class);

	@Autowired
	private SysExdelService exdelService;

	@PreAuthorize("hasAuthority('sys:exdel:add') AND hasAuthority('sys:mark:edit')")
	@PostMapping(value = "/save")
	public HttpResult save(@RequestBody SysExdel record) {
		log.info("Enter SysExdelController save function:");
		int result = exdelService.save(record);
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
	
	@PreAuthorize("hasAuthority('sys:exdel:query')")
	@PostMapping(value = "/query")
	public HttpResult query() {
		log.info("Enter SysExdelController query function:");
		return HttpResult.ok(exdelService.query());
	}

	@PreAuthorize("hasAuthority('sys:exdel:delete')")
	@PostMapping(value = "/delete")
	public HttpResult delete(@RequestBody List<SysExdel> records) {
		return HttpResult.ok(exdelService.delete(records));
	}
	
	@PreAuthorize("hasAuthority('sys:exdel:view')")
	@PostMapping(value = "/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		// log.info("列表查询");
		return HttpResult.ok(exdelService.findPage(pageRequest));
	}

	@PreAuthorize("hasAuthority('sys:exdel:view')")
	@GetMapping(value = "/findByLable")
	public HttpResult findByLable(@RequestParam String name) {
		// log.info("根据医院名称条件查询");
		return HttpResult.ok(exdelService.findByLable(name));
	}

	@PreAuthorize("hasAuthority('sys:exdel:leadin')")
	@PostMapping(value = "/leadin")
	public HttpResult leadin(MultipartFile file) throws Exception
	{
		return HttpResult.ok(exdelService.batchImport(file.getOriginalFilename(), file));
	}
	@PreAuthorize("hasAuthority('sys:exdel:leadout')")
	@PostMapping(value = "/leadout")
	public HttpResult leadout()
	{
  		log.info("Enter SysExdelController leadout function:");
		return HttpResult.ok(exdelService.syncMarketToExdel());
		//return HttpResult.ok(exdelService.leadout());
	}
	@PreAuthorize("hasAuthority('sys:exdel:exportExp')")
	@PostMapping(value = "/exportExp")
	public HttpResult exportExp(@RequestBody PageRequest pageRequest) {
		log.info("Enter SysExdelController exportExp function:");
		return HttpResult.ok(exdelService.exportExp(pageRequest));
	}
}
