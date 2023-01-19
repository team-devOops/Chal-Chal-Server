package com.chalchal.chalchalserver.todo.service;

import com.chalchal.chalchalserver.todo.dto.TodoTopicSaveRequest;
import com.chalchal.chalchalserver.todo.dto.TodoTopicUpdateRequest;
import com.chalchal.chalchalserver.todo.entity.TodoTopic;

public interface TodoTopicService {
    TodoTopic createTodoTopic(TodoTopicSaveRequest todoTopicSaveRequest);

    TodoTopic updateTodoTopic(TodoTopicUpdateRequest todoTopicUpdateRequest);

    TodoTopic findTodoTopicBySvcNo(String svcNo);

    TodoTopic deleteTodoTopic(String svcNo);
}
