package com.louis.kitty.admin.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.louis.kitty.admin.model.CardPersonal;

public interface CardPersonalMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(CardPersonal record);

    int insertSelective(CardPersonal record);

    CardPersonal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CardPersonal record);

    int updateByPrimaryKey(CardPersonal record);
    
    List<CardPersonal> findPage();
    
    List<CardPersonal> findPageByLabel(@Param(value="name") String name);

    List<CardPersonal> findByLable(@Param(value="name") String name);

	CardPersonal findClient(String serialnumber);

	List<CardPersonal> findPageByFilters(Map<String, Object> map);
	
	CardPersonal getLatestCustomercode();
}