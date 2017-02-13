package com.github.woodylic.utdemo.service;

import com.github.woodylic.utdemo.entity.SmsTask;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-service.xml","classpath:spring-dao.xml"})
public class SmsTaskServiceIntegrationTest {

    @Autowired
    private SmsTaskService smsTaskService;

    @Ignore
    @Test
    public void testSelectByPrimaryKey() {
        SmsTask smsTask = smsTaskService.selectByPrimaryKey(1001L);
        System.out.println(smsTask.toString());
    }

    @Ignore
    @Test
    @Rollback
    @Transactional
    public void testInsert() {
        SmsTask smsTask = new SmsTask();
        smsTask.setUserId(100001L);
        smsTask.setMsgContent("Test Message");
        smsTask.setStatus(0);

        smsTaskService.insert(smsTask);
    }
}
