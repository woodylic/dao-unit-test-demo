package com.github.woodylic.todolist.web.request;

import java.io.Serializable;

public class BaseRequest<T> implements Serializable {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BaseRequest(){
        super();
    }

    public BaseRequest(T data) {
        super();
        this.data = data;
    }
}
