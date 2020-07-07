package com.yian.admin.sevice.impl;


import java.io.InputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
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

import com.yian.admin.dao.SysDoctorMapper;
import com.yian.admin.dao.SysMarketMapper;
import com.yian.admin.dao.SysSalesmanMapper;
import com.yian.admin.model.SysDoctor;
import com.yian.admin.model.SysMarket;
import com.yian.admin.model.SysSalesman;
import com.yian.admin.sevice.SysMarketService;
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
 */

@Service
@Slf4j
public class SysMarketServiceImpl  implements SysMarketService {
    
	private static final Logger log = LoggerFactory.getLogger(SysMarketServiceImpl.class);

	@Autowired
	private SysMarketMapper sysmarketMapper;
	@Autowired
	private SysSalesmanMapper sysSalesmanMapper;

	@Autowired
	private SysDoctorMapper sysDoctorMapper;
	
	private final static String EXCEL_2003_DOWN = ".xls"; // 2003- 版本的excel
    private final static String EXCEL_2007_UP = ".xlsx"; // 2007+ 版本的excel

    /**
     *-- 提交客户信息
     */
    @Override
	public String vueSave(SysMarket record)
	{
    	    String str="";
		    SysSalesman recordSalesman = sysSalesmanMapper.findSalesName(record.getSellname());
		    if(recordSalesman != null)//check whether the salesman exsits in database
		    {
		    	SysMarket symark=sysmarketMapper.isExist(record.getActivityname(),record.getName(),record.getTel());
		    	if(symark !=null )
		    	{
		    		return str="该客户已注册 ";
		    	}
		    	else {
		    		record.setCreatetime(DateTimeUtils.getDateTime());//创建时间
			    	sysmarketMapper.insertSelective(record);
			    	return str="提交成功！";
		    	}
		    }
		    else
		    	return str = "该销售员不存在，请重新输入";
	}

	@Override
	public int save(SysMarket record)
	{		
		record.setCreatetime(DateTimeUtils.getDateTime());
		//log.info("-系统时间 --:"+DateTimeUtils.getDateTime());
		if(record.getId() == null || record.getId() == 0)
		{			
			return sysmarketMapper.insertSelective(record);			
		}
		return sysmarketMapper.updateByPrimaryKeySelective(record);
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

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		
		ColumnFilter columnFilter = pageRequest.getColumnFilter("name");
		
		if(columnFilter != null) {
			String name=columnFilter.getValue();
			log.debug("marketserviceImpl:"+name);
			return MybatisPageHelper.findPage(pageRequest, sysmarketMapper, "findPageByLabel",columnFilter.getValue());
		}
		return MybatisPageHelper.findPage(pageRequest, sysmarketMapper);
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
                   //{0=序号, 1=客户名称, 2=手机号, 3=医院名称, 4=科室, 5=详细地址, 6=创建时间}           
                   Object cellValue = this.getCellValue(cell);              	   
                   if(cell != null)
                   {
                       switch (j){
                           case 0 :                 	   
                        	   //log.info(" 序号 id :"+cellValue.toString());
                               //wev.setStrid(cellValue.toString());
                               break;
                           case 1:
                               wev.setName(cellValue.toString());
                               break;
                           case 2:
                               wev.setTel(cellValue.toString());
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
                               wev.setCreatetime(DateTimeUtils.getDateTime().toString());
                               break;   
                           default:
                               continue;
                       }
                    }
               }             
           
               if(wev != null )
               {
            	    markets.add(wev);
            	    log.info("已存在 :"+wev.getName()+" 手机号:"+wev.getTel());  
            	    SysMarket symark=sysmarketMapper.isExist(wev.getActivityname(),wev.getName(),wev.getTel());   
            	    if(symark == null )
                  	   sysmarketMapper.insertSelective(wev);	
                 	else
                 	   str="0000";
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
	public List<SysSalesman> getSalesNames() {
		log.info("Enter SysMarketServiceImpl getSalesNames:");
		List<SysSalesman> recordSales = new ArrayList<>();
		recordSales = sysSalesmanMapper.getSalesNames();
		return recordSales;
	}

}
