package com.yian.admin.sevice.impl;


import java.io.InputStream;
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
import com.yian.admin.model.SysDoctor;
import com.yian.admin.model.SysDoctorVO;
import com.yian.admin.model.SysMarket;
import com.yian.admin.sevice.SysDoctorService;

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
 * --   客户信息维护管理
 *  
 */

@Service
@Slf4j
public class SysDoctorServiceImpl  implements SysDoctorService {
    
	private static final Logger log = LoggerFactory.getLogger(SysDoctorServiceImpl.class);

	@Autowired
	private SysDoctorMapper sysdoctorMapper;

	
	private final static String EXCEL_2003_DOWN = ".xls"; // 2003- 版本的excel
    private final static String EXCEL_2007_UP = ".xlsx"; // 2007+ 版本的excel

   

	@Override
	public int save(SysDoctor record)
	{		
		//record.setCreatetime(DateTimeUtils.getDateTime());
		//log.info("-系统时间 --:"+DateTimeUtils.getDateTime());
		if(record.getId() == null || record.getId() == 0)
		{			
			return sysdoctorMapper.insertSelective(record);			
		}
		return sysdoctorMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SysDoctor record) {
		return sysdoctorMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysDoctor> records) {
		for(SysDoctor record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysDoctor findById(Long id) {
		return sysdoctorMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		
		ColumnFilter columnFilter = pageRequest.getColumnFilter("name");
		
		if(columnFilter != null) {
			String name=columnFilter.getValue();
			log.debug("marketserviceImpl:"+name);
			return MybatisPageHelper.findPage(pageRequest, sysdoctorMapper, "findPageByLabel",columnFilter.getValue());
		}
		return MybatisPageHelper.findPage(pageRequest, sysdoctorMapper);
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
	public List<SysDoctor> findByLable(String name) {
		return sysdoctorMapper.findByLable(name);
	}
	
	@Override
	public SysDoctor findNameandTel(String name,String tel) {
		//log.info("select SQL"+name+""+tel);
		SysDoctor str=sysdoctorMapper.findNameandTel(name,tel);
		//log.info("SQL"+str);
		return str;
	}
	
	
	@Override
	public List<SysDoctorVO> findhospital(String name) {
		log.info("ServiceImpl ：" + name);
		return sysdoctorMapper.findhospital(name);
	}
	
   @Override
	public List<SysDoctor> findMarPage()
	{
		return sysdoctorMapper.findPage();
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
           
				/*
				 * if(wev != null ) { markets.add(wev);
				 * log.info("已存在 :"+wev.getName()+" 手机号:"+wev.getTel()); SysDoctor
				 * symark=this.findNameandTel(wev.getName(),wev.getTel()); if(symark == null )
				 * sysdoctorMapper.insertSelective(wev); else str="0000"; }
				 */
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
