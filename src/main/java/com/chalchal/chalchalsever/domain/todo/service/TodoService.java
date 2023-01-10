package com.chalchal.chalchalsever.domain.todo.service;

import com.chalchal.chalchalsever.domain.todo.dto.TodoGroupSaveRequest;
import com.chalchal.chalchalsever.domain.todo.dto.TodoGroupUpdateRequest;
import com.chalchal.chalchalsever.domain.todo.dto.TodoListSaveRequest;
import com.chalchal.chalchalsever.domain.todo.dto.TodoListUpdateRequest;
import com.chalchal.chalchalsever.domain.todo.entity.TodoGroup;
import com.chalchal.chalchalsever.domain.todo.entity.TodoList;

import java.util.List;

public interface TodoService {
    TodoList createTodoList(TodoListSaveRequest todoListSaveRequest);

    TodoList updateTodoList(TodoListUpdateRequest todoListUpdateRequest);

    TodoList deleteTodoList(String svcNo);

    TodoList findTodoListBySvcNo(String svcNo);

    List<TodoList> findTodoListByRegId(Long id);

    TodoGroup createTodoGroup(TodoGroupSaveRequest todoGroupSaveRequest);

    TodoGroup updateTodoGroup(TodoGroupUpdateRequest todoGroupUpdateRequest);

    TodoGroup findTodoGroupBySvcNo(String svcNo);

    TodoGroup deleteTodoGroup(String svcNo);
}
