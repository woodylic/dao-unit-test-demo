package com.github.woodylic.utdemo.service;

import com.github.woodylic.utdemo.dao.SmsTaskDao;
import com.github.woodylic.utdemo.entity.SmsTask;
import com.github.woodylic.utdemo.service.impl.SmsTaskServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:spring-service.xml","classpath:spring-dao.xml"})
public class SmsTaskServiceTest {

    @Mock
    private SmsTaskDao smsTaskDao;

    @InjectMocks
    private SmsTaskServiceImpl smsTaskServiceImpl;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSelectByPrimaryKey(){

        SmsTask mockSmsTask = new SmsTask();
        mockSmsTask.setId(1L);
        mockSmsTask.setMsgContent("Test Message");

        when(smsTaskDao.selectByPrimaryKey(1L)).thenReturn(mockSmsTask);

        SmsTask smsTaskReturnFromService = smsTaskServiceImpl.selectByPrimaryKey(1L);

        assertEquals(mockSmsTask.toString(), smsTaskReturnFromService.toString());

        verify(smsTaskDao).selectByPrimaryKey(1L);
    }

    @Test
    public void testInsert() throws Exception {
        SmsTask smsTaskToBeInserted = new SmsTask();
        smsTaskToBeInserted.setId(1L);
        smsTaskToBeInserted.setMsgContent("Test Message");

		when(smsTaskDao.insert(any(SmsTask.class))).then(invocation -> {
			Object[] args = invocation.getArguments();
			SmsTask smsTask = (SmsTask) args[0];

			assertNotNull(smsTask);
			assertEquals(Long.valueOf(1L), smsTask.getId());
			assertEquals("Test Message", smsTask.getMsgContent());

			return null;
		});

        smsTaskServiceImpl.insert(smsTaskToBeInserted);

        verify(smsTaskDao).insert(smsTaskToBeInserted);
    }

    @Test(expected = InvalidParameterException.class)
    public void testInsertNullSmsTask() throws Exception {
        smsTaskServiceImpl.insert(null);
        verify(smsTaskDao).insert(null);
    }
}