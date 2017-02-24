package com.github.woodylic.todolist.dao

import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseOperation
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DatabaseTearDown
import com.github.springtestdbunit.annotation.ExpectedDatabase
import com.github.woodylic.todolist.entity.TodoItem
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener

@RunWith(SpringJUnit4ClassRunner::class)                            //指定使用Spring Test
@ContextConfiguration(value = "classpath:spring-dao-test.xml")      //加载Spring Context
@TestExecutionListeners(DependencyInjectionTestExecutionListener::class, DbUnitTestExecutionListener::class)
class TodoDaoTest
{
    @Autowired
    private lateinit var todoDao: TodoDao

    @Test
    @DatabaseSetup(value = "basic-test-data.xml")
    @ExpectedDatabase(value = "expected-result-for-insert.xml")
    fun testInsert()
    {
        val todo = TodoItem()
        todo.title = "Item Title"
        todo.description = "Item Description"

        val impactRows = todoDao.insert(todo)
        assertEquals(1, impactRows.toLong())
    }

    @Test
    @DatabaseSetup(value = "basic-test-data.xml")
    @ExpectedDatabase(value = "expected-result-for-update.xml")
    fun testUpdate()
    {
        val todo = TodoItem()
        todo.id = 1
        todo.title = "Item Title"
        todo.description = "Item Description"
        todo.status = 1

        var impactRows = todoDao.update(todo)
        assertEquals(1, impactRows.toLong())

        todo.id = 3
        impactRows = todoDao.update(todo)
        assertEquals(0, impactRows.toLong())
    }

    @Test
    @DatabaseSetup(value = "basic-test-data.xml")
    @ExpectedDatabase(value = "expected-result-for-delete.xml")
    fun testDeleteByPrimaryKey()
    {
        var impactRows = todoDao.deleteByPrimaryKey(1)
        assertEquals(1, impactRows.toLong())

        impactRows = todoDao.deleteByPrimaryKey(3)
        assertEquals(0, impactRows.toLong())
    }

    @Test
    @DatabaseSetup(value = "basic-test-data.xml")
    fun testSelectAll()
    {
        val todoItems = todoDao.selectAll()
        assertEquals(2, todoItems.size.toLong())
    }

    @Test
    @DatabaseSetup(value = *arrayOf("basic-test-data.xml"))
    fun testSelectByPrimaryKey()
    {
        val expectExist = todoDao.selectByPrimaryKey(1)
        assertNotNull(expectExist)

        val expectNotExist = todoDao.selectByPrimaryKey(3)
        assertNull(expectNotExist)
    }
}
