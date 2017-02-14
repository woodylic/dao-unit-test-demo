package com.github.woodylic.todolist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping(path = "/login")
    public void login() {
        
    }

    @RequestMapping(path = "/logout")
    public void logout(){

    }
}
