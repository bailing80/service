package com.louis.kitty.admin.sevice.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.louis.kitty.admin.dao.SysQRCodeMapper;
import com.louis.kitty.admin.model.SysExdel;
import com.louis.kitty.admin.model.SysMarket;
import com.louis.kitty.admin.model.SysQRCode;
import com.louis.kitty.admin.sevice.SysQRCodeService;
import com.louis.kitty.admin.util.PasswordUtils;
import com.louis.kitty.common.utils.DateTimeUtils;
import com.louis.kitty.core.page.ColumnFilter;
import com.louis.kitty.core.page.MybatisPageHelper;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author huigai
 * augest 22 2019
 * 二维码维护管理
 * update:earl
 * 
 *  
 */

@Service
@Slf4j
public class SysQRCodeServiceImpl  implements SysQRCodeService {
    
	private static final Logger log = LoggerFactory.getLogger(SysQRCodeServiceImpl.class);

	@Autowired
	private SysQRCodeMapper sysQRCodeMapper;

	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public int importQrcode(MultipartFile file, String name, String stats)//读取二维码文件，并保存到数据库中
	{
	   log.info("Enter SysQRCodeServiceImpl importQrcode function:");
	   SysQRCode record = new SysQRCode();
       record.setName(name);
       record.setStats(stats);
	   if(file == null)
	   {
		    log.info("上传文件为空，");
	   }
	   else {
			String path="/sysmarket/images";   
			//String path="E:\\images";
			//获取文件名称
			String fileName = file.getOriginalFilename();
			//获取文件名后缀
	        String suffix = fileName.substring(file.getOriginalFilename().lastIndexOf("."));
	        suffix = suffix.toLowerCase();
	        if(suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".png")/*|| suffix.equals(".gif")*/)
	        {
	            fileName = PasswordUtils.getSalt().toString()+suffix;
	            File targetFile = new File(path, fileName);
	            if(!targetFile.getParentFile().exists())
	            {    //注意，判断父级路径是否存在
	                targetFile.getParentFile().mkdirs();
	            }
		        long size = 0;
	            //保存
	            try {
	                file.transferTo(targetFile);
	                size = file.getSize();
	            } catch (Exception e) {
	                e.printStackTrace();
	                return 0;
	            }
			    String fileUrl="http://39.105.157.251:6090"+"/images/"+fileName;
			    log.info("---fileUrl:"+fileUrl);
			    record.setPathqrcode(fileUrl);
	     }		
	   }
	   return save(record);
	}
	
	@Override
	public int save(SysQRCode record)
	{
		log.info("Enter SysQRCodeServiceImpl save function:");
		
		log.info("qrcode name:" + record.getName() + ", path:" + record.getPathqrcode() + ",stats:" + record.getStats());
		SysQRCode temp = sysQRCodeMapper.findName(record.getName());
		if(temp == null )//add a new qrcode into database
		{
			record.setCreatetime(DateTimeUtils.getDateTime());
			sysQRCodeMapper.insertSelective(record);
			log.info("成功插入数据：二维码名称" + record.getName());
			return 1;
		}
		else {//update an existing qrcode in database
			record.setId(temp.getId());
			sysQRCodeMapper.updateByPrimaryKeySelective(record);
			log.info("该数据项已存在，更新数据库。record.id:" + record.getId());
			return 2;
		}
/*		
		if(record.getId() == null || record.getId() == 0) {	
			
			return sysQRCodeMapper.insertSelective(record);			
		}

		return sysQRCodeMapper.updateByPrimaryKeySelective(record);*/
	}

	@Override
	public int delete(SysQRCode record) {
		return sysQRCodeMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysQRCode> records) {
		for(SysQRCode record:records) {
			delete(record);
		}
		return 1;
	}
	

	@Override
	public SysQRCode findById(Long id) {
		return sysQRCodeMapper.selectByPrimaryKey(id);
	}

	@Override
	public String StrResult()
	{
		String targetPath="";		
		List<SysQRCode> records = sysQRCodeMapper.findPage();
		for(SysQRCode record:records) {
			try {
				byte[] buf = record.getQrcodetext();
				 targetPath = "E:\\" + record.getId() + ".png";
				File file = new File(targetPath);
			    FileOutputStream fos = new FileOutputStream(file);
			    fos.write(buf);
			    fos.flush();
			    fos.close();
			}
			catch (Exception e)
			{
			    e.printStackTrace();
			}
		}
		return targetPath;
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
		log.info("Enter SysQRCodeServiceImpl findPage function:");			
		//String str=this.StrResult();
		ColumnFilter columnFilter = pageRequest.getColumnFilter("name");
		ColumnFilter columnFilter1 = pageRequest.getColumnFilter("stats");
		String name=columnFilter.getValue();
		String stats = columnFilter1.getValue();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("stats", stats);

		log.info("filters: name_ " + name + ", stats_" + stats);

		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		List<SysQRCode> records = sysQRCodeMapper.findPageByFilters(map);
		Object result = records;//ReflectionUtils.invoke(mapper, queryMethodName, args);
		return getPageResult(pageRequest, new PageInfo((List) result));
/*		
		if(columnFilter != null) {
			return MybatisPageHelper.findPage(pageRequest, sysQRCodeMapper, "findPageByLabel",columnFilter.getValue());
		}
		
		return MybatisPageHelper.findPage(pageRequest, sysQRCodeMapper);*/

	}

	@Override
	public List<SysQRCode> findByLable(String name) {
		return sysQRCodeMapper.findByLable(name);
	}
	
   @Override
	public List<SysQRCode> findMarPage()
	{
	   log.info("Enter SysQRCodeServiceImpl findMarPage function:");
	   
	   return sysQRCodeMapper.findPage();
	}
}
