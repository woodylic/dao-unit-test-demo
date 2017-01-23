package com.github.woodylic.daout;

import com.github.woodylic.daout.entity.SmsTask;
import com.github.woodylic.daout.service.SmsTaskService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by choli on 2017/1/18.
 */
public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:conf/spring-context.xml");
		SmsTaskService service = ctx.getBean("smsTaskService", SmsTaskService.class);

		SmsTask task = service.selectByPrimaryKey(new BigInteger("12001"));
		System.out.println(task.getId());
		System.out.println(task.toString());

//		SmsTask newTask = new SmsTask();
//		newTask.setUserId(new BigInteger("1234567890"));
//		newTask.setPhoneNumber("13700010002");
//		newTask.setStatus(1);
//		newTask.setMsgContent("Hello World!");
//		newTask.setPlanSentTime(new Date(2017, 2, 1));
//		newTask.setCreateDate(new Date());
//
//		int impactRows = service.insert(newTask);
//		System.out.println("Impact Rows: " + impactRows + ", id: " + newTask.getId());
	}
}
