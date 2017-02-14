package com.github.woodylic.todolist.exception;

/**
 * Created by choli on 2017/2/14.
 */
public class TodoException extends Exception {

    public TodoException(){

    }

    public TodoException(String message) {
        super(message);
    }

    public TodoException(String message, Throwable cause) {
        super(message, cause);
    }
}
