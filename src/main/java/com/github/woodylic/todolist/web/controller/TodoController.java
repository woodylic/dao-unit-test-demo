package com.github.woodylic.todolist.web.controller;

import com.github.woodylic.todolist.entity.TodoItem;
import com.github.woodylic.todolist.service.TodoService;
import com.github.woodylic.todolist.web.request.BaseRequest;
import com.github.woodylic.todolist.web.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @RequestMapping(path="/add", method=RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody BaseRequest<TodoItem> reqBody) {
        BaseResponse resp = new BaseResponse();
        try {
            TodoItem todoItem = reqBody.getData();
            todoService.insert(todoItem);

            resp.setCode(BaseResponse.SUCCESSED_CODE);
        } catch (Exception e) {
            resp.setCode(BaseResponse.FAILED_CODE);
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

    @RequestMapping(path="/update", method=RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody BaseRequest<TodoItem> reqBody) {
        BaseResponse resp = new BaseResponse();
        try {
            TodoItem todoItem = reqBody.getData();
            todoService.update(todoItem);

            resp.setCode(BaseResponse.SUCCESSED_CODE);
        } catch (Exception e) {
            resp.setCode(BaseResponse.FAILED_CODE);
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

    @RequestMapping(path="/{id}", method=RequestMethod.GET)
    @ResponseBody
    public BaseResponse<TodoItem> findById(@PathVariable Long id) {
        BaseResponse resp = new BaseResponse();
        try {
            TodoItem todoItem = todoService.selectByPrimaryKey(id);
            resp.setData(todoItem);

            resp.setCode(BaseResponse.SUCCESSED_CODE);
        } catch (Exception e) {
            resp.setCode(BaseResponse.FAILED_CODE);
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

    @RequestMapping(path="/list", method=RequestMethod.GET)
    @ResponseBody
    public BaseResponse<TodoItem> findAll() {
        BaseResponse resp = new BaseResponse();
        try {
            List<TodoItem> todoItem = todoService.selectAll();
            resp.setData(todoItem);

            resp.setCode(BaseResponse.SUCCESSED_CODE);
        } catch (Exception e) {
            resp.setCode(BaseResponse.FAILED_CODE);
            resp.setMessage(e.getMessage());
        }
        return resp;
    }
}
