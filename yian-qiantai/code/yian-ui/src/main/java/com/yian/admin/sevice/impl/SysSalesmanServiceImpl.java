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
import com.yian.admin.dao.SysSalesmanMapper;
import com.yian.admin.model.SysDoctor;
import com.yian.admin.model.SysDoctorVO;
import com.yian.admin.model.SysMarket;
import com.yian.admin.model.SysSalesman;
import com.yian.admin.sevice.SysDoctorService;
import com.yian.admin.sevice.SysSalesmanService;
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
public class SysSalesmanServiceImpl  implements SysSalesmanService {
    
	private static final Logger log = LoggerFactory.getLogger(SysSalesmanServiceImpl.class);

	@Autowired
	private SysSalesmanMapper sysSalesmanMapper;

	
	@Override
	public int save(SysSalesman record)
	{		
		//record.setCreatetime(DateTimeUtils.getDateTime());
		//log.info("-系统时间 --:"+DateTimeUtils.getDateTime());
		if(record.getId() == null || record.getId() == 0)
		{			
			return sysSalesmanMapper.insertSelective(record);			
		}
		return sysSalesmanMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SysSalesman record) {
		return sysSalesmanMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysSalesman> records) {
		for(SysSalesman record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysSalesman findById(Long id) {
		return sysSalesmanMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		
		ColumnFilter columnFilter = pageRequest.getColumnFilter("name");
		
		if(columnFilter != null) {
			String name=columnFilter.getValue();
			log.debug("marketserviceImpl:"+name);
			return MybatisPageHelper.findPage(pageRequest, sysSalesmanMapper, "findPageByLabel",columnFilter.getValue());
		}
		return MybatisPageHelper.findPage(pageRequest, sysSalesmanMapper);
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
	public List<SysSalesman> findMarPage()
	{
		return sysSalesmanMapper.findPage();
	}



	@Override
	public SysSalesman findSalesName(String name)
	{	
		return sysSalesmanMapper.findSalesName(name);
	}


	
   


}
