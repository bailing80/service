package com.louis.kitty.admin.sevice.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import lombok.extern.slf4j.Slf4j;

import com.louis.kitty.admin.dao.CardXuliehaoMapper;
import com.louis.kitty.admin.model.CardXuliehao;
import com.louis.kitty.admin.sevice.CardXuliehaoService;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;


/**
 * 电子保修卡
 *  -产品对于关系
 * @author earl
 * @date October 12, 2019
 */
@Service
@Slf4j

public class CardXuliehaoServicelmpl implements CardXuliehaoService 
{

	private static final Logger log = LoggerFactory.getLogger(CardXuliehaoServicelmpl.class);
	
	
	@Autowired 
	private CardXuliehaoMapper cardProductMapper;
	
	@Override
	public String findxuliehao(String serial) {
		//log.info("————————xuliehao： "+serial.substring(0, 2));
		String str;
		str=cardProductMapper.findxuliehao(serial);
		log.info("————————xuliehao： "+str);
		return str;
	}

	@Override
	public int save(CardXuliehao record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(CardXuliehao record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(List<CardXuliehao> records) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CardXuliehao findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
