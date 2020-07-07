package com.louis.kitty.admin.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.louis.kitty.admin.model.SysEnglishuser;


public interface SysEnglishuserMapper 
{

	 	int deleteByPrimaryKey(Long id);

	    int insert(SysEnglishuser record);

	    int insertSelective(SysEnglishuser record);

	    SysEnglishuser selectByPrimaryKey(Long id);

	    int updateByPrimaryKeySelective(SysEnglishuser record);

	    int updateByPrimaryKey(SysEnglishuser record);
	    
	    List<SysEnglishuser> findPage();
	    
	    SysEnglishuser findByName(@Param(value="phone") String phone);
	    
}
