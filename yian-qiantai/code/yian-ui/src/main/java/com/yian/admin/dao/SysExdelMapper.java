package com.yian.admin.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.yian.admin.model.SysExdel;
import com.yian.admin.model.SysMarket;
import com.yian.admin.model.SysClientAndExpress;

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

	SysExdel findNameandTel(String name, String telephone);

	void updateExpressInfo(String name, String telephone, String fromLocation, String expressName, String expressNumber,
			String giftSend);


}