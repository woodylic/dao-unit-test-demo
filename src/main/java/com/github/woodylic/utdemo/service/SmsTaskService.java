package com.github.woodylic.utdemo.service;

import com.github.woodylic.utdemo.entity.SmsTask;

public interface SmsTaskService {

	void insert(SmsTask smsTask);

	SmsTask selectByPrimaryKey(Long id);
}
