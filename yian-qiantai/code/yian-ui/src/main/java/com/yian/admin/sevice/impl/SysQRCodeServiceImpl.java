package com.yian.admin.sevice.impl;


import java.io.FileOutputStream;
import java.io.File;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.yian.admin.dao.SysQRCodeMapper;
import com.yian.admin.model.SysQRCode;
import com.yian.admin.sevice.SysQRCodeService;
import com.yian.admin.util.PasswordUtils;
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
	public int importQrcode(MultipartFile file, String activityName)//读取二维码文件，并保存到数据库中
	{
		   log.info("Enter SysQRCodeServiceImpl importQrcode function:");
		    SysQRCode record = new SysQRCode();
		    //String path="J:/images";	      
		    String path="/sysmarket/images";
		    //获取文件名称
			String fileName = file.getOriginalFilename();
			//获取文件名后缀
	       // Calendar currTime = Calendar.getInstance();
	        //String time = String.valueOf(currTime.get(Calendar.YEAR))+String.valueOf((currTime.get(Calendar.MONTH)+1));
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
            //项目url，这里可以使用常量或者去数据字典获取相应的url前缀；
            String fileUrl="http://localhost:6090"+"/images/"+fileName;
            //文件获取路径      
            log.info("---fileUrl:"+fileUrl);
            record.setName(activityName);
            record.setPathqrcode(fileUrl);
            save(record);
	     }
	     return 1;
	}
	
	@Override
	public int save(SysQRCode record)
	{
		log.info("Enter SysQRCodeServiceImpl save function:");
		record.setCreatetime(DateTimeUtils.getDateTime());
		
		if(record.getId() == null || record.getId() == 0) {	
			
			return sysQRCodeMapper.insertSelective(record);			
		}

		return sysQRCodeMapper.updateByPrimaryKeySelective(record);
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
				 targetPath = "j:\\" + record.getId() + ".png";
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
	@Override
	public PageResult findPage(PageRequest pageRequest) {
		log.info("Enter SysQRCodeServiceImpl findPage function:");			
		//String str=this.StrResult();
		ColumnFilter columnFilter = pageRequest.getColumnFilter("name");
		//String name=columnFilter.getValue();
	    //log.info("targetPath:"+str);
		
		if(columnFilter != null) {
			return MybatisPageHelper.findPage(pageRequest, sysQRCodeMapper, "findPageByLabel",columnFilter.getValue());
		}
		
		return MybatisPageHelper.findPage(pageRequest, sysQRCodeMapper);

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
   
   /**
    * 活动图片
    * status 等于零
    */
   @Override
	public SysQRCode findBymarket()
	{
	   log.info("Enter SysQRCodeServiceImpl findBymarket function:");	   
	   return sysQRCodeMapper.findBymarket();
	}
   
}
