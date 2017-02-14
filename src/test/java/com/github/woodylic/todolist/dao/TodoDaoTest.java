package com.github.woodylic.todolist.dao;

import com.github.woodylic.todolist.entity.TodoItem;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:spring-dao-test.xml")
@Transactional                                          //h2数据库在unit test启动后只创建一次
@TransactionConfiguration(defaultRollback = true)       //故每次写操作后需要回滚
public class TodoDaoTest {

	@Autowired
	private TodoDao todoDao;

	@Test
	public void testInsert() throws InterruptedException {

        TodoItem todo = new TodoItem();
        todo.setTitle("Item Title");
        todo.setDescription("Item Description");
        todo.setStatus(0);

		int impactRows = todoDao.insert(todo);
		assertEquals(1, impactRows);
	}

    @Test
    public void testUpdate() throws InterruptedException {

        TodoItem todo = new TodoItem();
        todo.setId(1L);
        todo.setTitle("Item Title");
        todo.setDescription("Item Description");
        todo.setStatus(0);

        int impactRows = todoDao.update(todo);
        assertEquals(1, impactRows);
    }

	@Test
	public void testSelectAll() throws InterruptedException {

		List<TodoItem> todoItems = todoDao.selectAll();
        assertEquals(3, todoItems.size());
	}

	@Test
	public void testSelectByPrimaryKey() {

        String expectedString = "TodoItem{id=1, title='learn C#', description='Professional C#', status=1}";

        TodoItem actual = todoDao.selectByPrimaryKey(1L);
        assertNotNull(actual);

        assertEquals(expectedString, actual.toString());
    }
}
