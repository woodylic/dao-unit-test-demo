package com.github.woodylic.todolist.service;

import com.github.woodylic.todolist.entity.TodoItem;
import com.github.woodylic.todolist.exception.TodoException;

import java.util.List;

public interface TodoService {

	void insert(TodoItem todoItem) throws TodoException;

    void update(TodoItem todoItem) throws TodoException;

    TodoItem selectByPrimaryKey(Long id) throws TodoException;

    List<TodoItem> selectAll();
}
