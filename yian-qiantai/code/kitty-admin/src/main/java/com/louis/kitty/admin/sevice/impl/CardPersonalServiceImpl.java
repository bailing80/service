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
import com.louis.kitty.admin.dao.CardPersonalMapper;
import com.louis.kitty.admin.dao.CardPersonalRecordMapper;
import com.louis.kitty.admin.dao.CardProductConfigMapper;
import com.louis.kitty.admin.dao.CardProductInstallationMapper;
import com.louis.kitty.admin.model.CardPersonal;
import com.louis.kitty.admin.model.CardPersonalDetails;
import com.louis.kitty.admin.model.CardPersonalRecord;
import com.louis.kitty.admin.model.CardProductConfig;
import com.louis.kitty.admin.model.CardProductInstallation;
import com.louis.kitty.admin.sevice.CardPersonalService;
import com.louis.kitty.common.utils.DateTimeUtils;
import com.louis.kitty.core.page.ColumnFilter;
import com.louis.kitty.core.page.MybatisPageHelper;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;
import lombok.extern.slf4j.Slf4j;
/** 
   * @author hgliu
   * October 12 2019
   * 个人客户信息维护管理   
   * @update hgliu
   *  
 */

@Service
@Slf4j
public class CardPersonalServiceImpl  implements CardPersonalService {
    
	private static final Logger log = LoggerFactory.getLogger(CardPersonalServiceImpl.class);

	@Autowired
	private CardPersonalMapper cardPersonalMapper;
	
	@Autowired
	private CardPersonalRecordMapper cardPersonalRecordMapper;
	
	@Autowired
	private CardProductConfigMapper cardProductConfigMapper;
	
	@Autowired
	private CardProductInstallationMapper cardProductInstallationMapper;
	
	private final static String EXCEL_2003_DOWN = ".xls"; // 2003- 版本的excel
    private final static String EXCEL_2007_UP = ".xlsx"; // 2007+ 版本的excel
  //用户名
    private static String Uid = "aeonmedcom";  
    //接口安全秘钥  d41d8cd98f00b204e980
    private static String Key = "d41d8cd98f00b204e980";
    
    private static String smsText="！请在注册电子保修卡页面输入以完成注册。5分钟内有效。如非本人操作，请忽略本短信。回复TD退订";
	@Override
	public int save(CardPersonalDetails record)
	{
		log.info("Enter CardPersonalServiceImpl save function:");
		//log.info(record.getName() + "," + record.getAge() + "," + record.getProductmodel());
		//分离出客户基本信息
		CardPersonal personal = new CardPersonal();
		personal.setName(record.getName());
		personal.setSex(record.getSex());
		personal.setAge(record.getAge());
		personal.setTelephone(record.getTelephone());
		personal.setBuychannel(record.getBuychannel());
		personal.setProvince(record.getProvince());
		personal.setCity(record.getCity());
		personal.setSerialnumber(record.getSerialnumber());
		personal.setProductmodel(record.getProductmodel());
		personal.setPurchasedate(record.getPurchasedate());
		
		if(record.getId() == null || record.getId() == 0)//新增
		{
			CardPersonal temp = cardPersonalMapper.findClient(record.getSerialnumber());
			if(temp == null)
			{
				//log.info("新增客户：客户名称" + record.getName() +" 手机号:"+record.getTelephone() );
				personal.setCustomercode(getLatestCustomercode());
				personal.setCreatetime(DateTimeUtils.getDateTime());
				cardPersonalMapper.insertSelective(personal);
				//分离出患者病情记录并保存到数据库
				CardPersonalRecord personalRecord = new CardPersonalRecord();
				personalRecord.setCustomercode(personal.getCustomercode());
				personalRecord.setHospital(record.getHospital());
				personalRecord.setRoom(record.getRoom());
				personalRecord.setBreathetype(record.getBreathetype());
				personalRecord.setOthersA(record.getOthersA());
				personalRecord.setPsgresult(record.getPsgresult());
				personalRecord.setSaturation(record.getSaturation());
				personalRecord.setComplication(record.getComplication());
				personalRecord.setOthersB(record.getOthersB());
				personalRecord.setParameters(record.getParameters());
				cardPersonalRecordMapper.insertSelective(personalRecord);
				//分离出产品购买及配置信息并保存到数据库
				CardProductConfig productConfig = new CardProductConfig();
				productConfig.setCustomercode(personal.getCustomercode());
				productConfig.setSellername(record.getSellername());
				productConfig.setAppearance(record.getAppearance());
				productConfig.setPacking(record.getPacking());
				productConfig.setAccessories(record.getAccessories());
				productConfig.setRemarks(record.getRemarks());
				cardProductConfigMapper.insertSelective(productConfig);
				//分离出产品安装信息并保存到数据库
				CardProductInstallation productInstallation = new CardProductInstallation();
				productInstallation.setCustomercode(personal.getCustomercode());
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
			CardPersonal temp = cardPersonalMapper.findClient(record.getSerialnumber());
			log.info(record.getSerialnumber());
			if(temp != null )
			{
				log.info("编辑客户：客户名称" + record.getName() +" 手机号:"+record.getTelephone());
				personal.setId(temp.getId());
				personal.setCustomercode(temp.getCustomercode());
				personal.setCreateTime(temp.getCreateTime());
				cardPersonalMapper.updateByPrimaryKeySelective(personal);
				return 3;//edit an item successfully
			}
			else {
				log.info("编辑失败，该客户不存在！");
				return 4;//edit failed
			}
		}
	}

	@Override
	public int delete(CardPersonal record) {
		//log.info("record.customercode:" + record.getCustomercode());
		String customercode = (cardPersonalMapper.selectByPrimaryKey(record.getId())).getCustomercode();
		cardPersonalRecordMapper.deleteByCustomercode(customercode);
		cardProductConfigMapper.deleteByCustomercode(customercode);
		cardProductInstallationMapper.deleteByCustomercode(customercode);
		cardPersonalMapper.deleteByPrimaryKey(record.getId());
		return 1;
	}

	@Override
	public int delete(List<CardPersonal> records) {
		log.info("Enter delete function:");
		for(CardPersonal record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public CardPersonal findById(Long id) {
		return cardPersonalMapper.selectByPrimaryKey(id);
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
		ColumnFilter columnFilter2 = pageRequest.getColumnFilter("sex");
		ColumnFilter columnFilter3 = pageRequest.getColumnFilter("province");
		ColumnFilter columnFilter4 = pageRequest.getColumnFilter("customercode");
		ColumnFilter columnFilter5 = pageRequest.getColumnFilter("startTime");
		ColumnFilter columnFilter6 = pageRequest.getColumnFilter("endTime");
		ColumnFilter columnFilter7 = pageRequest.getColumnFilter("city");
		ColumnFilter columnFilter8 = pageRequest.getColumnFilter("buychannel");
		String name=columnFilter.getValue();
		String telephone = columnFilter1.getValue();
		String sex = columnFilter2.getValue();
		String province = columnFilter3.getValue();
		String customercode = columnFilter4.getValue();
		String startTime = columnFilter5.getValue();
		String endTime = columnFilter6.getValue();
		String city = columnFilter7.getValue();
		String buychannel = columnFilter8.getValue();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("telephone", telephone);
		map.put("sex", sex);
		map.put("province", province);
		if(city.compareTo("全部") != 0)
			map.put("city", city);
		map.put("buychannel", buychannel);
		map.put("customercode", customercode);
		map.put("startTime",startTime);
		map.put("endTime",  endTime);
		log.info("startTime:" + columnFilter5.getValue() + ",endTime:" + columnFilter6.getValue());
		log.info("filters: customercode_" + customercode + ", name_" + name + ", telephone_" + telephone + ", sex_" + sex 
				+ ",province_" + province + ",city_" + city + ",buychannel_" + buychannel);

		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		//int currIndex = (pageRequest.getPageNum() - 1) * pageRequest.getPageSize();
		//log.info("~~~~~~  pageNum:" + pageNum + ", currIndex:" + currIndex + ", pageSize:" + pageSize);
		PageHelper.startPage(pageNum, pageSize);
		List<CardPersonal> records = cardPersonalMapper.findPageByFilters(map);
		Object result = records;//ReflectionUtils.invoke(mapper, queryMethodName, args);
		return getPageResult(pageRequest, new PageInfo((List) result));
/*		
		ColumnFilter columnFilter = pageRequest.getColumnFilter("name");
		
		if(columnFilter != null) {
			String name=columnFilter.getValue();
			log.debug("marketserviceImpl:"+name);
			return MybatisPageHelper.findPage(pageRequest, cardPersonalMapper, "findPageByLabel",columnFilter.getValue());
		}
		return MybatisPageHelper.findPage(pageRequest, cardPersonalMapper);*/
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
	public List<CardPersonal> findByLable(String name) {
		return cardPersonalMapper.findByLable(name);
	}
	
	@Override
	public List<CardPersonalDetails> findAllByFilters(PageRequest pageRequest) {
		ColumnFilter columnFilter = pageRequest.getColumnFilter("name");
		ColumnFilter columnFilter1 = pageRequest.getColumnFilter("telephone");
		ColumnFilter columnFilter2 = pageRequest.getColumnFilter("sex");
		ColumnFilter columnFilter3 = pageRequest.getColumnFilter("province");
		ColumnFilter columnFilter4 = pageRequest.getColumnFilter("customercode");
		ColumnFilter columnFilter5 = pageRequest.getColumnFilter("startTime");
		ColumnFilter columnFilter6 = pageRequest.getColumnFilter("endTime");
		ColumnFilter columnFilter7 = pageRequest.getColumnFilter("city");
		ColumnFilter columnFilter8 = pageRequest.getColumnFilter("buychannel");
		String keyCode;
		String name=columnFilter.getValue();
		String telephone = columnFilter1.getValue();
		String sex = columnFilter2.getValue();
		String province = columnFilter3.getValue();
		String customercode = columnFilter4.getValue();
		String startTime = columnFilter5.getValue();
		String endTime = columnFilter6.getValue();
		String city = columnFilter7.getValue();
		String buychannel = columnFilter8.getValue();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("telephone", telephone);
		map.put("sex", sex);
		map.put("province", province);
		if(city.compareTo("全部") != 0)
			map.put("city", city);
		map.put("buychannel", buychannel);
		map.put("customercode", customercode);
		map.put("startTime",startTime);
		map.put("endTime",  endTime);
		List<CardPersonal> personals = cardPersonalMapper.findPageByFilters(map);
		List<CardPersonalDetails> allRecords = new ArrayList<>();
		for(CardPersonal personal:personals)
		{
			CardPersonalDetails details = new CardPersonalDetails();
			details.setCustomercode(personal.getCustomercode());
			details.setName(personal.getName());
			details.setSex(personal.getSex());
			details.setAge(personal.getAge());
			details.setTelephone(personal.getTelephone());
			details.setBuychannel(personal.getBuychannel());
			details.setProvince(personal.getProvince());
			details.setCity(personal.getCity());
			details.setSerialnumber(personal.getSerialnumber());
			details.setProductmodel(personal.getProductmodel());
			details.setPurchasedate(personal.getPurchasedate());
			
			keyCode = personal.getCustomercode();
			CardPersonalRecord personalRecord = cardPersonalRecordMapper.findItem(keyCode);
			if(null != personalRecord)
			{
				details.setHospital(personalRecord.getHospital());
				details.setRoom(personalRecord.getRoom());
				details.setBreathetype(personalRecord.getBreathetype());
				details.setOthersA(personalRecord.getOthersA());
				details.setPsgresult(personalRecord.getPsgresult());
				details.setSaturation(personalRecord.getSaturation());
				details.setComplication(personalRecord.getComplication());
				details.setOthersB(personalRecord.getOthersB());
				details.setParameters(personalRecord.getParameters());
			}
			CardProductConfig productConfig = cardProductConfigMapper.findItem(keyCode);
			if(null != productConfig)
			{
				details.setSellername(productConfig.getSellername());
				details.setAppearance(productConfig.getAppearance());
				details.setPacking(productConfig.getPacking());
				details.setAccessories(productConfig.getAccessories());
				details.setRemarks(productConfig.getRemarks());
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
		//log.info(""+);
		return allRecords;
	}
	
   @Override
	public List<CardPersonal> findMarPage()
	{
	   log.info("----------------------");
		return cardPersonalMapper.findPage();
	}
	

   @Transactional(readOnly = false,rollbackFor = Exception.class)
   @Override
   public String batchImport(String fileName, MultipartFile file) throws Exception 
   {
	   String str="";
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
               CardPersonal personal=new CardPersonal();
               CardPersonalRecord personalRecord = new CardPersonalRecord();
               CardProductConfig productConfig = new CardProductConfig();
               CardProductInstallation productInstallation = new CardProductInstallation();
			   //遍历列
               for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) 
               {           	   	   
                   Cell cell = row.getCell(j);
                   //"用户姓名","性别","出生日期","手机号码","购买渠道","省","市","序列号","机型","购买日期", 均为必填项                    	   
                   if(cell != null)
                   {
                	   Object cellValue = this.getCellValue(cell);
                       switch (j){
                           case 0:                 	   
                               personal.setName(cellValue.toString());
                               //log.info(personal.getName());
                               break;
                           case 1:
                               personal.setSex(cellValue.toString());
                               //log.info(personal.getSex());
                               break;
                           case 2:
                        	   personal.setAge(cellValue.toString());
                               break;
                           case 3: 
                        	   personal.setTelephone(cellValue.toString());
                               String stelephone=personal.getTelephone();
                               if(stelephone.length()>=3 || stelephone.length()<=11)
                               {
                            	  break;  
                               }else 
                               {
                            	    str="导入失败，手机号长度不够";
                               }                    
                           case 4: 
                               personal.setBuychannel(cellValue.toString());
                               //log.info(personal.getBuychannel());
                               break;
                           case 5: 
                               personal.setProvince(cellValue.toString());
                               //log.info(personal.getProvince());
                               break;
                           case 6: 
                               personal.setCity(cellValue.toString());
                               //log.info(personal.getProvince());
                               break;
                           case 7:
                        	   personal.setSerialnumber(cellValue.toString());
                        	   //log.info(personal.getProductmodel());
                        	   break;
                           case 8:
                        	   personal.setProductmodel(cellValue.toString());
                        	   //log.info(personal.getSerialnumber());
                        	   break;
                           case 9:
		                	   if(cell != null)
		                	   {
		                		   if(cellValue.toString() != null && cellValue.toString().isEmpty() == false && cellValue.toString().indexOf('-') != -1)
		                		   {//Excel表中日期格式为"1985-10-10"时
		                			   personal.setPurchasedate(cellValue.toString());
		                		   }
		                		   else
		                		   {//Excel表中日期格式为"1985/10/10"时
			                		   Date d = cell.getDateCellValue();      		  
			                		   if(null != d)
			                		   {
			                			   DateTimeUtils dateTimeUtils = new DateTimeUtils();
			                			   personal.setPurchasedate(dateTimeUtils.getDateTime(d));
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
                            // "确诊医院","就诊科室","呼吸疾病类型(必填项)","其他","PSG结果","血氧饱和度","合并症(必填项)","其他","呼吸机参数设定",
                           case 10: 
                               personalRecord.setHospital(cellValue.toString());
                               //log.info(personalRecord.getHospital());
                               break;
                           case 11:
                        	   personalRecord.setRoom(cellValue.toString());
                        	   //log.info(personalRecord.getRoom());
                        	   break;
                           case 12:
                        	   personalRecord.setBreathetype(cellValue.toString());
                        	   //log.info(personalRecord.getBreathetype());
                        	   break;
                           case 13:
                        	   personalRecord.setOthersA(cellValue.toString());
                        	   //log.info(personalRecord.getOthersA());
                        	   break;
                           case 14:
                        	   personalRecord.setPsgresult(cellValue.toString());
                        	   //log.info(personalRecord.getPsgresult());
                        	   break;
                           case 15:
                        	   personalRecord.setSaturation(cellValue.toString());
                        	   //log.info(personalRecord.getSaturation());
                        	   break;
                           case 16:
                        	   personalRecord.setComplication(cellValue.toString());
                        	   //log.info(personalRecord.getComplication());
                        	   break;
                           case 17:
                        	   personalRecord.setOthersB(cellValue.toString());
                        	   //log.info(personalRecord.getOthersB());
                        	   break;
                           case 18:
                        	   personalRecord.setParameters(cellValue.toString());
                        	   //log.info(personalRecord.getParameters());
                        	   break;
                        	//"销售商名称","产品运输途中产品外观","产品运输途中产品外包装","产品附件是否齐全","描述",
                           case 19:
                        	   productConfig.setSellername(cellValue.toString());
                        	   break;
                           case 20:
                        	   productConfig.setAppearance(cellValue.toString());
                        	   break;
                           case 21:
                        	   productConfig.setPacking(cellValue.toString());
                        	   break;
                           case 22:
                        	   productConfig.setAccessories(cellValue.toString());
                        	   break;
                           case 23:
                        	   productConfig.setRemarks(cellValue.toString());
                        	   break;
                           //"使用条件是否满足要求","开机自监测试机工作情况","用户培训情况","中文标识是否准确放置","装机结论","安装人","安装日期","验收人","验收日期" 
                           case 24:
                        	   productInstallation.setRequirements(cellValue.toString());
                        	   break;
                           case 25:
                        	   productInstallation.setSelftest(cellValue.toString());
                        	   break;
                           case 26:
                        	   productInstallation.setUsertraining(cellValue.toString());
                        	   break;
                           case 27:
                        	   productInstallation.setLogoplacement(cellValue.toString());
                        	   break;
                           case 28:
                        	   productInstallation.setInstallconclusion(cellValue.toString());
                        	   break;
                           case 29:
                        	   productInstallation.setInstaller(cellValue.toString());
                        	   break;
                           case 30:
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
                           case 31:
                        	   productInstallation.setAcceptor(cellValue.toString());
                        	   break;
                           case 32:
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
                	   if(j >= 0 && j <= 8)
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
           
               if(personal != null && personal.getName() != null && personal.getTelephone() != null )//去掉Excel中空行
               {
            	    CardPersonal temp=cardPersonalMapper.findClient(personal.getSerialnumber());   
            	    if(temp == null )
            	    {
            	    	personal.setCreatetime(DateTimeUtils.getDateTime());
            	    	personal.setCustomercode(getLatestCustomercode());
            	    	personalRecord.setCustomercode(personal.getCustomercode());
            	    	productConfig.setCustomercode(personal.getCustomercode());
            	    	productInstallation.setCustomercode(personal.getCustomercode());
            	    	cardPersonalMapper.insertSelective(personal);
            	    	cardPersonalRecordMapper.insertSelective(personalRecord);
            	    	cardProductConfigMapper.insertSelective(productConfig);
            	    	cardProductInstallationMapper.insertSelective(productInstallation);
                  	    log.info("导入新客户 :" + personal.getName()+" 手机号:"+personal.getTelephone());
                  	    str="导入成功";
            	    }
                 	else
                 	{
                 		log.info("导入数据：该客户已存在，" + personal.getName()+" 手机号:"+personal.getTelephone() + ",更新客户信息");
                 		personal.setId(temp.getId());
                 		personal.setCustomercode(temp.getCustomercode());
                 		personalRecord.setCustomercode(personal.getCustomercode());
            	    	productConfig.setCustomercode(personal.getCustomercode());
            	    	productInstallation.setCustomercode(personal.getCustomercode());
                 		log.info("personal id:" + personal.getId());             		
                 		cardPersonalMapper.updateByPrimaryKeySelective(personal);
            	    	cardPersonalRecordMapper.updateByCustomercode(personalRecord);
            	    	cardProductConfigMapper.updateByCustomercode(productConfig);
            	    	cardProductInstallationMapper.updateByCustomercode(productInstallation);
                 		str="导入失败";
                 	}
             		personal = null;
             		personalRecord = null;
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
	public int save(CardPersonal record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public CardPersonalDetails getDetailsById(Long id) {//查看详情
		CardPersonal personal = cardPersonalMapper.selectByPrimaryKey(id);
		
		CardPersonalDetails details = new CardPersonalDetails();
		
		details.setCustomercode(personal.getCustomercode());
		details.setName(personal.getName());
		details.setSex(personal.getSex());
		details.setAge(personal.getAge());
		details.setTelephone(personal.getTelephone());
		details.setBuychannel(personal.getBuychannel());
		details.setProvince(personal.getProvince());
		details.setCity(personal.getCity());
		details.setSerialnumber(personal.getSerialnumber());
		details.setProductmodel(personal.getProductmodel());
		details.setPurchasedate(personal.getPurchasedate());
		
		String keyCode = personal.getCustomercode();
		CardPersonalRecord personalRecord = cardPersonalRecordMapper.findItem(keyCode);
		if(null != personalRecord)
		{
			details.setHospital(personalRecord.getHospital());
			details.setRoom(personalRecord.getRoom());
			details.setBreathetype(personalRecord.getBreathetype());
			details.setOthersA(personalRecord.getOthersA());
			details.setPsgresult(personalRecord.getPsgresult());
			details.setSaturation(personalRecord.getSaturation());
			details.setComplication(personalRecord.getComplication());
			details.setOthersB(personalRecord.getOthersB());
			details.setParameters(personalRecord.getParameters());
		}
		CardProductConfig productConfig = cardProductConfigMapper.findItem(keyCode);
		if(null != productConfig)
		{	
			details.setSellername(productConfig.getSellername());	
			details.setAppearance(productConfig.getAppearance());
			details.setPacking(productConfig.getPacking());
			details.setAccessories(productConfig.getAccessories());
			details.setRemarks(productConfig.getRemarks());
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
	public List<CardPersonalDetails> batchExport(List<CardPersonal> records) {
		// TODO Auto-generated method stub
		List<CardPersonalDetails> allDetails = new ArrayList<>();
		for(CardPersonal personal:records)
		{
			CardPersonalDetails details = new CardPersonalDetails();
			details = getDetailsById(personal.getId());
			allDetails.add(details);
		}
		return allDetails;
	}
	public String getLatestCustomercode() {
		int number = 0;
		String latestCustomercode = "";
		String customercode = "CTS-";
		Date now=new Date();
		SimpleDateFormat f=new SimpleDateFormat("yyyyMMdd");
		String date = f.format(now);
		customercode += date;
		customercode += '-';
		//get the latest customer code in database 
		CardPersonal record = cardPersonalMapper.getLatestCustomercode();
		if(record != null)
			latestCustomercode = record.getCustomercode();
		log.info("latestCustomerCode in database:"+latestCustomercode);
	
		if(latestCustomercode.contains(date))
		{
			//CTS-20191021-003, get 003 from the customercode
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

	/**
	 *   短信 接口
	 */
	/*
	 * @Override public String Smsmessage(String sphone,String mseid)throws
	 * Exception { String message=""; HttpClientUtil client =
	 * HttpClientUtil.getInstance(); int result = client.sendMsgUtf8(Uid, Key,
	 * "验证码为:"+mseid+smsText, sphone); // int bl =1; if(result < 0) {
	 * message=client.getErrorMsg(result); }else { message=String.valueOf(result); }
	 * return message; }
	 */
}
