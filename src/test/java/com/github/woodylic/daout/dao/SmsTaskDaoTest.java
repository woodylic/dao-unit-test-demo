package com.github.woodylic.daout.dao;

import com.github.woodylic.daout.entity.SmsTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:spring-context-test.xml")
public class SmsTaskDaoTest {

	@Autowired
	private SmsTaskDao mapper;

	@Test
	public void testInsert(){
		SmsTask newTask = new SmsTask();
		newTask.setUserId(10001L);
		newTask.setPhoneNumber("13700010002");
		newTask.setStatus(1);
		newTask.setMsgContent("Hello World!");
		newTask.setPlanSentTime(new Date(117, 2, 1));
		newTask.setCreateDate(new Date());

		int impactRows = mapper.insert(newTask);
		assertEquals(1, impactRows);
	}

	@Test
	public void testSelectByPrimaryKey(){
		SmsTask task = mapper.selectByPrimaryKey(1L);
		assertEquals("SmsTask{id=1, userId=10001, phoneNumber='13700010002', msgContent='test message', status=0, createDate=Wed Feb 01 00:00:00 CST 2017, planSentTime=Wed Mar 01 00:00:00 CST 2017, actualSentTime=null}", task.toString());
	}
}
