package com.github.woodylic.todolist.web.handler;

import com.github.woodylic.todolist.web.response.ErrorResponse;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RestExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        ErrorResponse resp = new ErrorResponse();
        //resp.setStatus(response.getStatus());
        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        resp.setCode(10001);
        resp.setMessage("Some error happen.");

        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(null);

        ModelAndView mv = new ModelAndView();
        mv.setView(jsonView);
        return mv;
    }
}
