package com.github.woodylic.daout.service;

import com.github.woodylic.daout.entity.SmsTask;

public interface SmsTaskService {

	void insert(SmsTask smsTask);

	SmsTask selectByPrimaryKey(Long id);
}
