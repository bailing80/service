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

import com.louis.kitty.admin.model.SysHospital;
import com.louis.kitty.admin.sevice.SysHospitalService;
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
@RequestMapping("hospital")
public class SysHospitalController {

	private static final Logger log = LoggerFactory.getLogger(SysHospitalController.class);

	@Autowired
	private SysHospitalService hospitalService;

	/**
	 * 
	 * @param record
	 * @return
	 */
	@PreAuthorize("hasAuthority('sys:hospital:add') AND hasAuthority('sys:hospital:edit')")
	@PostMapping(value = "/save")
	public HttpResult save(@RequestBody SysHospital record) {
		int result = hospitalService.save(record);
		if(1 == result)
			return HttpResult.ok("添加成功");
		else if(2 == result)
			return HttpResult.ok("添加失败，该医院已存在");
		else if(3 == result)
			return HttpResult.ok("编辑成功");
		else if(4 == result)
			return HttpResult.ok("编辑失败，该医院不存在");
		else 
			return HttpResult.ok("未知错误！");
	}

	@PreAuthorize("hasAuthority('sys:hospital:delete')")
	@PostMapping(value = "/delete")
	public HttpResult delete(@RequestBody List<SysHospital> records) {
		return HttpResult.ok(hospitalService.delete(records));
	}
	
	@PreAuthorize("hasAuthority('sys:hospital:view')")
	@PostMapping(value = "/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		log.info("列表查询");
		return HttpResult.ok(hospitalService.findPage(pageRequest));
	}

	@PreAuthorize("hasAuthority('sys:hospital:view')")
	@GetMapping(value = "/findByLable")
	public HttpResult findByLable(@RequestParam String name) {
		// log.info("根据医院名称条件查询");
		return HttpResult.ok(hospitalService.findByLable(name));
	}

	@PreAuthorize("hasAuthority('sys:hospital:exportHospitalInfo')")
	@PostMapping(value = "/exportHospitalInfo")
	public HttpResult  exportHospitalInfo(@RequestBody PageRequest pageRequest)
	{
		return HttpResult.ok(hospitalService.findByFilters(pageRequest)); 
	}

	@PreAuthorize("hasAuthority('sys:hospital:importHospitalInfo')")
	@PostMapping(value = "/importHospitalInfo")
	public HttpResult  importHospitalInfo(MultipartFile file)throws Exception
	{
  		log.info(" 导入 --file--");
  		String fileName = file.getOriginalFilename();
		log.info(" -        -fileName--:"+fileName);
		String str=hospitalService.batchImport(fileName, file);
		log.info(" --file--end:"+str);
		return HttpResult.ok(str);
	}
}
