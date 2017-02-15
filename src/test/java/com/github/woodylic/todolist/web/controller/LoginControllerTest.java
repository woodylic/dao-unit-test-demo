package com.github.woodylic.todolist.web.controller;

import com.github.woodylic.todolist.service.TodoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
public class LoginControllerTest {

    private MockHttpSession mockHttpSession;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new LoginController()).build();
        mockHttpSession = new MockHttpSession();
    }

    @Test
    public void testGetLoginStatus() throws Exception {

        mockHttpSession.setAttribute("user", "mockUser");

        mockMvc.perform(get("/loginStatus").session(mockHttpSession))
                .andExpect(status().isOk());
    }

    @Test
    public void testLogin() throws Exception {
        String postContent = "{\"data\":{\"userName\":\"mockUser\",\"password\":\"myPassword\"}}";

        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON_UTF8).content(postContent).session(mockHttpSession))
                .andExpect(status().isOk())
                .andDo(r -> assertEquals("mockUser", mockHttpSession.getAttribute("user")));
    }

    @Test
    public void testLogout() throws Exception {
        mockHttpSession.setAttribute("user", "mockUser");

        mockMvc.perform(post("/logout").session(mockHttpSession))
                .andExpect(status().isOk())
                .andDo(r -> assertNull(mockHttpSession.getAttribute("user")));
    }
}