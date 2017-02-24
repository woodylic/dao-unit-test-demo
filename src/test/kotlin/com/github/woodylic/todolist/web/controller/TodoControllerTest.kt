package com.github.woodylic.todolist.web.controller

import com.github.woodylic.todolist.entity.TodoItem
import com.github.woodylic.todolist.exception.TodoException
import com.github.woodylic.todolist.service.TodoService
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@WebAppConfiguration
class TodoControllerTest
{
    private lateinit var mockMvc: MockMvc

    @Mock
    private lateinit var todoServiceMock: TodoService

    @InjectMocks
    private lateinit var todoController: TodoController

    @Before
    fun setUp()
    {
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders.standaloneSetup(todoController).build()
    }

    @Test
    fun testAdd()
    {
        doAnswer { invocation ->
            val args = invocation.arguments
            val actual = args[0] as TodoItem

            //确认controller把json转换为了正确的todoItem
            assertNull(actual.id)
            assertEquals("To do something", actual.title)
            assertEquals("Details of a to do item.", actual.description)
            assertNull(actual.status)

            null
        }.whenever(todoServiceMock).insert(any())

        val postContent = "{\"data\":{\"title\":\"To do something\",\"description\":\"Details of a to do item.\"}}"
        val expectedResponse = "{\"code\":1,\"message\":null,\"data\":null}"

        mockMvc.perform(post("/todo/add").contentType(MediaType.APPLICATION_JSON_UTF8).content(postContent))
                .andExpect(status().isOk)
                .andExpect(content().string(expectedResponse))

        verify(todoServiceMock).insert(any())
    }

    @Test
    fun testAddFailed()
    {
        doThrow(TodoException("Some error happen.")).whenever(todoServiceMock).insert(any())

        val postContent = "{\"data\":{\"title\":\"To do something\",\"description\":\"Details of a to do item.\"}}"
        val expectedResponse = "{\"code\":0,\"message\":\"Some error happen.\",\"data\":null}"

        mockMvc.perform(post("/todo/add").contentType(MediaType.APPLICATION_JSON_UTF8).content(postContent))
                .andExpect(status().isOk)
                .andExpect(content().string(expectedResponse))

        verify(todoServiceMock).insert(any())
    }

    @Test
    fun testSelectById()
    {
        val expectedResponse = "{\"code\":1,\"message\":null,\"data\":{\"id\":1,\"title\":\"To do something\",\"description\":\"Details of a to do item.\",\"status\":0}}"

        whenever(todoServiceMock.selectByPrimaryKey(1)).thenReturn(mockTodoItem())

        mockMvc.perform(get("/todo/1"))
                .andExpect(status().isOk)
                .andExpect(content().string(expectedResponse))
    }

    private fun mockTodoItem() : TodoItem {
        val todoItem = TodoItem()
        todoItem.id = 1
        todoItem.title = "To do something"
        todoItem.description = "Details of a to do item."
        todoItem.status = 0

        return todoItem
    }
}
