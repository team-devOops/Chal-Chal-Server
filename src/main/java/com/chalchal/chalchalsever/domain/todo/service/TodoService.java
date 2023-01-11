package com.chalchal.chalchalsever.domain.todo.service;

import com.chalchal.chalchalsever.domain.todo.dto.TodoListSaveRequest;
import com.chalchal.chalchalsever.domain.todo.dto.TodoListUpdateRequest;
import com.chalchal.chalchalsever.domain.todo.entity.TodoList;

import java.util.List;

public interface TodoService {
    TodoList createTodoList(TodoListSaveRequest todoListSaveRequest);

    TodoList updateTodoList(TodoListUpdateRequest todoListUpdateRequest);

    TodoList deleteTodoList(String svcNo);

    TodoList findTodoListBySvcNo(String svcNo);

    List<TodoList> findTodoListByRegId(Long id);
}
