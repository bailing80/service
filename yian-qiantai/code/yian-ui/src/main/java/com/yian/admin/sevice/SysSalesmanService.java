package com.yian.admin.sevice;

import java.util.List;
import com.yian.admin.model.SysSalesman;
import com.louis.kitty.core.service.CurdService;

/**
 * -销售员管理管理
 * @author earl
 * @date august 8, 2019
 */
public interface SysSalesmanService extends CurdService<SysSalesman> {



	List<SysSalesman> findMarPage();

	SysSalesman findSalesName(String name);

	int save(SysSalesman record);


}
