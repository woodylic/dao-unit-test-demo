package com.github.woodylic.utdemo.web.controller;

import com.github.woodylic.utdemo.entity.SmsTask;
import com.github.woodylic.utdemo.service.SmsTaskService;
import com.github.woodylic.utdemo.web.request.BaseRequest;
import com.github.woodylic.utdemo.web.response.BaseResponse;
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
    public BaseResponse<SmsTask> getSmsTaskById(@PathVariable Long id) {
        SmsTask smsTask = smsTaskService.selectByPrimaryKey(id);
        return new BaseResponse(smsTask);
    }

    @RequestMapping(path="/add", method=RequestMethod.POST)
    @ResponseBody
    public void addSmsTask(@RequestBody BaseRequest<SmsTask> response) {

        System.out.print(response.getRequestData().toString());
        //smsTaskService.insert(smsTask);
    }
}
