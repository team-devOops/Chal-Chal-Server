package com.chalchal.chalchalserver.domain.todo.service;

import com.chalchal.chalchalserver.domain.todo.dto.TodoListSaveRequest;
import com.chalchal.chalchalserver.domain.todo.dto.TodoListUpdateRequest;
import com.chalchal.chalchalserver.domain.todo.entity.TodoList;

import java.util.List;

public interface TodoService {
    TodoList createTodoList(TodoListSaveRequest todoListSaveRequest);

    TodoList updateTodoList(TodoListUpdateRequest todoListUpdateRequest);

    TodoList deleteTodoList(String svcNo);

    TodoList findTodoListBySvcNo(String svcNo);

    List<TodoList> findTodoListByRegId(Long id);
}
