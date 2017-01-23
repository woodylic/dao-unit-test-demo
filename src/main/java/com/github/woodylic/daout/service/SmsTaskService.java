package com.github.woodylic.daout.service;

import com.github.woodylic.daout.entity.SmsTask;

import java.math.BigInteger;

/**
 * Created by choli on 2017/1/18.
 */
public interface SmsTaskService {

	int insert(SmsTask smsTask);

	SmsTask selectByPrimaryKey(BigInteger id);

}
