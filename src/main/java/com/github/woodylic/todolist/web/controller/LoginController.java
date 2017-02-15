package com.github.woodylic.todolist.web.controller;

import com.github.woodylic.todolist.dto.LoginDTO;
import com.github.woodylic.todolist.exception.TodoException;
import com.github.woodylic.todolist.web.request.BaseRequest;
import com.github.woodylic.todolist.web.response.BaseResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path="/")
public class LoginController {

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse login(@RequestBody BaseRequest<LoginDTO> reqBody, HttpSession session) {
        BaseResponse resp = new BaseResponse();
        try {
            session.setAttribute("user", reqBody.getData().getUserName());

            resp.setCode(BaseResponse.SUCCESSED_CODE);
            resp.setMessage("登录成功。");
        }
        catch (Exception e){
            resp.setCode(BaseResponse.FAILED_CODE);
            resp.setMessage("登录失败。原因：" + e.getMessage());
        }
        return resp;
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse logout(HttpSession session) {
        BaseResponse resp = new BaseResponse();
        try {
            session.setAttribute("user", null);

            resp.setCode(BaseResponse.SUCCESSED_CODE);
            resp.setMessage("注销成功。");
        }
        catch (Exception e){
            resp.setCode(BaseResponse.FAILED_CODE);
            resp.setMessage("注销失败。原因：" + e.getMessage());
        }
        return resp;
    }

    @RequestMapping(path = "/loginStatus", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<String> getCurrentUser(HttpSession session){
        BaseResponse resp = new BaseResponse();
        try {
            String currentUser = (String)session.getAttribute("user");
            if(currentUser == null)
                throw new TodoException("用户未登录。");

            resp.setCode(BaseResponse.SUCCESSED_CODE);
            resp.setData(currentUser);
        }
        catch (Exception e){
            resp.setCode(BaseResponse.FAILED_CODE);
            resp.setMessage("获取当前用户失败。原因：" + e.getMessage());
        }
        return resp;
    }
}
