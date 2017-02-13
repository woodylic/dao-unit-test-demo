package com.github.woodylic.utdemo.dao;

import com.github.woodylic.utdemo.entity.SmsTask;

public interface SmsTaskDao {

	int insert(SmsTask smsTask);

	SmsTask selectByPrimaryKey(Long id);
}
