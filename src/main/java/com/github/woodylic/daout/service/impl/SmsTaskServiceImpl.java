package com.github.woodylic.daout.service.impl;

import com.github.woodylic.daout.entity.SmsTask;
import com.github.woodylic.daout.dao.SmsTaskDao;
import com.github.woodylic.daout.service.SmsTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service("smsTaskService")
public class SmsTaskServiceImpl implements SmsTaskService{

	@Autowired
	private SmsTaskDao smsTaskDao;

	public void insert(SmsTask smsTask) {

		if(smsTask == null)
		    throw new InvalidParameterException("smsTask should not be null");

		smsTaskDao.insert(smsTask);
	}

	public SmsTask selectByPrimaryKey(Long id) {

	    return smsTaskDao.selectByPrimaryKey(id);
	}
}
