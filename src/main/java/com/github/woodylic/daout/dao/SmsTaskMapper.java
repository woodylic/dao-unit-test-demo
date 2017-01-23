package com.github.woodylic.daout.dao;

import com.github.woodylic.daout.entity.SmsTask;

import java.math.BigInteger;
import java.util.List;

public interface SmsTaskMapper {

	int insert(SmsTask smsTask);

	SmsTask selectByPrimaryKey(BigInteger id);

	List<SmsTask> selectAll();
}
