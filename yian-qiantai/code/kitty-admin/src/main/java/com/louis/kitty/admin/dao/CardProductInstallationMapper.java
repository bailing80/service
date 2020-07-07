package com.louis.kitty.admin.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.louis.kitty.admin.model.CardProductInstallation;

public interface CardProductInstallationMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(CardProductInstallation record);

    int insertSelective(CardProductInstallation record);

    CardProductInstallation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CardProductInstallation record);

    int updateByPrimaryKey(CardProductInstallation record);
    
    List<CardProductInstallation> findPage();
    
    List<CardProductInstallation> findPageByLabel(@Param(value="customercode") String customercode);

    List<CardProductInstallation> findByLable(@Param(value="customercode") String customercode);
    
    CardProductInstallation findItem(String customercode);
    
    int deleteByCustomercode(String customercode);
    
    int updateByCustomercode(CardProductInstallation record);
}