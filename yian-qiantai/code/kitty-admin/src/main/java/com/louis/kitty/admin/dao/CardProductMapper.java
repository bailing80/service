package com.louis.kitty.admin.dao;

import org.apache.ibatis.annotations.Param;

import com.louis.kitty.admin.model.CardProduct;

public interface CardProductMapper 
{
	
    int deleteByPrimaryKey(Long id);

    int insert(CardProduct record);

    int insertSelective(CardProduct record);

    CardProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CardProduct record);

    int updateByPrimaryKey(CardProduct record);
    
    String findByLable(@Param(value="serialnumber") String serialnumber);
    
}