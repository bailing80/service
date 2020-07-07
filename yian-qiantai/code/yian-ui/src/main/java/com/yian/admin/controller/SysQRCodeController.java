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
import com.yian.admin.model.SysQRCode;
import com.yian.admin.sevice.SysQRCodeService;
import com.louis.kitty.core.http.HttpResult;
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
/*
	@PreAuthorize("hasAuthority('sys:qrcode:add') AND hasAuthority('sys:qrcode:edit')")
	@PostMapping(value = "/importQrcode")
	public HttpResult importQrcode(MultipartFile file,@RequestParam String name) throws Exception {
		log.info("Enter SysQRCodeController importQrcode function:");
		log.info("`````:"+name);
		return HttpResult.ok(qrcodeService.importQrcode(file, file.getOriginalFilename()));
	}
	*/
	@PreAuthorize("hasAuthority('sys:qrcode:add') AND hasAuthority('sys:qrcode:edit')")/// 
	@PostMapping(value = "/save")
	public HttpResult save(@RequestParam String name, MultipartFile file) throws Exception {
		log.info("Enter SysQRCodeController save function:");
		return HttpResult.ok(qrcodeService.importQrcode(file, name));
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
		/*
		 * SysQRCode sysQRCode= new SysQRCode(); PageResult
		 * pageResult=qrcodeService.findPage(pageRequest); 
		 * List<SysQRCode> stlist=new ArrayList<>(); 
		 * sysQRCode.setCodetext(qrcodeService.StrResult());
		 * stlist.add(sysQRCode);
		 * log.info("-------------------------------: "+qrcodeService.StrResult());
		 */
		return HttpResult.ok(qrcodeService.findPage(pageRequest));
	}

	@PreAuthorize("hasAuthority('sys:qrcode:view')")
	@GetMapping(value = "/findByLable")
	public HttpResult findByLable(@RequestParam String name) {
		return HttpResult.ok(qrcodeService.findByLable(name));
	}

}
