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
import com.louis.kitty.admin.dao.SysSalesMapper;
import com.louis.kitty.admin.model.SysSales;
import com.louis.kitty.admin.sevice.SysSalesService;
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
 *  销售人员管理 
 */

@Service
@Slf4j
public class SysSalesServiceImpl  implements SysSalesService {
    
	private static final Logger log = LoggerFactory.getLogger(SysSalesServiceImpl.class);

	@Autowired
	private SysSalesMapper sysSalesMapper;
	
	private final static String EXCEL_2003_DOWN = ".xls"; // 2003- 版本的excel
    private final static String EXCEL_2007_UP = ".xlsx"; // 2007+ 版本的excel

	@Override
	public int save(SysSales record)
	{
		log.info("Enter SysSalesServiceImpl save function:");
		if(record.getId() == null || record.getId() == 0)//新增
		{
			SysSales temp = sysSalesMapper.findByName(record.getName());
			if(temp == null )
			{
				log.info("新增：销售人员名称" + record.getName());
				sysSalesMapper.insertSelective(record);
				return 1;//add an item successfully			
			}
			else {
				log.info("该销售人员已存在，新增失败。");
				return 2;//add failed
			}

		}
		else {//编辑
			SysSales temp = sysSalesMapper.findByName(record.getName());
			if(temp != null )
			{
				log.info("编辑：销售人员名称" + record.getName() );
				sysSalesMapper.updateByPrimaryKeySelective(record);
				return 3;//edit an item successfully
			}
			else {
				log.info("编辑失败，该销售人员不存在！");
				return 4;//edit failed
			}
		}
		
		
		//log.info("-系统时间 --:"+DateTimeUtils.getDateTime());
		/*
		if(record.getId() == null || record.getId() == 0)
		{			
			return sysSalesMapper.insertSelective(record);			
		}
		return sysSalesMapper.updateByPrimaryKeySelective(record);*/
	}

	@Override
	public int delete(SysSales record) {
		return sysSalesMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysSales> records) {
		for(SysSales record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysSales findById(Long id) {
		return sysSalesMapper.selectByPrimaryKey(id);
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
		ColumnFilter columnFilter1 = pageRequest.getColumnFilter("type");
		String name=columnFilter.getValue();
		String type = columnFilter1.getValue();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("type", type);
		log.info("filters: name_ " + name + ",type_" + type);

		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		List<SysSales> records = sysSalesMapper.findPageByFilters(map);
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
	public List<SysSales> findByLable(String name) {
		return sysSalesMapper.findByLable(name);
	}
	
	@Override
	public List<SysSales> findByFilters(PageRequest pageRequest) {
		ColumnFilter columnFilter = pageRequest.getColumnFilter("name");
		ColumnFilter columnFilter1 = pageRequest.getColumnFilter("type");
		String name=columnFilter.getValue();
		String type = columnFilter1.getValue();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("type", type);
		List<SysSales> records = sysSalesMapper.findPageByFilters(map);
		return records;
	}
	
	@Override
	public SysSales findByName(String name) {
		//log.info("select SQL"+name+""+tel);
		SysSales str=sysSalesMapper.findByName(name);
		//log.info("SQL"+str);
		return str;
	}
	
	
	
   @Override
	public List<SysSales> findMarPage()
	{
	   log.info("----------------------");
		return sysSalesMapper.findPage();
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
	   List<SysSales> hospitals=new ArrayList<>();
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
               
               SysSales wev=new SysSales();
			   //遍历列
               for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) 
               {           	   	   
                   Cell cell = row.getCell(j);
                   //{0=销售人员名称, 1=Type，2=Status，3=省份}                       	   
                   if(cell != null)
                   {
                	   Object cellValue = this.getCellValue(cell);
                       switch (j){
                           case 0 :
                               wev.setName(cellValue.toString());
                               break;
                           case 1:
                               wev.setType(cellValue.toString());
                               break;
                           case 2: 
                               wev.setStatus(cellValue.toString());
                               break;
                           case 3: 
                               wev.setProvincial(cellValue.toString());
                               break;
                           default:
                               continue;
                       }
                    }
               }             
           
               if(wev != null && wev.getName() != null)
               {
            	    hospitals.add(wev);  
            	    SysSales record=this.findByName(wev.getName());   
            	    if(record == null )
            	    {
                  	   	sysSalesMapper.insertSelective(wev);
                  	    log.info("导入新销售人员 :" + wev.getName());
            	    }
                 	else
                 	{
                 		log.info("导入数据：该销售人员已存在，" + wev.getName());
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
