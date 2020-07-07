package com.louis.kitty.admin.sevice.impl;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.louis.kitty.admin.dao.SysEngremarkMapper;
import com.louis.kitty.admin.model.SysEngremark;
import com.louis.kitty.admin.sevice.SysEngremarkService;
import com.louis.kitty.core.page.PageRequest;
import com.louis.kitty.core.page.PageResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SysEngremarkServiceImpl implements SysEngremarkService
{
	
	private  final Logger log = LoggerFactory.getLogger(SysEngremarkServiceImpl.class); 
	@Autowired
	private SysEngremarkMapper sysEngremarkMapper;
	@Override
	public int save(SysEngremark record) {
	
		record.setCreateTime(new Date());
		return sysEngremarkMapper.insertSelective(record);
	}
	@Override
	public int delete(SysEngremark record) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int delete(List<SysEngremark> records) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public SysEngremark findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PageResult findPage(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	


	



}
