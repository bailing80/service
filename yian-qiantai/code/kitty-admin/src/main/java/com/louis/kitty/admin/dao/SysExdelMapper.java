package com.louis.kitty.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.louis.kitty.admin.model.SysExdel;
import com.louis.kitty.admin.model.SysMarket;
import com.louis.kitty.admin.model.SysClientAndExpress;

public interface SysExdelMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(SysExdel record);

    int insertSelective(SysExdel record);

    SysExdel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysExdel record);

    int updateByPrimaryKey(SysExdel record);
    
    List<SysExdel> findPage();
    
    List<SysMarket> importClient();
    
    List<SysClientAndExpress> exportClientAndExpress();
    
    List<SysExdel> findPageByLabel(@Param(value="name") String name);

    List<SysExdel> findByLable(@Param(value="name") String name);

	SysExdel isExist(String activityname, String name, String telephone);

	void updateExpressStatus(String activityname, String name, String telephone, String statusExp);

	List<SysExdel> findPageByFilters(Map<String, Object> map);

}