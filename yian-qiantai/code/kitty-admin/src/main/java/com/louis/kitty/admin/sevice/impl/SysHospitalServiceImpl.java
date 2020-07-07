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
import com.louis.kitty.admin.model.SysHospital;
import com.louis.kitty.admin.model.SysMarket;
import com.louis.kitty.admin.sevice.SysHospitalService;
import com.louis.kitty.common.utils.DateTimeUtils;
import com.louis.kitty.core.page.ColumnFilter;
import com.louis.kitty.core.page.MybatisPageHelper;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author hgliu
 * October 6 2019
 *  医院信息管理 
 */

@Service
@Slf4j
public class SysHospitalServiceImpl  implements SysHospitalService {
    
	private static final Logger log = LoggerFactory.getLogger(SysHospitalServiceImpl.class);

	@Autowired
	private SysHospitalMapper sysHospitalMapper;
	
	private final static String EXCEL_2003_DOWN = ".xls"; // 2003- 版本的excel
    private final static String EXCEL_2007_UP = ".xlsx"; // 2007+ 版本的excel

	@Override
	public int save(SysHospital record)
	{
		log.info("Enter SysHospitalServiceImpl save function:");
		if(record.getId() == null || record.getId() == 0)//新增
		{
			SysHospital temp = sysHospitalMapper.findByName(record.getName());
			if(temp == null )
			{
				log.info("新增：医院名称" + record.getName());
				sysHospitalMapper.insertSelective(record);
				return 1;//add an item successfully			
			}
			else {
				log.info("该医院已存在，新增失败。");
				return 2;//add failed
			}

		}
		else {//编辑
			SysHospital temp = sysHospitalMapper.findByName(record.getName());
			if(temp != null )
			{
				log.info("编辑：医院名称" + record.getName() );
				sysHospitalMapper.updateByPrimaryKeySelective(record);
				return 3;//edit an item successfully
			}
			else {
				log.info("编辑失败，该医院不存在！");
				return 4;//edit failed
			}
		}
		
		
		//log.info("-系统时间 --:"+DateTimeUtils.getDateTime());
		/*
		if(record.getId() == null || record.getId() == 0)
		{			
			return sysHospitalMapper.insertSelective(record);			
		}
		return sysHospitalMapper.updateByPrimaryKeySelective(record);*/
	}

	@Override
	public int delete(SysHospital record) {
		return sysHospitalMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysHospital> records) {
		for(SysHospital record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysHospital findById(Long id) {
		return sysHospitalMapper.selectByPrimaryKey(id);
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
		ColumnFilter columnFilter1 = pageRequest.getColumnFilter("province");
		ColumnFilter columnFilter2 = pageRequest.getColumnFilter("district");
		ColumnFilter columnFilter3 = pageRequest.getColumnFilter("grade");
		String name=columnFilter.getValue();
		String province = columnFilter1.getValue();
		String district = columnFilter2.getValue();
		String grade = columnFilter3.getValue();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("province", province);
		map.put("district", district);
		map.put("grade", grade);
		log.info("filters: name_ " + name + ",province_" + province + ",district:" + district + ",grade:" + grade);

		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		List<SysHospital> records = sysHospitalMapper.findPageByFilters(map);
		Object result = records;//ReflectionUtils.invoke(mapper, queryMethodName, args);
		return getPageResult(pageRequest, new PageInfo((List) result));
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
	public List<SysHospital> findByLable(String name) {
		return sysHospitalMapper.findByLable(name);
	}
	
	@Override
	public List<SysHospital> findByFilters(PageRequest pageRequest) {
		ColumnFilter columnFilter = pageRequest.getColumnFilter("name");
		ColumnFilter columnFilter1 = pageRequest.getColumnFilter("province");
		ColumnFilter columnFilter2 = pageRequest.getColumnFilter("district");
		ColumnFilter columnFilter3 = pageRequest.getColumnFilter("grade");
		String name=columnFilter.getValue();
		String province = columnFilter1.getValue();
		String district = columnFilter2.getValue();
		String grade = columnFilter3.getValue();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("province", province);
		map.put("district", district);
		map.put("grade", grade);
		//log.info("filters: name_ " + name + ",province_" + province + ",district:" + district + ",grade:" + grade);
		List<SysHospital> records = sysHospitalMapper.findPageByFilters(map);
		return records;
	}
	
	@Override
	public SysHospital findByName(String name) {
		//log.info("select SQL"+name+""+tel);
		SysHospital str=sysHospitalMapper.findByName(name);
		//log.info("SQL"+str);
		return str;
	}
	
	
	
   @Override
	public List<SysHospital> findMarPage()
	{
	   log.info("----------------------");
		return sysHospitalMapper.findPage();
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
	   List<SysHospital> hospitals=new ArrayList<>();
	   try {
	       InputStream in =file.getInputStream();	     
	    // 创建Excel工作薄
           Workbook work = this.getWorkbook(in, fileName);
           if (null == work) {
               throw new Exception("创建Excel工作薄为空或excel格式不对！");
           }
           in.close();
           Sheet sheet = work.getSheetAt(0);
           //log.info("sheet.getLastRowNum() is " + sheet.getLastRowNum());
           //遍历行
           for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum() + 1; i++) 
           {
               Row row= sheet.getRow(i);
               if (row == null || i == 0)
               {
                   continue;
               }
               
               SysHospital wev=new SysHospital();
			   //遍历列
               for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) 
               {           	   	   
                   Cell cell = row.getCell(j);
                   //{0=省份，1=地区, 2=医院名称, 3=地址，4=级别，5=门诊量，6=描述}                       	   
                   if(cell != null)
                   {
                	   Object cellValue = this.getCellValue(cell);
                       switch (j){
                           case 0 :                 	   
                        	  wev.setProvince(cellValue.toString());
                               break;
                           case 1:
                               wev.setDistrict(cellValue.toString());
                               break;
                           case 2: 
                               wev.setName(cellValue.toString());
                               break;
                           case 3: 
                               wev.setHospitalAddres(cellValue.toString());
                               break;
                           case 4: 
                               wev.setGrade(cellValue.toString());
                               break;
                           case 5: 
                               wev.setOutpatientVolume(cellValue.toString());
                               break;
                           case 6: 
                               wev.setRemarks(cellValue.toString());
                               break;   
                           default:
                               continue;
                       }
                    }
               }             
           
               if(wev != null && wev.getName() != null)
               {
            	    hospitals.add(wev);  
            	    SysHospital record=this.findByName(wev.getName());   
            	    if(record == null )
            	    {
                  	   	sysHospitalMapper.insertSelective(wev);
                  	    log.info("导入新医院 :" + wev.getName());
            	    }
                 	else
                 	{
                 		log.info("导入数据：该医院已存在，" + wev.getName());
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


}
