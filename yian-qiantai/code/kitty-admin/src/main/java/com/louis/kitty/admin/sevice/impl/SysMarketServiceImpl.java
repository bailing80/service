package com.louis.kitty.admin.sevice.impl;


import java.io.InputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import com.louis.kitty.admin.dao.SysHospitalMapper;
import com.louis.kitty.admin.dao.SysMarketMapper;
import com.louis.kitty.admin.dao.SysSalesMapper;
import com.louis.kitty.admin.model.SysExdel;
import com.louis.kitty.admin.model.SysHospital;
import com.louis.kitty.admin.model.SysMarket;
import com.louis.kitty.admin.model.SysSales;
import com.louis.kitty.admin.sevice.SysMarketService;
import com.louis.kitty.common.utils.DateTimeUtils;
import com.louis.kitty.core.page.ColumnFilter;
import com.louis.kitty.core.page.MybatisPageHelper;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author earl
 * augest 9 2019
   *   客户信息维护管理
   *   
   * @update hgliu
   *  
 */

@Service
@Slf4j
public class SysMarketServiceImpl  implements SysMarketService {
    
	private static final Logger log = LoggerFactory.getLogger(SysMarketServiceImpl.class);

	@Autowired
	private SysMarketMapper sysmarketMapper;
	
	@Autowired
	private SysSalesMapper sysSalesMapper;
	
	@Autowired
	private SysHospitalMapper sysHospitalMapper;

	
	private final static String EXCEL_2003_DOWN = ".xls"; // 2003- 版本的excel
    private final static String EXCEL_2007_UP = ".xlsx"; // 2007+ 版本的excel

	@Override
	public int save(SysMarket record)
	{
		log.info("Enter SysMarketServiceImpl save function:");
		if(record.getId() == null || record.getId() == 0)//新增
		{
			SysMarket temp = sysmarketMapper.isExist(record.getActivityname(),record.getName(),record.getTel());
			if(temp == null )
			{
				log.info("新增客户：客户名称" + record.getName() +" 手机号:"+record.getTel() +" 营销名称:"+record.getActivityname());
				record.setCreatetime(DateTimeUtils.getDateTime());
				sysmarketMapper.insertSelective(record);
				return 1;//add an item successfully			
			}
			else {
				log.info("该客户已存在，新增失败。");
				return 2;//add failed
			}

		}
		else {//编辑
			SysMarket temp = sysmarketMapper.isExist(record.getActivityname(),record.getName(),record.getTel());
			if(temp != null )
			{
				log.info("编辑客户：客户名称" + record.getName() +" 手机号:"+record.getTel() +" 营销名称:"+record.getActivityname());
				sysmarketMapper.updateByPrimaryKeySelective(record);
				return 3;//edit an item successfully
			}
			else {
				log.info("编辑失败，该客户不存在！");
				return 4;//edit failed
			}
		}
	}

	@Override
	public int delete(SysMarket record) {
		return sysmarketMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysMarket> records) {
		for(SysMarket record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysMarket findById(Long id) {
		return sysmarketMapper.selectByPrimaryKey(id);
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
		ColumnFilter columnFilter1 = pageRequest.getColumnFilter("tel");
		ColumnFilter columnFilter2 = pageRequest.getColumnFilter("hospital");
		ColumnFilter columnFilter3 = pageRequest.getColumnFilter("departmentoffices");
		ColumnFilter columnFilter4 = pageRequest.getColumnFilter("activityname");
		ColumnFilter columnFilter5 = pageRequest.getColumnFilter("startTime");
		ColumnFilter columnFilter6 = pageRequest.getColumnFilter("endTime");
		ColumnFilter columnFilter7 = pageRequest.getColumnFilter("sellname");
		String name=columnFilter.getValue();
		String tel = columnFilter1.getValue();
		String hospital = columnFilter2.getValue();
		String departmentoffices = columnFilter3.getValue();
		String activityname = columnFilter4.getValue();
		String startTime = columnFilter5.getValue();
		String endTime = columnFilter6.getValue();
		String sellname = columnFilter7.getValue();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("tel", tel);
		map.put("hospital", hospital);
		map.put("departmentoffices", departmentoffices);
		map.put("activityname", activityname);
		map.put("startTime",startTime);
		map.put("endTime",  endTime);
		map.put("sellname", sellname);
		log.info("startTime:" + columnFilter5.getValue() + ",endTime:" + columnFilter6.getValue());
		log.info("filters: name_ " + name + ", tel_" + tel + ", hospital_" + hospital 
				+ ",departmentoffices_" + departmentoffices +", activityname_" + activityname + ", sellname_" + sellname);

		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		//int currIndex = (pageRequest.getPageNum() - 1) * pageRequest.getPageSize();
		//log.info("~~~~~~  pageNum:" + pageNum + ", currIndex:" + currIndex + ", pageSize:" + pageSize);
		PageHelper.startPage(pageNum, pageSize);
		List<SysMarket> records = sysmarketMapper.findPageByFilters(map);
		Object result = records;//ReflectionUtils.invoke(mapper, queryMethodName, args);
		return getPageResult(pageRequest, new PageInfo((List) result));
/*		
		ColumnFilter columnFilter = pageRequest.getColumnFilter("name");
		
		if(columnFilter != null) {
			String name=columnFilter.getValue();
			log.debug("marketserviceImpl:"+name);
			return MybatisPageHelper.findPage(pageRequest, sysmarketMapper, "findPageByLabel",columnFilter.getValue());
		}
		return MybatisPageHelper.findPage(pageRequest, sysmarketMapper);*/
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
	public List<SysMarket> findByLable(String name) {
		return sysmarketMapper.findByLable(name);
	}
	
	@Override
	public List<SysMarket> findByFilters(PageRequest pageRequest) {
		ColumnFilter columnFilter = pageRequest.getColumnFilter("name");
		ColumnFilter columnFilter1 = pageRequest.getColumnFilter("tel");
		ColumnFilter columnFilter2 = pageRequest.getColumnFilter("hospital");
		ColumnFilter columnFilter3 = pageRequest.getColumnFilter("departmentoffices");
		ColumnFilter columnFilter4 = pageRequest.getColumnFilter("activityname");
		ColumnFilter columnFilter5 = pageRequest.getColumnFilter("startTime");
		ColumnFilter columnFilter6 = pageRequest.getColumnFilter("endTime");
		ColumnFilter columnFilter7 = pageRequest.getColumnFilter("sellname");
		String name=columnFilter.getValue();
		String tel = columnFilter1.getValue();
		String hospital = columnFilter2.getValue();
		String departmentoffices = columnFilter3.getValue();
		String activityname = columnFilter4.getValue();
		String startTime = columnFilter5.getValue();
		String endTime = columnFilter6.getValue();
		String sellname = columnFilter7.getValue();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("tel", tel);
		map.put("hospital", hospital);
		map.put("departmentoffices", departmentoffices);
		map.put("activityname", activityname);
		map.put("startTime",startTime);
		map.put("endTime",  endTime);
		map.put("sellname", sellname);
		List<SysMarket> records = sysmarketMapper.findPageByFilters(map);
		return records;
	}
	
   @Override
	public List<SysMarket> findMarPage()
	{
	   log.info("----------------------");
		return sysmarketMapper.findPage();
	}
	
   
   @Transactional(readOnly = false,rollbackFor = Exception.class)
   @Override
   public String batchImport(String fileName, MultipartFile file) throws Exception 
   {
	   String str="导入数据成功";
	   if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$"))
	   {
           throw new Exception("上传文件格式不正确");
	   }
	   //log.info("上传文件名称：:"+fileName);
	    List<SysMarket> markets=new ArrayList<>();
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
               if (row == null || i == 0)
               {
                   continue;
               }
               
               SysMarket wev=new SysMarket();
			   //遍历列
               for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) 
               {           	   	   
                   Cell cell = row.getCell(j);
                   //{0=营销活动，1=客户名称, 2=手机号, 3=医院名称, 4=科室, 5=详细地址, 6=销售员名称，7=创建时间}                       	   
                   if(cell != null)
                   {
                	   Object cellValue = this.getCellValue(cell);
                       switch (j){
                           case 0 :                 	   
                               wev.setActivityname(cellValue.toString());
                               log.info(" activityname :"+cellValue.toString());
                               break;
                           case 1:
                               wev.setName(cellValue.toString());
                               //log.info(" name :"+cellValue.toString());
                               break;
                           case 2:
                               wev.setTel(cellValue.toString());
                               //log.info(" tel :"+cellValue.toString());
                               break;
                           case 3: 
                               wev.setHospital(cellValue.toString());
                               break;
                           case 4: 
                               wev.setDepartmentoffices(cellValue.toString());
                               break;
                           case 5: 
                               wev.setAddress(cellValue.toString());
                               break;
                           case 6: 
                               wev.setSellname(cellValue.toString());
                               break;
                           case 7: 
                               wev.setCreatetime(DateTimeUtils.getDateTime());
                               //record.setCreatetime(DateTimeUtils.getDateTime());
                               break;   
                           default:
                               continue;
                       }
                    }
               }             
           
               if(wev != null && wev.getName() != null && wev.getTel() != null )//去掉Excel中空行
               {
            	    markets.add(wev);  
            	    SysMarket symark=sysmarketMapper.isExist(wev.getActivityname(),wev.getName(),wev.getTel());   
            	    if(symark == null )
            	    {
            	    	wev.setCreatetime(DateTimeUtils.getDateTime());
                  	   	sysmarketMapper.insertSelective(wev);
                  	    log.info("导入新客户 :" + wev.getName()+" 手机号:"+wev.getTel() + ",营销名称：" + wev.getActivityname());
            	    }
                 	else
                 	{
                 		log.info("导入数据：该客户已存在，" + wev.getName()+" 手机号:"+wev.getTel() + ",营销名称：" + wev.getActivityname());
                 		str="0000";
                 	}
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
	public List<SysSales> getSalesNames() {
		log.info("Enter SysMarketServiceImpl getSalesNames:");
		List<SysSales> recordSales = new ArrayList<>();
		recordSales = sysSalesMapper.getSalesNames();
		return recordSales;
	}

	@Override
	public List<SysHospital> getHospitalNames(String name) {
		log.info("Enter SysMarketServiceImpl getHospitalNames:");
		List<SysHospital> recordHospitals = new ArrayList<>();
		recordHospitals = sysHospitalMapper.findPageByLabel(name);
		log.info("Hospital size found by filter:" + recordHospitals.size());
		return recordHospitals;
	}

	@Override
	public PageResult nameDuplicateCheck(PageRequest pageRequest) {
		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		//int currIndex = (pageRequest.getPageNum() - 1) * pageRequest.getPageSize();
		//log.info("~~~~~~  pageNum:" + pageNum + ", currIndex:" + currIndex + ", pageSize:" + pageSize);
		PageHelper.startPage(pageNum, pageSize);
		List<SysMarket> records = sysmarketMapper.nameDuplicateCheck();
		Object result = records;//ReflectionUtils.invoke(mapper, queryMethodName, args);
		return getPageResult(pageRequest, new PageInfo((List) result));
	}

	@Override
	public PageResult telDuplicateCheck(PageRequest pageRequest) {
		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		//int currIndex = (pageRequest.getPageNum() - 1) * pageRequest.getPageSize();
		//log.info("~~~~~~  pageNum:" + pageNum + ", currIndex:" + currIndex + ", pageSize:" + pageSize);
		PageHelper.startPage(pageNum, pageSize);
		List<SysMarket> records = sysmarketMapper.telDuplicateCheck();
		Object result = records;//ReflectionUtils.invoke(mapper, queryMethodName, args);
		return getPageResult(pageRequest, new PageInfo((List) result));
	}
}
