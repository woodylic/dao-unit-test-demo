package com.github.woodylic.utdemo.web.controller;

import com.github.woodylic.utdemo.entity.SmsTask;
import com.github.woodylic.utdemo.service.SmsTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/smsTask")
public class SmsTaskController {

    @Autowired
    private SmsTaskService smsTaskService;

    @RequestMapping(path="/{id}", method=RequestMethod.GET)
    @ResponseBody
    public SmsTask getSmsTaskById(@PathVariable Long id) {
        return smsTaskService.selectByPrimaryKey(id);
    }

    @RequestMapping(path="/add", method=RequestMethod.POST)
    public void addSmsTask(@RequestBody SmsTask smsTask) {
        smsTaskService.insert(smsTask);
    }
}
