package com.github.woodylic.todolist.exception;

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
