package com.chalchal.chalchalserver.todo.service;

import com.chalchal.chalchalserver.todo.dto.TodoListSaveRequest;
import com.chalchal.chalchalserver.todo.dto.TodoListUpdateRequest;
import com.chalchal.chalchalserver.todo.entity.TodoList;

import java.util.List;

public interface TodoService {
    TodoList createTodoList(TodoListSaveRequest todoListSaveRequest);

    TodoList updateTodoList(TodoListUpdateRequest todoListUpdateRequest);

    TodoList deleteTodoList(String svcNo);

    TodoList findTodoListBySvcNo(String svcNo);

    List<TodoList> findTodoListByRegId(Long id);
}
