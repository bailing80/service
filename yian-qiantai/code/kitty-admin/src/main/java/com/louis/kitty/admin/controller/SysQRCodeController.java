package com.louis.kitty.admin.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
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

import com.louis.kitty.admin.model.SysQRCode;
import com.louis.kitty.admin.sevice.SysQRCodeService;
import com.louis.kitty.common.utils.StringUtils;
import com.louis.kitty.admin.model.SysMarket;
import com.louis.kitty.core.http.HttpResult;
import com.louis.kitty.core.page.ColumnFilter;
import com.louis.kitty.core.page.PageRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * 二维码管理 
 * @author huigai
 * @date august 23, 2019
 */
@Slf4j
@RestController
@RequestMapping("qrcode")
public class SysQRCodeController {

	private static final Logger log = LoggerFactory.getLogger(SysQRCodeController.class);

	@Autowired
	private SysQRCodeService qrcodeService;

	@PreAuthorize("hasAuthority('sys:qrcode:add') AND hasAuthority('sys:qrcode:edit')")/// 
	@PostMapping(value = "/save")
	public HttpResult save(@RequestParam String name, String stats, MultipartFile file) throws Exception {
		log.info("Enter SysQRCodeController save function:");
		//log.info("name:" + name + ",stats:" + stats +"file:" + file.getOriginalFilename());
		int result = qrcodeService.importQrcode(file, name, stats);
		if(result == 1)
			return HttpResult.ok("成功添加二维码到数据库中");
		else if(result == 2)
			return HttpResult.ok("成功更新数据库");
		else 
			return HttpResult.error("未知错误");
		
		
		//return HttpResult.ok(result);
	}

	@PreAuthorize("hasAuthority('sys:qrcode:delete')")
	@PostMapping(value = "/delete")
	public HttpResult delete(@RequestBody List<SysQRCode> records) {
		log.info("Enter SysQRCodeController delete function:");
		return HttpResult.ok(qrcodeService.delete(records));
	}
	
	@PreAuthorize("hasAuthority('sys:qrcode:view')")
	@PostMapping(value = "/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		log.info("Enter SysQRCodeController view function:");	
		return HttpResult.ok(qrcodeService.findPage(pageRequest));
	}

	@PreAuthorize("hasAuthority('sys:qrcode:view')")
	@GetMapping(value = "/findByLable")
	public HttpResult findByLable(@RequestParam String name) {
		return HttpResult.ok(qrcodeService.findByLable(name));
	}

}
