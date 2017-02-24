package com.github.woodylic.todolist.service

import com.github.woodylic.todolist.dao.TodoDao
import com.github.woodylic.todolist.entity.TodoItem
import com.github.woodylic.todolist.exception.TodoException
import com.github.woodylic.todolist.service.impl.TodoServiceImpl
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TodoServiceTest
{
    @Mock
    private lateinit var todoDaoMock: TodoDao

    @InjectMocks
    private lateinit var todoServiceImpl: TodoServiceImpl

    @Before
    fun init()
    {
        MockitoAnnotations.initMocks(this) //初始化所有@Mock标记的对象
    }

    @Test
    fun testInsert()
    {
        val aTodoItem = TodoItem()
        aTodoItem.id = 1
        aTodoItem. title = "To do something"

        whenever(todoDaoMock.insert(aTodoItem)).then({ invocation ->
            val args = invocation.arguments
            val actual = args[0] as TodoItem

            assertNotNull(actual)
            assertEquals(aTodoItem.toString(), actual.toString())

            1
        })

        todoServiceImpl.insert(aTodoItem)
        verify(todoDaoMock).insert(aTodoItem)
    }

    @Test(expected = TodoException::class)
    @Throws(TodoException::class)
    fun testInsertFailed()
    {
        whenever(todoDaoMock.insert(any())).thenReturn(0)
        todoServiceImpl.insert(mock())

        verify(todoDaoMock.insert(any()))
    }

    @Test
    @Throws(TodoException::class)
    fun testUpdate()
    {
        val expected = todoItemForTest()

        whenever(todoDaoMock.update(any())).then({ invocation ->
            val args = invocation.arguments
            val actual = args[0] as TodoItem

            assertEquals(expected.toString(), actual.toString())

            1
        })

        todoServiceImpl.update(expected)

        verify(todoDaoMock).update(expected)
    }

    @Test(expected = TodoException::class)
    @Throws(TodoException::class)
    fun testUpdateTodoItemWithoutId()
    {
        val aTodoItem = todoItemForTest()
        aTodoItem.id = null

        todoServiceImpl.update(aTodoItem)
        verify(todoDaoMock).update(aTodoItem)
    }

    @Test(expected = TodoException::class)
    @Throws(TodoException::class)
    fun testUpdateFailed()
    {
        whenever(todoDaoMock.update(any())).thenReturn(0)

        todoServiceImpl.update(mock())
        verify(todoDaoMock).update(mock())
    }

    @Test
    @Throws(TodoException::class)
    fun testSelectByPrimaryKey()
    {
        val expected = todoItemForTest()

        //定义todoDaoMock行为
        whenever(todoDaoMock.selectByPrimaryKey(1)).thenReturn(expected)

        //执行待测试方法
        val actual = todoServiceImpl.selectByPrimaryKey(1)

        //验证返回结果
        assertEquals(expected.toString(), actual.toString())

        //验证操作行为（todoDaoMock.selectByPrimaryKey(1L)被调用了一次。）
        verify(todoDaoMock).selectByPrimaryKey(1)
    }

    @Test(expected = TodoException::class)
    @Throws(TodoException::class)
    fun testSelectByInvalidKey()
    {
        todoServiceImpl.selectByPrimaryKey(0)
    }

    //目前todoService.selectAll仅仅是调用了todoDao.service，业务层没有任何逻辑，不需要单测。

    fun todoItemForTest() : TodoItem {
        val todoItem = TodoItem()
        todoItem.id = 1
        todoItem.title = "To do something"
        todoItem.description = "Details of a to do item."
        todoItem.status = 0

        return todoItem
    }
}