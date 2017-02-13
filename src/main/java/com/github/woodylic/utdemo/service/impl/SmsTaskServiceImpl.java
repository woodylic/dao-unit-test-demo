package com.github.woodylic.utdemo.service.impl;

import com.github.woodylic.utdemo.entity.SmsTask;
import com.github.woodylic.utdemo.dao.SmsTaskDao;
import com.github.woodylic.utdemo.service.SmsTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;

@Service("smsTaskService")
public class SmsTaskServiceImpl implements SmsTaskService{

	@Autowired
	private SmsTaskDao smsTaskDao;

    @Transactional
	public void insert(SmsTask smsTask) {

		if(smsTask == null)
		    throw new InvalidParameterException("smsTask should not be null.");

		smsTaskDao.insert(smsTask);

        ////引发runtime error，测试回滚。
        //String string = null;
        //if(string.equals(""))
        //   string = null;
	}

    @Transactional
	public SmsTask selectByPrimaryKey(Long id) {

        if(id <= 0)
            throw new InvalidParameterException("ID should be larger than 0.");

	    return smsTaskDao.selectByPrimaryKey(id);
	}
}
