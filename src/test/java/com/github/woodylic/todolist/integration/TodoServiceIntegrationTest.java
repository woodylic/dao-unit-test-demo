package com.github.woodylic.todolist.integration;

import com.github.woodylic.todolist.entity.TodoItem;
import com.github.woodylic.todolist.exception.TodoException;
import static org.junit.Assert.*;

import com.github.woodylic.todolist.service.TodoService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-service.xml","classpath:spring-dao.xml"})
public class TodoServiceIntegrationTest {

    @Autowired
    private TodoService todoService;

    @Ignore
    @Test
    public void testSelectByPrimaryKey() throws TodoException {

        TodoItem todoItem = todoService.selectByPrimaryKey(1L);

        String expectedString = "TodoItem{id=1, title='learn C#', description='Professional C#', status=1}";
        assertEquals(expectedString, todoItem.toString());
    }

    @Ignore
    @Test
    @Rollback
    @Transactional
    public void testInsert() throws TodoException, SQLException {

        //新建待插入todo item
        TodoItem todo = new TodoItem();
        todo.setTitle("Item Title");
        todo.setDescription("Item Description");
        todo.setStatus(0);

        //执行insert操作
        todoService.insert(todo);

        assertNotNull(todo.getId());
        System.out.print(todo.toString());
    }
}
