package com.louis.kitty.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.louis.kitty.admin.model.CardHospital;

public interface CardHospitalMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(CardHospital record);

    int insertSelective(CardHospital record);

    CardHospital selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CardHospital record);

    int updateByPrimaryKey(CardHospital record);
    
    List<CardHospital> findPage();
    
    List<CardHospital> findPageByLabel(@Param(value="name") String name);

    List<CardHospital> findByLable(@Param(value="name") String name);
    
    CardHospital findHospital(String serialnumber);
    
    List<CardHospital> findPageByFilters(Map<String, Object> map);
    
    CardHospital getLatestCustomercode();
}