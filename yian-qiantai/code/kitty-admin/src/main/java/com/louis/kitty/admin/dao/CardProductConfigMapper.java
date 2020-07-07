package com.louis.kitty.admin.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.louis.kitty.admin.model.CardProductConfig;

public interface CardProductConfigMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(CardProductConfig record);

    int insertSelective(CardProductConfig record);

    CardProductConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CardProductConfig record);

    int updateByPrimaryKey(CardProductConfig record);
    
    List<CardProductConfig> findPage();
    
    List<CardProductConfig> findPageByLabel(@Param(value="customercode") String customercode);

    List<CardProductConfig> findByLable(@Param(value="customercode") String customercode);
    
    CardProductConfig findItem(String customercode);
    
    int deleteByCustomercode(String customercode);
    
    int updateByCustomercode(CardProductConfig record);
}