package com.yian.admin.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.yian.admin.model.SysMarket;

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
	
	
}