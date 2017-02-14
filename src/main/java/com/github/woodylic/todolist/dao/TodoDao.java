package com.github.woodylic.todolist.dao;

import com.github.woodylic.todolist.entity.TodoItem;

import java.util.List;

public interface TodoDao {

    int insert(TodoItem todoItem);

    int update(TodoItem todoItem);

    TodoItem selectByPrimaryKey(Long id);

    List<TodoItem> selectAll();
}
