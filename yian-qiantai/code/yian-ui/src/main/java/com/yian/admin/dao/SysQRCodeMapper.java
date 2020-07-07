package com.yian.admin.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.yian.admin.model.SysQRCode;


public interface SysQRCodeMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(SysQRCode record);

    int insertSelective(SysQRCode record);

    SysQRCode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysQRCode record);

    int updateByPrimaryKey(SysQRCode record);
    
    List<SysQRCode> findPage();
    
    List<SysQRCode> findPageByLabel(@Param(value="name") String name);

    List<SysQRCode> findByLable(@Param(value="name") String name);
    
    SysQRCode findBymarket();
    
    
}