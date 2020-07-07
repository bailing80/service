package com.louis.kitty.admin.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.louis.kitty.admin.model.CardPersonal;
import com.louis.kitty.admin.model.CardPersonalDetails;
import com.louis.kitty.admin.sevice.CardPersonalService;
import com.louis.kitty.admin.sevice.CardXuliehaoService;
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
@RequestMapping("cardpersonal")
public class CardPersonalController {
	
	private static final Logger log = LoggerFactory.getLogger(CardPersonalController.class);

	@Autowired
	private CardXuliehaoService cardproductService;
	@Autowired
	private CardPersonalService cardpersonalService;

	@PreAuthorize("hasAuthority('sys:cardpersonal:add') AND hasAuthority('sys:cardpersonal:edit')")
	@PostMapping(value = "/save")
	public HttpResult save(@RequestBody CardPersonalDetails record) {
		int result = cardpersonalService.save(record);
		if(1 == result)
			return HttpResult.ok("添加成功");
		else if(2 == result)
			return HttpResult.ok("添加失败，该机器已注册");
		else if(3 == result)
			return HttpResult.ok("编辑成功");
		else if(4 == result)
			return HttpResult.ok("编辑失败，该客户不存在");
		else 
			return HttpResult.ok("未知错误！");
	}

	@PreAuthorize("hasAuthority('sys:cardpersonal:delete')")
	@PostMapping(value = "/delete")
	public HttpResult delete(@RequestBody List<CardPersonal> records) {
		//log.info("Enter Controller delete function:");
		//log.info("size:" + records.size());
		//log.info("id:" + records.get(0).getId() + ",customerode:" + records.get(0).getCustomercode() + ",name:" + records.get(0).getName());
		return HttpResult.ok(cardpersonalService.delete(records));
	}

	@PreAuthorize("hasAuthority('sys:cardpersonal:view')")
	@PostMapping(value = "/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		// log.info("列表查询");
		return HttpResult.ok(cardpersonalService.findPage(pageRequest));
	}

	@PreAuthorize("hasAuthority('sys:cardpersonal:view')")
	@GetMapping(value = "/findByLable")
	public HttpResult findByLable(@RequestParam String name) {
		return HttpResult.ok(cardpersonalService.findByLable(name));
	}
	
	@PreAuthorize("hasAuthority('sys:cardpersonal:getDetails')")
	@PostMapping(value = "/getDetails")
	public HttpResult getDetails(@RequestBody Long id) {
		log.info("Id:" + id);
		//return HttpResult.ok();
		return HttpResult.ok(cardpersonalService.getDetailsById(id));
	}

	@PreAuthorize("hasAuthority('sys:cardpersonal:delete')")
	@PostMapping(value = "/batchExport")
	public HttpResult batchExport(@RequestBody List<CardPersonal> records) {
		log.info("Enter Controller batchExport function:");
		log.info("size:" + records.size());
		//log.info("id:" + records.get(0).getId() + ",customerode:" + records.get(0).getCustomercode() + ",name:" + records.get(0).getName());
		return HttpResult.ok(cardpersonalService.batchExport(records));
	}
	
	@PreAuthorize("hasAuthority('sys:cardpersonal:exportExcel')")
	@PostMapping(value = "/exportExcel")
	public HttpResult exportExcel(@RequestBody PageRequest pageRequest)
	{
		return HttpResult.ok(cardpersonalService.findAllByFilters(pageRequest)); 
	}

	@PreAuthorize("hasAuthority('sys:cardpersonal:importExcel')")
	@PostMapping(value = "/importExcel")
	public HttpResult  importExcel(MultipartFile file)throws Exception
	{
  		log.info(" 导入 --file--");
		String fileName = file.getOriginalFilename();
		log.info(" --fileName--:"+fileName);
		String str=cardpersonalService.batchImport(fileName, file);
		log.info(" --file--end:"+str);
		return HttpResult.ok(str);
	}
	
	@PostMapping(value = "/sendMessage")
	public HttpResult sendMessage(@RequestBody CardPersonalDetails record)throws Exception
	{
		String smessage="";
		log.info("手机号码:"+record.getTelephone());
		
		
		int mseid=(int)((Math.random()*9+1)*1000);
		
		/*
		 * String
		 * ssmessage=cardpersonalService.Smsmessage(record.getTelephone(),String.valueOf
		 * (mseid)); log.info("ssmessage++"+ssmessage); int
		 * ismessagen=Integer.parseInt(ssmessage); if(ismessagen < 0) {
		 * smessage=ssmessage; }else { smessage=String.valueOf(mseid); }
		 */
		return HttpResult.ok(smessage);
	}

	/**
	 * - 产品对应管理
	 * @param serialnumber
	 * @return
	 * @throws Exception 
	 */
	
	@PostMapping(value = "/findXuliehao")
	public HttpResult findXuliehao(@RequestBody String productmodel) throws Exception 
	{
		return HttpResult.ok(cardproductService.findxuliehao(productmodel.toString()));
	}
	
}
