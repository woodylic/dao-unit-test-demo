package com.github.woodylic.todolist.service.impl;

import com.github.woodylic.todolist.dao.TodoDao;
import com.github.woodylic.todolist.entity.TodoItem;
import com.github.woodylic.todolist.exception.TodoException;
import com.github.woodylic.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.List;

@Service("smsTaskService")
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoDao todoDao;

	public void insert(TodoItem todoItem) throws TodoException {
        todoItem.setStatus(0);
        int impactRows = todoDao.insert(todoItem);

        if(impactRows <= 0)
            throw new TodoException("Failed to insert todo item.");
	}

    public void update(TodoItem todoItem) throws TodoException {

        //添加简单的validation，作为service层unit test的测试目标。
        if(todoItem.getId() == null)
            throw new TodoException("Cannot update a new todo item.");

        int impactRows = todoDao.update(todoItem);
        if(impactRows <= 0)
            throw new TodoException("Failed to update todo item.");
    }

	public TodoItem selectByPrimaryKey(Long id) throws TodoException {

        if(id <= 0)
            throw new TodoException("ID should be larger than 0.");

	    return todoDao.selectByPrimaryKey(id);
	}

    public List<TodoItem> selectAll() {
        return todoDao.selectAll();
    }
}
