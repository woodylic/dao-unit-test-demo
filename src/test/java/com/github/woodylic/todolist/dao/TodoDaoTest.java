package com.github.woodylic.todolist.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.woodylic.todolist.entity.TodoItem;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)                         //指定使用Spring Test
@ContextConfiguration(value="classpath:spring-dao-test.xml")    //加载Spring Context
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class TodoDaoTest {

	@Autowired
	private TodoDao todoDao;

	@Test
    @DatabaseSetup(value="basic-test-data.xml")
    @ExpectedDatabase(value="expected-result-for-insert.xml")
	public void testInsert() throws InterruptedException {

        TodoItem todo = new TodoItem();
        todo.setTitle("Item Title");
        todo.setDescription("Item Description");
        todo.setStatus(0);

		int impactRows = todoDao.insert(todo);
		assertEquals(1, impactRows);
	}

    @Test
    @DatabaseSetup(value="basic-test-data.xml")
    @ExpectedDatabase(value="expected-result-for-update.xml")
    public void testUpdate() throws InterruptedException {

        TodoItem todo = new TodoItem();
        todo.setId(1L);
        todo.setTitle("Item Title");
        todo.setDescription("Item Description");
        todo.setStatus(1);

        int impactRows = todoDao.update(todo);
        assertEquals(1, impactRows);

        todo.setId(3L);
        impactRows = todoDao.update(todo);
        assertEquals(0, impactRows);
    }

    @Test
    @DatabaseSetup(value="basic-test-data.xml")
    @ExpectedDatabase(value="expected-result-for-delete.xml")
    public void testDeleteByPrimaryKey(){
        int impactRows = todoDao.deleteByPrimaryKey(Long.valueOf(1L));
        assertEquals(1, impactRows);

        impactRows = todoDao.deleteByPrimaryKey(Long.valueOf(3L));
        assertEquals(0, impactRows);
    }

	@Test
    @DatabaseSetup(value = {"basic-test-data.xml"})
    @DatabaseTearDown(value={"basic-test-data.xml"}, type= DatabaseOperation.DELETE)
	public void testSelectAll() throws InterruptedException {
		List<TodoItem> todoItems = todoDao.selectAll();
        assertEquals(2, todoItems.size());
	}

	@Test
    @DatabaseSetup(value = {"basic-test-data.xml"})
	public void testSelectByPrimaryKey() {

        TodoItem expectExist = todoDao.selectByPrimaryKey(1L);
        assertNotNull(expectExist);

        TodoItem expectNotExist = todoDao.selectByPrimaryKey(3L);
        assertNull(expectNotExist);
    }
}
