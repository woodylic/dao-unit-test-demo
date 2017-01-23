package com.github.woodylic.daout.dao;

import com.github.woodylic.daout.entity.SmsTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:spring-context-test.xml")
public class SmsTaskMapperTest {

	@Autowired
	private SmsTaskMapper mapper;

	@Test
	public void testInsert(){
		SmsTask newTask = new SmsTask();
		newTask.setUserId(new BigInteger("1234567890"));
		newTask.setPhoneNumber("13700010002");
		newTask.setStatus(1);
		newTask.setMsgContent("Hello World!");
		newTask.setPlanSentTime(new Date(2017, 2, 1));
		newTask.setCreateDate(new Date());

		int impactRows = mapper.insert(newTask);
		assertEquals(1, impactRows);
	}

	@Test
	public void testSelectAll() {
		List<SmsTask> smsTasks = mapper.selectAll();
		assertEquals(1, smsTasks.size());
	}

	@Test
	public void testSelectByPrimaryKey(){
		SmsTask task = mapper.selectByPrimaryKey(new BigInteger("1"));
		assertEquals("SmsTask{id=1, userId=10001, phoneNumber='13700010002', msgContent='test message', status=0, createDate=Wed Feb 01 00:00:00 CST 2017, planSentTime=Wed Mar 01 00:00:00 CST 2017, actualSentTime=null}", task.toString());
	}
}
