package com.louis.kitty.admin.sevice.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import lombok.extern.slf4j.Slf4j;

import com.louis.kitty.admin.dao.CardProductMapper;
import com.louis.kitty.admin.model.CardProduct;
import com.louis.kitty.admin.sevice.CardProductService;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;


/**
 * 电子保修卡
 *  -产品对于关系
 * @author earl
 * @date October 12, 2019
 */
@Slf4j
@Service
public class CardProductServicelmpl implements CardProductService 
{

	private static final Logger log = LoggerFactory.getLogger(CardProductServicelmpl.class);
	
	
	@Autowired
	private CardProductMapper cardProductMapper;
	
	
	@Override
	public int save(CardProduct record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(CardProduct record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(List<CardProduct> records) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CardProduct findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String findByLable(String serialnumber) {
		return cardProductMapper.findByLable(serialnumber);
	}
	

}
