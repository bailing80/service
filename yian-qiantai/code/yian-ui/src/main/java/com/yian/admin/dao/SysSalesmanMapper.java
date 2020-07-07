package com.yian.admin.dao;


import java.util.List;
import com.yian.admin.model.SysSalesman;

public interface SysSalesmanMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(SysSalesman record);

    int insertSelective(SysSalesman record);

    SysSalesman selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysSalesman record);

    int updateByPrimaryKey(SysSalesman record);

	List<SysSalesman> findPage();
	
	SysSalesman findSalesName(String name);

	List<SysSalesman> getSalesNames();
}