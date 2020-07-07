package com.louis.kitty.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.louis.kitty.admin.model.CardPersonalRecord;

public interface CardPersonalRecordMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(CardPersonalRecord record);

    int insertSelective(CardPersonalRecord record);

    CardPersonalRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CardPersonalRecord record);

    int updateByPrimaryKey(CardPersonalRecord record);
    
    List<CardPersonalRecord> findPage();
    
    List<CardPersonalRecord> findPageByLabel(@Param(value="customercode") String customercode);

    List<CardPersonalRecord> findByLable(@Param(value="customercode") String customercode);
    
    CardPersonalRecord findItem(String customercode);
    
    int deleteByCustomercode(String customercode);
    
    int updateByCustomercode(CardPersonalRecord record);
}