package com.louis.kitty.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.louis.kitty.admin.model.SysMarket;
import com.louis.kitty.admin.model.SysSales;

public interface SysMarketMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(SysMarket record);

    int insertSelective(SysMarket record);

    SysMarket selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMarket record);

    int updateByPrimaryKey(SysMarket record);
    
    List<SysMarket> findPage();
    
    List<SysMarket> findPageByLabel(@Param(value="name") String name);

    List<SysMarket> findByLable(@Param(value="name") String name);

	SysMarket isExist(String activityname, String name, String tel);
	
	SysMarket findNameandhospital(String name, String hospital);

	List<SysMarket> findPageByFilters(Map<String, Object> map);

	List<SysMarket> nameDuplicateCheck();

	List<SysMarket> telDuplicateCheck();
}