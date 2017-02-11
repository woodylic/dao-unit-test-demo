package com.github.woodylic.daout.service;

import com.github.woodylic.daout.dao.SmsTaskDao;
import com.github.woodylic.daout.entity.SmsTask;
import com.github.woodylic.daout.service.impl.SmsTaskServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

//@RunWith(SpringJUnit4ClassRunner.class)
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
    public void testInsert(){
        SmsTask smsTaskToBeInserted = new SmsTask();
        smsTaskToBeInserted.setId(1L);
        smsTaskToBeInserted.setMsgContent("Test Message");

        smsTaskServiceImpl.insert(smsTaskToBeInserted);

        verify(smsTaskDao).insert(smsTaskToBeInserted);
    }

    @Test(expected = InvalidParameterException.class)
    public void testInsertNullSmsTask() throws Exception {
        smsTaskServiceImpl.insert(null);
        verify(smsTaskDao).insert(null);
    }
}