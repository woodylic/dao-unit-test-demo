package com.github.woodylic.utdemo.web.request;

import com.github.woodylic.utdemo.web.response.BaseResponse;

import java.io.Serializable;

public class BaseRequest<T> implements Serializable {

    private T requestData;

    public T getRequestData() {
        return requestData;
    }

    public void setRequestData(T requestData) {
        this.requestData = requestData;
    }

    public BaseRequest(){
        super();
    }

    public BaseRequest(T requestData) {
        super();
        this.requestData = requestData;
    }
}
