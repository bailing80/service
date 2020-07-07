package com.louis.kitty.admin.sevice;


import com.louis.kitty.admin.model.CardProduct;
import com.louis.kitty.core.service.CurdService;

/**
 * 电子保修卡
 *  -产品对于关系
 * @author earl
 * @date October 12, 2019
 */
public interface CardProductService extends CurdService<CardProduct> 
{

	String findByLable(String serialnumber);
	
}
