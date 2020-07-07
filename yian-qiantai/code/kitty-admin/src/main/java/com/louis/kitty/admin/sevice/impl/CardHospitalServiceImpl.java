package com.louis.kitty.admin.sevice.impl;


import java.io.InputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;	
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.louis.kitty.admin.dao.CardHospitalMapper;
import com.louis.kitty.admin.dao.CardProductConfigMapper;
import com.louis.kitty.admin.dao.CardProductInstallationMapper;
import com.louis.kitty.admin.model.CardHospital;
import com.louis.kitty.admin.model.CardHospitalDetails;
import com.louis.kitty.admin.model.CardProductConfig;
import com.louis.kitty.admin.model.CardProductInstallation;
import com.louis.kitty.admin.sevice.CardHospitalService;
import com.louis.kitty.admin.util.CustomerCodeUtil;
import com.louis.kitty.common.utils.DateTimeUtils;
import com.louis.kitty.core.page.ColumnFilter;
import com.louis.kitty.core.page.MybatisPageHelper;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;
import lombok.extern.slf4j.Slf4j;
/** 
   * @author hgliu
   * October 12 2019
   * 医院客户信息维护管理   
   * @update hgliu
   *  
 */

@Service
@Slf4j
public class CardHospitalServiceImpl  implements CardHospitalService {
    
	private static final Logger log = LoggerFactory.getLogger(CardHospitalServiceImpl.class);
	//private static final String tag = "HOSPITAL";
	//private CustomerCodeUtil util = new CustomerCodeUtil();

	@Autowired
	private CardHospitalMapper cardHospitalMapper;
	
	@Autowired
	private CardProductConfigMapper cardProductConfigMapper;
	
	@Autowired
	private CardProductInstallationMapper cardProductInstallationMapper;
	
	private final static String EXCEL_2003_DOWN = ".xls"; // 2003- 版本的excel
    private final static String EXCEL_2007_UP = ".xlsx"; // 2007+ 版本的excel

	@Override
	public int save(CardHospitalDetails record)
	{
		log.info("Enter CardHospitalServiceImpl save function:");
		//log.info(record.getName() + "," + record.getBirthday() + "," + record.getProductmodel());
		//分离出客户基本信息
		CardHospital hospital = new CardHospital();
		hospital.setName(record.getName());
		hospital.setAddress(record.getAddress());
		hospital.setContacts(record.getContacts());
		hospital.setOfficephone(record.getOfficephone());
		hospital.setTelephone(record.getTelephone());
		hospital.setOffice(record.getOffice());
		hospital.setOfficer(record.getOfficer());
		
		if(record.getId() == null || record.getId() == 0)//新增
		{
			CardHospital temp = cardHospitalMapper.findHospital(record.getSerialnumber());
			if(temp == null )
			{
				log.info("新增医院客户：客户名称" + record.getName() +" 手机号:"+record.getTelephone() );
				hospital.setCustomercode(getLatestCustomercode());
				hospital.setCreatetime(DateTimeUtils.getDateTime());
				cardHospitalMapper.insertSelective(hospital);
				//分离出产品购买信息并保存到数据库
				CardProductConfig productConfig = new CardProductConfig();
				productConfig.setCustomercode(hospital.getCustomercode());
				productConfig.setProductmodel(record.getProductmodel());
				productConfig.setSerialnumber(record.getSerialnumber());
				productConfig.setSellername(record.getSellername());
				productConfig.setPurchasedate(record.getPurchasedate());
				cardProductConfigMapper.insertSelective(productConfig);
				//分离出产品安装信息并保存到数据库
				CardProductInstallation productInstallation = new CardProductInstallation();
				productInstallation.setCustomercode(hospital.getCustomercode());
				productInstallation.setRequirements(record.getRequirements());
				productInstallation.setSelftest(record.getSelftest());
				productInstallation.setUsertraining(record.getUsertraining());
				productInstallation.setLogoplacement(record.getLogoplacement());
				productInstallation.setInstallconclusion(record.getInstallconclusion());
				productInstallation.setInstaller(record.getInstaller());
				productInstallation.setInstalldate(record.getInstalldate());
				productInstallation.setAcceptor(record.getAcceptor());
				productInstallation.setAcceptdate(record.getAcceptdate());
				cardProductInstallationMapper.insertSelective(productInstallation);
				return 1;//add an item successfully			
			}
			else {
				log.info("该客户已存在，新增失败。");
				return 2;//add failed
			}

		}
		else {//编辑
			CardHospital temp = cardHospitalMapper.findHospital(record.getSerialnumber());
			if(temp != null )
			{
				log.info("编辑医院客户：客户名称" + record.getName() +" 手机号:"+record.getTelephone());
				hospital.setId(temp.getId());
				hospital.setCustomercode(temp.getCustomercode());
				hospital.setCreateTime(temp.getCreateTime());
				cardHospitalMapper.updateByPrimaryKeySelective(hospital);
				return 3;//edit an item successfully
			}
			else {
				log.info("编辑失败，该客户不存在！");
				return 4;//edit failed
			}
		}
	}

	@Override
	public int delete(CardHospital record) {
		//log.info("record.customercode:" + record.getCustomercode());
		String customercode = cardHospitalMapper.selectByPrimaryKey(record.getId()).getCustomercode();
		cardProductConfigMapper.deleteByCustomercode(customercode);
		cardProductInstallationMapper.deleteByCustomercode(customercode);
		cardHospitalMapper.deleteByPrimaryKey(record.getId());
		return 1;
	}

	@Override
	public int delete(List<CardHospital> records) {
		log.info("Enter delete function:");
		for(CardHospital record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public CardHospital findById(Long id) {
		return cardHospitalMapper.selectByPrimaryKey(id);
	}
	
	private static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
		PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        //log.info("-----" + pageResult.getPageNum() + "," + pageResult.getPageSize() + "," + pageResult.getTotalSize() + "," + pageResult.getTotalPages());
        pageResult.setContent(pageInfo.getList());
		return pageResult;
	}
	
	@Override
	public PageResult findPage(PageRequest pageRequest) {
		ColumnFilter columnFilter = pageRequest.getColumnFilter("name");
		ColumnFilter columnFilter1 = pageRequest.getColumnFilter("telephone");
		ColumnFilter columnFilter2 = pageRequest.getColumnFilter("office");
		ColumnFilter columnFilter3 = pageRequest.getColumnFilter("address");
		ColumnFilter columnFilter4 = pageRequest.getColumnFilter("customercode");
		ColumnFilter columnFilter5 = pageRequest.getColumnFilter("startTime");
		ColumnFilter columnFilter6 = pageRequest.getColumnFilter("endTime");
		String name=columnFilter.getValue();
		String telephone = columnFilter1.getValue();
		String office = columnFilter2.getValue();
		String address = columnFilter3.getValue();
		String customercode = columnFilter4.getValue();
		String startTime = columnFilter5.getValue();
		String endTime = columnFilter6.getValue();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("telephone", telephone);
		map.put("office", office);
		map.put("address", address);
		map.put("customercode", customercode);
		map.put("startTime",startTime);
		map.put("endTime",  endTime);
		log.info("startTime:" + columnFilter5.getValue() + ",endTime:" + columnFilter6.getValue());
		log.info("filters: customercode_" + customercode + ", name_" + name + ", telephone_" + telephone + ", office_" + office 
				+ ",address_" + address);

		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		//int currIndex = (pageRequest.getPageNum() - 1) * pageRequest.getPageSize();
		//log.info("~~~~~~  pageNum:" + pageNum + ", currIndex:" + currIndex + ", pageSize:" + pageSize);
		PageHelper.startPage(pageNum, pageSize);
		List<CardHospital> records = cardHospitalMapper.findPageByFilters(map);
		Object result = records;//ReflectionUtils.invoke(mapper, queryMethodName, args);
		return getPageResult(pageRequest, new PageInfo((List) result));
/*		
		ColumnFilter columnFilter = pageRequest.getColumnFilter("name");
		
		if(columnFilter != null) {
			String name=columnFilter.getValue();
			log.debug("marketserviceImpl:"+name);
			return MybatisPageHelper.findPage(pageRequest, CardHospitalMapper, "findPageByLabel",columnFilter.getValue());
		}
		return MybatisPageHelper.findPage(pageRequest, CardHospitalMapper);*/
	}

	/**
	 * 获取过滤字段的值
	 * @param filterName
	 * @return
	 */
	public String getColumnFilterValue(PageRequest pageRequest, String filterName) {
		String value = null;
		ColumnFilter columnFilter = pageRequest.getColumnFilter(filterName);
		if(columnFilter != null) {
			value = columnFilter.getValue();
		}
		return value;
	}
	
	@Override
	public List<CardHospital> findByLable(String name) {
		return cardHospitalMapper.findByLable(name);
	}
	
	@Override
	public List<CardHospitalDetails> findAllByFilters(PageRequest pageRequest) {
		ColumnFilter columnFilter = pageRequest.getColumnFilter("name");
		ColumnFilter columnFilter1 = pageRequest.getColumnFilter("telephone");
		ColumnFilter columnFilter2 = pageRequest.getColumnFilter("office");
		ColumnFilter columnFilter3 = pageRequest.getColumnFilter("address");
		ColumnFilter columnFilter4 = pageRequest.getColumnFilter("customercode");
		ColumnFilter columnFilter5 = pageRequest.getColumnFilter("startTime");
		ColumnFilter columnFilter6 = pageRequest.getColumnFilter("endTime");
		String keyCode;
		String name=columnFilter.getValue();
		String telephone = columnFilter1.getValue();
		String office = columnFilter2.getValue();
		String address = columnFilter3.getValue();
		String customercode = columnFilter4.getValue();
		String startTime = columnFilter5.getValue();
		String endTime = columnFilter6.getValue();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("telephone", telephone);
		map.put("office", office);
		map.put("address", address);
		map.put("customercode", customercode);
		map.put("startTime",startTime);
		map.put("endTime",  endTime);
		List<CardHospital> personals = cardHospitalMapper.findPageByFilters(map);
		List<CardHospitalDetails> allRecords = new ArrayList<>();
		for(CardHospital hospital:personals)
		{
			CardHospitalDetails details = new CardHospitalDetails();
			details.setCustomercode(hospital.getCustomercode());
			details.setName(hospital.getName());
			details.setAddress(hospital.getAddress());
			details.setContacts(hospital.getContacts());
			details.setOfficephone(hospital.getOfficephone());
			details.setTelephone(hospital.getTelephone());
			details.setOffice(hospital.getOffice())	;
			details.setOfficer(hospital.getOfficer());
			
			keyCode = hospital.getCustomercode();
			CardProductConfig productConfig = cardProductConfigMapper.findItem(keyCode);
			if(null != productConfig)
			{
				details.setProductmodel(productConfig.getProductmodel());
				details.setSerialnumber(productConfig.getSerialnumber());
				details.setSellername(productConfig.getSellername());
				details.setPurchasedate(productConfig.getPurchasedate());
			}
			CardProductInstallation productInstallation = cardProductInstallationMapper.findItem(keyCode);
			if(null != productInstallation)
			{
				details.setRequirements(productInstallation.getRequirements());
				details.setSelftest(productInstallation.getSelftest());
				details.setUsertraining(productInstallation.getUsertraining());
				details.setLogoplacement(productInstallation.getLogoplacement());
				details.setInstallconclusion(productInstallation.getInstallconclusion());
				details.setInstaller(productInstallation.getInstaller());
				details.setInstalldate(productInstallation.getInstalldate());
				details.setAcceptor(productInstallation.getAcceptor());
				details.setAcceptdate(productInstallation.getAcceptdate());
			}
			allRecords.add(details);
		}
		return allRecords;
	}
	
   @Override
	public List<CardHospital> findMarPage()
	{
	   log.info("----------------------");
		return cardHospitalMapper.findPage();
	}
	

   @Transactional(readOnly = false,rollbackFor = Exception.class)
   @Override
   public String batchImport(String fileName, MultipartFile file) throws Exception 
   {
	   String str="导入数据成功";
	   boolean flag = false;
	   if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$"))
	   {
           throw new Exception("上传文件格式不正确");
	   }
	   //log.info("上传文件名称：:"+fileName);
	   try {
	       InputStream in =file.getInputStream();	     
	    // 创建Excel工作薄
           Workbook work = this.getWorkbook(in, fileName);
           if (null == work) {
               throw new Exception("创建Excel工作薄为空或excel格式不对！");
           }
           in.close();
           Sheet sheet = work.getSheetAt(0);
           log.info("sheet.getLastRowNum() is " + sheet.getLastRowNum());
           //遍历行
           for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum() + 1; i++) 
           {
               Row row= sheet.getRow(i);
               if (row == null || i == 0 || i == 1)
               {
                   continue;
               }
               flag = false;
               CardHospital hospital=new CardHospital();
               CardProductConfig productConfig = new CardProductConfig();
               CardProductInstallation productInstallation = new CardProductInstallation();
			   //遍历列
               for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) 
               {           	   	   
                   Cell cell = row.getCell(j);
                   //"医院名称","地址","设备科联系人","电话","手机","装机科室","装机主任", 均为必填项                    	   
                   if(cell != null)
                   {
                	   Object cellValue = this.getCellValue(cell);
                       switch (j){
                           case 0 :                 	   
                               hospital.setName(cellValue.toString());
                               break;
                           case 1:
                               hospital.setAddress(cellValue.toString());
                               break;
                           case 2:
		                	   hospital.setContacts(cellValue.toString());
                               break;
                           case 3: 
                               hospital.setOfficephone(cellValue.toString());
                               break;
                           case 4: 
                               hospital.setTelephone(cellValue.toString());
                               break;
                           case 5: 
                               hospital.setOffice(cellValue.toString());
                               break;
                           case 6: 
                               hospital.setOfficer(cellValue.toString());
                               break;
                        	// "产品型号","序列号","销售商名称","购买日期"
                        	// 前四项为必填项
                           case 7:
                        	   productConfig.setProductmodel(cellValue.toString());
                        	   break;
                           case 8:
                        	   productConfig.setSerialnumber(cellValue.toString());
                        	   break;
                           case 9:
                        	   productConfig.setSellername(cellValue.toString());
                        	   break;
                           case 10:
		                	   if(cell != null)
		                	   {
		                		   if(cellValue.toString() != null && cellValue.toString().isEmpty() == false && cellValue.toString().indexOf('-') != -1)
		                		   {//Excel表中日期格式为"1985-10-10"时
		                			   productConfig.setPurchasedate(cellValue.toString());
		                		   }
		                		   else
		                		   {//Excel表中日期格式为"1985/10/10"时
			                		   Date d = cell.getDateCellValue();      		  
			                		   if(null != d)
			                		   {
			                			   DateTimeUtils dateTimeUtils = new DateTimeUtils();
			                			   productConfig.setPurchasedate(dateTimeUtils.getDateTime(d));
			                		   }
			                		   else
			                		   {
			                			   flag = true;
			                			   log.info("purchasedate is null,此为必填项，不能为空");
			                			   break;
			                		   }
		                		   }
		                	   }
                        	   break;
                           //"使用条件是否满足要求","开机自监测试机工作情况","用户培训情况","中文标识是否准确放置","装机结论","安装人","安装日期","验收人","验收日期" 
                           case 11:
                        	   productInstallation.setRequirements(cellValue.toString());
                        	   break;
                           case 12:
                        	   productInstallation.setSelftest(cellValue.toString());
                        	   break;
                           case 13:
                        	   productInstallation.setUsertraining(cellValue.toString());
                        	   break;
                           case 14:
                        	   productInstallation.setLogoplacement(cellValue.toString());
                        	   break;
                           case 15:
                        	   productInstallation.setInstallconclusion(cellValue.toString());
                        	   break;
                           case 16:
                        	   productInstallation.setInstaller(cellValue.toString());
                        	   break;
                           case 17:
		                	   if(cell != null)
		                	   {
		                		   if(cellValue.toString() != null && cellValue.toString().isEmpty() == false && cellValue.toString().indexOf('-') != -1)
		                		   {//Excel表中日期格式为"1985-10-10"时
		                			   productInstallation.setInstalldate(cellValue.toString());
		                		   }
		                		   else
		                		   {//Excel表中日期格式为"1985/10/10"时
			                		   Date d = cell.getDateCellValue();      		  
			                		   if(null != d)
			                		   {
			                			   DateTimeUtils dateTimeUtils = new DateTimeUtils();
			                			   productInstallation.setInstalldate(dateTimeUtils.getDateTime(d));
			                		   }		                			   
		                		   }

		                	   }
                        	   break;
                           case 18:
                        	   productInstallation.setAcceptor(cellValue.toString());
                        	   break;
                           case 19:
		                	   if(cell != null)
		                	   {
		                		   if(cellValue.toString() != null && cellValue.toString().isEmpty() == false && cellValue.toString().indexOf('-') != -1)
		                		   {//Excel表中日期格式为"1985-10-10"时
		                			   productInstallation.setAcceptdate(cellValue.toString());
		                		   }
		                		   else
		                		   {//Excel表中日期格式为"1985/10/10"时
			                		   Date d = cell.getDateCellValue();      		  
			                		   if(null != d)
			                		   {
			                			   DateTimeUtils dateTimeUtils = new DateTimeUtils();
			                			   productInstallation.setAcceptdate(dateTimeUtils.getDateTime(d));
			                		   }
		                		   }
		                	   }
                        	   break;
                           default:
                               continue;
                       }
                    }
                   else
                   {
                	   //log.info("cell is null");
                	   if(j >= 0 && j <= 10)
                	   {
                		   flag = true;//必填项为空，则不导入
                		   log.info("j = " + j + "，此为必填项，不能为空");
                	   }
                   }
                   
                   if(flag == true) //必填项为空后不再解析后续参数
                	   break;
               }
               if(flag == true)
               {
            	   log.info("必填项有空项，不导入该项客户信息。");
            	   continue;
               }
           
               if(hospital != null && hospital.getName() != null && hospital.getTelephone() != null )//去掉Excel中空行
               {
            	    CardHospital temp=cardHospitalMapper.findHospital(hospital.getSerialnumber());   
            	    if(temp == null )
            	    {
            	    	hospital.setCreatetime(DateTimeUtils.getDateTime());
            	    	hospital.setCustomercode(getLatestCustomercode());
            	    	productConfig.setCustomercode(hospital.getCustomercode());
            	    	productInstallation.setCustomercode(hospital.getCustomercode());
            	    	cardHospitalMapper.insertSelective(hospital);
            	    	cardProductConfigMapper.insertSelective(productConfig);
            	    	cardProductInstallationMapper.insertSelective(productInstallation);
                  	    log.info("导入新客户 :" + hospital.getName()+" 手机号:"+hospital.getTelephone());
            	    }
                 	else
                 	{
                 		log.info("导入数据：该客户已存在，" + hospital.getName()+" 手机号:"+hospital.getTelephone() + ",更新客户信息");
                 		hospital.setId(temp.getId());
                 		hospital.setCustomercode(temp.getCustomercode());
                 		productConfig.setCustomercode(temp.getCustomercode());
                 		productInstallation.setCustomercode(temp.getCustomercode());
                 		log.info("hospital id:" + hospital.getId());             		
                 		cardHospitalMapper.updateByPrimaryKeySelective(hospital);
                 		cardProductConfigMapper.updateByCustomercode(productConfig);
            	    	cardProductInstallationMapper.updateByCustomercode(productInstallation);
                 		str="0000";
                 	}
             		hospital = null;
             		productConfig = null;
             		productInstallation = null;
                }
           }
           //return null;
		} catch (Exception e) {
			log.error(String.format("parse excel exception!"), e);
		}
	   return str;
   }
   
   public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception
   {
       Workbook wb = null;
       String fileType = fileName.substring(fileName.lastIndexOf("."));
       if (EXCEL_2003_DOWN.equals(fileType)) {
           wb = new HSSFWorkbook(inStr); // 2003-
       } else if (EXCEL_2007_UP.equals(fileType)) {
           wb = new XSSFWorkbook(inStr); //2007+
       }
       return wb;
   }
   
   private Object getCellValue(Cell cell) 
   {
       Object value = null;
       DecimalFormat df = new DecimalFormat("0"); // 格式化number String字符
       //SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
      // DecimalFormat df2 = new DecimalFormat("0.00"); // 格式化数字
       switch (cell.getCellType()) 
       {
           case Cell.CELL_TYPE_STRING:
               value = cell.getRichStringCellValue().getString();
               break;
           case Cell.CELL_TYPE_NUMERIC:
			 /*
			 * if ("General".equals(cell.getCellStyle().getDataFormatString())) { value =
			 * df.format(cell.getNumericCellValue()); } else if
			 * ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) { value =
			 * sdf.format(cell.getDateCellValue()); } else { value =
			 * df2.format(cell.getNumericCellValue()); }
			 */
               value=df.format(cell.getNumericCellValue());
               break;
           case Cell.CELL_TYPE_BOOLEAN:
               value = cell.getBooleanCellValue();
               break;
           case Cell.CELL_TYPE_BLANK:
               value = "";
               break;
           default:
               break;
       }
       return value;
   }

	@Override
	public int save(CardHospital record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public CardHospitalDetails getDetailsById(Long id) {//查看详情
		CardHospital hospital = cardHospitalMapper.selectByPrimaryKey(id);
		
		CardHospitalDetails details = new CardHospitalDetails();
		
		details.setCustomercode(hospital.getCustomercode());
		details.setName(hospital.getName());
		details.setAddress(hospital.getAddress());
		details.setContacts(hospital.getContacts());
		details.setOfficephone(hospital.getOfficephone());
		details.setTelephone(hospital.getTelephone());
		details.setOffice(hospital.getOffice())	;
		details.setOfficer(hospital.getOfficer());
		
		String keyCode = hospital.getCustomercode();
		CardProductConfig productConfig = cardProductConfigMapper.findItem(keyCode);
		if(null != productConfig)
		{
			details.setProductmodel(productConfig.getProductmodel());
			details.setSerialnumber(productConfig.getSerialnumber());
			details.setSellername(productConfig.getSellername());
			details.setPurchasedate(productConfig.getPurchasedate());
		}
		CardProductInstallation productInstallation = cardProductInstallationMapper.findItem(keyCode);
		if(null != productInstallation)
		{
			details.setRequirements(productInstallation.getRequirements());
			details.setSelftest(productInstallation.getSelftest());
			details.setUsertraining(productInstallation.getUsertraining());
			details.setLogoplacement(productInstallation.getLogoplacement());
			details.setInstallconclusion(productInstallation.getInstallconclusion());
			details.setInstaller(productInstallation.getInstaller());
			details.setInstalldate(productInstallation.getInstalldate());
			details.setAcceptor(productInstallation.getAcceptor());
			details.setAcceptdate(productInstallation.getAcceptdate());
		}
		return details;
	}

	@Override
	public List<CardHospitalDetails> batchExport(List<CardHospital> records) {
		List<CardHospitalDetails> allDetails = new ArrayList<>();
		for(CardHospital hospital:records)
		{
			CardHospitalDetails details = new CardHospitalDetails();
			details = getDetailsById(hospital.getId());
			allDetails.add(details);
		}
		return allDetails;
	}
	public String getLatestCustomercode() {
		int number = 0;
		String latestCustomercode = "";
		String customercode = "CUS-";
		Date now=new Date();
		SimpleDateFormat f=new SimpleDateFormat("yyyyMMdd");
		String date = f.format(now);
		customercode += date;
		customercode += '-';
		//get the latest customer code in database 
		CardHospital record = cardHospitalMapper.getLatestCustomercode();
		if(record != null)
			latestCustomercode= record.getCustomercode();
		log.info("latestCustomerCode in database:"+latestCustomercode);
	
		if(latestCustomercode.contains(date))
		{
			//CUS-20191021-003, get 003 from the customercode
			String str = (String) latestCustomercode.subSequence(latestCustomercode.length() - 3, latestCustomercode.length());
			number = Integer.parseInt(str);
		}

		customercode += getStringNumber(number+1);
		return customercode;
	}
	
	String getStringNumber(int number)
	{
		String str;
		if(number < 10)
		{
			str = "00";
			str += Integer.toString(number);
		}
		else if(number < 100)
		{
			str = "0";
			str += Integer.toString(number);
		}
		else {
			str = Integer.toString(number);
		}
		return str;
	}
}
