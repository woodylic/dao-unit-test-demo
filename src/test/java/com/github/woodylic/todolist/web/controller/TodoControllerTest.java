package com.github.woodylic.todolist.web.controller;

import com.github.woodylic.todolist.entity.TodoItem;
import com.github.woodylic.todolist.exception.TodoException;
import com.github.woodylic.todolist.service.TodoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration    //在跑单元测试的时候真实的启一个web服务，然后开始调用Controller的Rest API，待单元测试跑完之后再将web服务停掉
public class TodoControllerTest {

    private MockMvc mockMvc;

    @Mock
    TodoService todoServiceMock;

    @InjectMocks
    TodoController todoController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();
    }

    @Test
    public void testAdd() throws Exception {

        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            TodoItem actual = (TodoItem) args[0];

            //确认controller把json转换为了正确的todoItem
            assertNull(actual.getId());
            assertEquals("To do something", actual.getTitle());
            assertEquals("Details of a to do item.", actual.getDescription());
            assertNull(actual.getStatus());

            return null;
        }).when(todoServiceMock).insert(any());

        String postContent = "{\"data\":{\"title\":\"To do something\",\"description\":\"Details of a to do item.\"}}";;
        String expectedResponse = "{\"code\":1,\"message\":null,\"data\":null}";

        mockMvc.perform(post("/todo/add").contentType(MediaType.APPLICATION_JSON_UTF8).content(postContent))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse))
                .andDo(print());

        verify(todoServiceMock).insert(any());
    }

    @Test
    public void testAddFailed() throws Exception {

        doThrow(new TodoException("Some error happen.")).when(todoServiceMock).insert(any());

        String postContent = "{\"data\":{\"title\":\"To do something\",\"description\":\"Details of a to do item.\"}}";;
        String expectedResponse = "{\"code\":0,\"message\":\"Some error happen.\",\"data\":null}";

        mockMvc.perform(post("/todo/add").contentType(MediaType.APPLICATION_JSON_UTF8).content(postContent))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse))
                .andDo(print());

        verify(todoServiceMock).insert(any());
    }

    @Test
    public void testSelectById() throws Exception {

        String expectedResponse = "{\"code\":1,\"message\":null,\"data\":{\"id\":1,\"title\":\"To do something\",\"description\":\"Details of a to do item.\",\"status\":0}}";

        when(todoServiceMock.selectByPrimaryKey(1L)).thenReturn(getTodoItemForTest());

        mockMvc.perform(get("/todo/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse))
                .andDo(print());
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