package com.louis.kitty.admin.dao;

import java.util.List;
import com.louis.kitty.admin.model.SysExdelReturn;

public interface SysExdelReturnMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(SysExdelReturn record);

    int insertSelective(SysExdelReturn record);

    SysExdelReturn selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysExdelReturn record);

    int updateByPrimaryKey(SysExdelReturn record);
    
    List<SysExdelReturn> findPage();
}