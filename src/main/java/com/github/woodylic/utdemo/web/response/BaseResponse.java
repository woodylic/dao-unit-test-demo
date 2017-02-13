package com.github.woodylic.utdemo.web.response;

import java.io.Serializable;

/**
 * Created by choli on 2017/2/13.
 */
public class BaseResponse<T> implements Serializable {

    private static final Integer SUCCESSED_CODE = 1;
    private static final Integer FAILED_CODE = 0;

    private Integer code;
    private String message;
    private T responseData;

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

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

    public BaseResponse(){
        super();
    }

    public BaseResponse(T responseData) {
        this(responseData, null);
    }

    public BaseResponse(T responseData, String message) {
        super();
        this.code = SUCCESSED_CODE;
        this.responseData = responseData;
        this.message = message;
    }
}
