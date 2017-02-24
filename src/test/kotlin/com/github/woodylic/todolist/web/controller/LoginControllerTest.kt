package com.github.woodylic.todolist.web.controller

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpSession
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@WebAppConfiguration
class LoginControllerTest
{
    private lateinit var mockHttpSession: MockHttpSession
    private lateinit var mockMvc: MockMvc

    @Before
    fun setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(LoginController()).build()
        mockHttpSession = MockHttpSession()
    }

    @Test
    fun testGetLoginStatus()
    {
        mockHttpSession.setAttribute("user", "mockUser")
        mockMvc.perform(get("/loginStatus").session(mockHttpSession))
                .andExpect(status().isOk)
    }

    @Test
    fun testLogin()
    {
        val postContent = "{\"data\":{\"userName\":\"mockUser\",\"password\":\"myPassword\"}}"
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(postContent).session(mockHttpSession))
                .andExpect(status().isOk)
                .andDo { r -> assertEquals("mockUser", mockHttpSession.getAttribute("user")) }
    }

    @Test
    fun testLogout()
    {
        mockHttpSession.setAttribute("user", "mockUser")
        mockMvc.perform(post("/logout").session(mockHttpSession))
                .andExpect(status().isOk)
                .andDo { r -> assertNull(mockHttpSession.getAttribute("user")) }
    }
}