package com.louis.kitty.admin.sevice.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.louis.kitty.admin.dao.SysEnglishuserMapper;
import com.louis.kitty.admin.model.SysEnglishuser;
import com.louis.kitty.admin.sevice.SysEnglishuserService;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SysEnglishuserServiceImpl implements SysEnglishuserService
{
	
	private  final Logger log = LoggerFactory.getLogger(SysEnglishuserServiceImpl.class); 
	@Autowired
	private SysEnglishuserMapper sysEnglishuserMapper;

	/**
	 * 用户登录
	 */
	@Override
	public SysEnglishuser findByName(String phone) {
		return sysEnglishuserMapper.findByName(phone);
	}
	
	@Override
	public int updateByPrimaryKeySelective(SysEnglishuser record) {
		return sysEnglishuserMapper.updateByPrimaryKeySelective(record);
	}

	
	/**
	 * 用户注册
	 */
	@Transactional
	@Override
	public int save(SysEnglishuser record) {
		log.debug("用户新增");
		return sysEnglishuserMapper.insertSelective(record);

	}


	@Override
	public int delete(SysEnglishuser record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int delete(List<SysEnglishuser> records) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public SysEnglishuser findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PageResult findPage(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}



}
