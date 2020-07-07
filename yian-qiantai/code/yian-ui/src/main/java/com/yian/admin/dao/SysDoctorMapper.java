package com.yian.admin.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.yian.admin.model.SysDoctor;
import com.yian.admin.model.SysDoctorVO;

public interface SysDoctorMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(SysDoctor record);

    int insertSelective(SysDoctor record);

    SysDoctor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDoctor record);

    int updateByPrimaryKey(SysDoctor record);
    
    List<SysDoctor> findPage();
    
    List<SysDoctor> findPageByLabel(@Param(value="name") String name);

    List<SysDoctor> findByLable(@Param(value="name") String name);

	SysDoctor findNameandTel(String name, String tel);
	
	SysDoctor findNameandhospital(String name, String hospital);
	
	 List<SysDoctorVO> findhospital (String name);
}