package com.louis.kitty.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.louis.kitty.admin.model.SysHospital;

public interface SysHospitalMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(SysHospital record);
  
    int insertSelective(SysHospital record);
  
    SysHospital selectByPrimaryKey(Long id);
  
    int updateByPrimaryKeySelective(SysHospital record);
  
    int updateByPrimaryKey(SysHospital record);
	     
    List<SysHospital> findPage();
    
    List<SysHospital> findPageByLabel(@Param(value="name") String name);

    List<SysHospital> findByLable(@Param(value="name") String name);
    
    SysHospital findByName(String name);

	List<SysHospital> findPageByFilters(Map<String, Object> map);

}