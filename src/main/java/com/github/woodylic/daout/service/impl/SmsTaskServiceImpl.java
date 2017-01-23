package com.github.woodylic.daout.service.impl;

import com.github.woodylic.daout.entity.SmsTask;
import com.github.woodylic.daout.dao.SmsTaskMapper;
import com.github.woodylic.daout.service.SmsTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * Created by choli on 2017/1/18.
 */
@Service("smsTaskService")
public class SmsTaskServiceImpl implements SmsTaskService{

	@Autowired
	private SmsTaskMapper smsTaskMapper;

	public int insert(SmsTask smsTask) {
		return smsTaskMapper.insert(smsTask);
	}

	public SmsTask selectByPrimaryKey(BigInteger id) {
		return smsTaskMapper.selectByPrimaryKey(id);
	}
}
