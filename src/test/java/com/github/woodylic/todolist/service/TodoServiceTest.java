package com.github.woodylic.todolist.service;

import com.github.woodylic.todolist.dao.TodoDao;
import com.github.woodylic.todolist.entity.TodoItem;
import com.github.woodylic.todolist.exception.TodoException;
import com.github.woodylic.todolist.service.impl.TodoServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TodoServiceTest {

    @Mock
    private TodoDao todoDaoMock;

    @InjectMocks
    private TodoServiceImpl todoServiceImpl;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this); //初始化所有@Mock标记的对象
    }

    @Test
    public void testInsert() throws Exception {

        TodoItem expected = new TodoItem();
        expected.setId(1L);
        expected.setTitle("To do something");

        when(todoDaoMock.insert(any(TodoItem.class))).then(invocation -> {
            Object[] args = invocation.getArguments();
            TodoItem actual = (TodoItem) args[0];

            assertNotNull(actual);
            assertEquals(expected.toString(), actual.toString());

            return 1;
        });

        todoServiceImpl.insert(expected);
        verify(todoDaoMock).insert(expected);
    }

    @Test(expected = TodoException.class)
    public void testInsertFailed() throws TodoException {
        when(todoDaoMock.insert(any(TodoItem.class))).thenReturn(0);
        todoServiceImpl.insert(mock(TodoItem.class));

        verify(todoDaoMock.insert(any(TodoItem.class)));
    }

    @Test
    public void testUpdate() throws TodoException {

        TodoItem expected = getTodoItemForTest();

        when(todoDaoMock.update(any(TodoItem.class))).then(invocation -> {
            Object[] args = invocation.getArguments();
            TodoItem actual = (TodoItem) args[0];

            assertEquals(expected.toString(), actual.toString());

            return 1;
        });

        todoServiceImpl.update(expected);

        verify(todoDaoMock).update(expected);
    }

    @Test(expected = TodoException.class)
    public void testUpdateTodoItemWithoutId() throws TodoException {
        TodoItem todo = getTodoItemForTest();
        todo.setId(null);

        todoServiceImpl.update(todo);
        verify(todoDaoMock).update(todo);
    }

    @Test(expected = TodoException.class)
    public void testUpdateFailed() throws TodoException {

        when(todoDaoMock.update(any(TodoItem.class))).thenReturn(0);

        TodoItem todo = mock(TodoItem.class);
        todoServiceImpl.update(todo);
        verify(todoDaoMock).update(todo);
    }

    @Test
    public void testSelectByPrimaryKey() throws TodoException {

        TodoItem expected = getTodoItemForTest();

        //定义todoDaoMock行为
        when(this.todoDaoMock.selectByPrimaryKey(1L)).thenReturn(expected);

        //执行待测试方法
        TodoItem actual = todoServiceImpl.selectByPrimaryKey(1L);

        //验证返回结果
        assertEquals(expected.toString(), actual.toString());

        //验证操作行为（todoDaoMock.selectByPrimaryKey(1L)被调用了一次。）
        verify(this.todoDaoMock).selectByPrimaryKey(1L);
    }

    @Test(expected = TodoException.class)
    public void testSelectByInvalidKey() throws TodoException {
        todoServiceImpl.selectByPrimaryKey(0L);
    }

    @Ignore
    @Test
    public void testSelectAll() {
        //目前todoService.selectAll仅仅是调用了todoDao.service，业务层没有任何逻辑，不需要单测。
    }

    private TodoItem getTodoItemForTest() {
        TodoItem todoItem = new TodoItem();
        todoItem.setId(1L);
        todoItem.setTitle("To do something");
        todoItem.setDescription("Details of a to do item.");
        todoItem.setStatus(0);

        return todoItem;
    }
}