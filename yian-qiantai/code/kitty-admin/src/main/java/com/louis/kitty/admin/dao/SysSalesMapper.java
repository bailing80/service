package com.louis.kitty.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.louis.kitty.admin.model.SysSales;

public interface SysSalesMapper {
	
    int deleteByPrimaryKey(Long id);
	
    int insert(SysSales record);
  
    int insertSelective(SysSales record);
  
    SysSales selectByPrimaryKey(Long id);
  
    int updateByPrimaryKeySelective(SysSales record);
  
    int updateByPrimaryKey(SysSales record);
	 
    List<SysSales> getSalesNames();
    
    List<SysSales> findPage();
    
    List<SysSales> findPageByLabel(@Param(value="name") String name);

    List<SysSales> findByLable(@Param(value="name") String name);
    
    SysSales findByName(String name);

	List<SysSales> findPageByFilters(Map<String, Object> map);

}