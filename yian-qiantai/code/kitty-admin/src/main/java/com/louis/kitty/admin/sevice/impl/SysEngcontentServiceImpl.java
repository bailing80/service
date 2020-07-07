package com.louis.kitty.admin.sevice.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.louis.kitty.admin.dao.SysEngcontentMapper;
import com.louis.kitty.admin.model.SysEngcontent;
import com.louis.kitty.admin.sevice.SysEngcontentService;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class SysEngcontentServiceImpl implements SysEngcontentService
{
	
	private  final Logger log = LoggerFactory.getLogger(SysEngcontentServiceImpl.class); 
	@Autowired
	private SysEngcontentMapper sysEngcontentMapper;
	
	
	@Override
	public List<SysEngcontent> selectList(String status) {
		// TODO Auto-generated method stub
		return sysEngcontentMapper.selectList(status);
	}

	
	
	@Override
	public int save(SysEngcontent record) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int delete(SysEngcontent record) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int delete(List<SysEngcontent> records) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public SysEngcontent findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PageResult findPage(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}





}
