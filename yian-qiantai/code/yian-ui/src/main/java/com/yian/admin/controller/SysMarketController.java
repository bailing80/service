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
import com.yian.admin.model.SysMarket;
import com.yian.admin.sevice.SysMarketService;
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
	 * 前端采集客户名称及手机号
	 * @param record
	 * @return
	 */
	@PostMapping(value = "/vuesave")
	public HttpResult vuesave(@RequestBody SysMarket record)
	{
		return HttpResult.ok(marketService.vueSave(record));
	}
	/**
	 * 
	 * @param record
	 * @return
	 */
	@PreAuthorize("hasAuthority('sys:mark:add') AND hasAuthority('sys:mark:edit')")
	@PostMapping(value = "/save")
	public HttpResult save(@RequestBody SysMarket record) {
		return HttpResult.ok(marketService.save(record));
	}

	@PreAuthorize("hasAuthority('sys:mark:delete')")
	@PostMapping(value = "/delete")
	public HttpResult delete(@RequestBody List<SysMarket> records) {
		return HttpResult.ok(marketService.delete(records));
	}

	@PreAuthorize("hasAuthority('sys:mark:view')")
	@PostMapping(value = "/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		// log.info("列表查询");
		return HttpResult.ok(marketService.findPage(pageRequest));
	}

	@PreAuthorize("hasAuthority('sys:mark:view')")
	@GetMapping(value = "/findByLable")
	public HttpResult findByLable(@RequestParam String name) {
		// log.info("根据医院名称条件查询");
		return HttpResult.ok(marketService.findByLable(name));
	}

	@PreAuthorize("hasAuthority('sys:mark:leadout')")
	@PostMapping(value = "/leadGout")
	public HttpResult  leadGout()//HttpResult @RequestBody PageRequest pageRequest
	{
		//log.info("---name:"+name);
		//log.info("--Controller---"+pageRequest.getPageSize()+" name:"+ pageRequest.getColumnFilter("name"));//+ pageRequest.getColumnFilter("name")
		//log.info("response:"+response.getStatus());
		return HttpResult.ok(marketService.findMarPage());
		/**try {
			
			 List<SysMarket> marketList =marketService.findByLable(null) ; 
			 HSSFWorkbook wb= new HSSFWorkbook(); 
			 HSSFSheet sheet = wb.createSheet("客户信息列表");
			 HSSFRow row = null; 
			 row = sheet.createRow(0);//创建第一个单元格 
			 row.setHeight((short) (26.25 *
			 20)); row.createCell(0).setCellValue("委托方信息列表");//为第一行单元格设值
			 //为标题设计空间/firstRow从第1行开始/ lastRow从第0行结束/从第1个单元格开始/ 从第3个单元格结束 
			
			 CellRangeAddress columnRegion = new
			 CellRangeAddress(1,4,0,0); 
			 sheet.addMergedRegion(columnRegion); 
			 //第一个table_name表名字/ 第二个table_name 数据库名称 
			 row = sheet.createRow(1); 
			 row.setHeight((short)(22.50 * 20));//设置行高 
			 row.createCell(0).setCellValue("ID");
			 row.createCell(1).setCellValue("logo");
			 row.createCell(2).setCellValue("医院名称");
			 row.createCell(3).setCellValue("手机号"); 
			 for (int i = 0; i < marketList.size();i++) { 
				 row = sheet.createRow(i + 2); 
				 SysMarket company = marketList.get(i);
				 row.createCell(0).setCellValue(company.getId());
				 row.createCell(1).setCellValue(company.getName());
				 row.createCell(2).setCellValue(company.getTel()); 
				}
				sheet.setDefaultRowHeight((short) (16.5 * 20)); //列宽自适应 
				for (int i = 0; i <=13; i++) { 
					sheet.autoSizeColumn(i); 
				} 
					String title= "markclient"; 
					String fileName = new String(title.getBytes("GB2312"), "ISO_8859_1"); 
					fileName =URLEncoder.encode(fileName,"utf-8");
					response.setContentType("application/vnd.ms-excel;charset=utf-8");
					OutputStream os = response.getOutputStream();
					response.setHeader("Content-disposition","attachment;filename="+fileName+".xls");
					//默认Excel名称 
					wb.write(os); 
					os.flush();
					os.close();					
		} catch (Exception e) {
			e.printStackTrace();
		}**/
     
	}

	

	@PreAuthorize("hasAuthority('sys:mark:leadout')")
	@PostMapping(value = "/leadgin")
	//@RequestMapping(value = "/leadgin", method = RequestMethod.POST)
	public HttpResult  leadgin(MultipartFile file)throws Exception
	{
  		log.info(" 导入 --file--");
		 String fileName = file.getOriginalFilename();
		 log.info(" -        -fileName--:"+fileName);
		 log.info(" -          -file--:"+file);
		 String str=marketService.batchImport(fileName, file);
		 log.info(" --file--end:"+str);
		 
		// List<SysMarket> list = ExcelUtils.readExcel("", SysMarket.class, file);
		 //list.forEach(b -> log.info(JSON.toJSONString(b));
		return HttpResult.ok(str);
	}
}
