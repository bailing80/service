package com.yian.admin.controller;


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
import com.yian.admin.model.SysExdel;
import com.yian.admin.sevice.SysExdelService;
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
		return HttpResult.ok(exdelService.save(record));
	}
	
	@PreAuthorize("hasAuthority('sys:exdel:queryExpInfo')")
	@PostMapping(value = "/queryExpInfo")
	public HttpResult queryExpInfo(@RequestBody SysExdel record) {
		log.info("Enter SysExdelController queryExpInfo function:");
		return HttpResult.ok(exdelService.queryExpInfo(record));
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
		return HttpResult.ok(exdelService.leadout());
	}
}
