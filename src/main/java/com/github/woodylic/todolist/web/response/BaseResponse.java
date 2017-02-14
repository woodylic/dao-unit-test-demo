package com.github.woodylic.todolist.web.response;

import java.io.Serializable;

/**
 * Created by choli on 2017/2/13.
 */
public class BaseResponse<T> implements Serializable {

    public static final Integer SUCCESSED_CODE = 1;
    public static final Integer FAILED_CODE = 0;

    private Integer code;
    private String message;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BaseResponse(){
        super();
    }
}
