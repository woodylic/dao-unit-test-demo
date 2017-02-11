package com.github.woodylic.daout.dao;

import com.github.woodylic.daout.entity.SmsTask;

public interface SmsTaskDao {

	int insert(SmsTask smsTask);

	SmsTask selectByPrimaryKey(Long id);
}
