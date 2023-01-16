package com.chalchal.chalchalserver.domain.todo.service;

import com.chalchal.chalchalserver.domain.todo.dto.TodoTopicSaveRequest;
import com.chalchal.chalchalserver.domain.todo.dto.TodoTopicUpdateRequest;
import com.chalchal.chalchalserver.domain.todo.entity.TodoTopic;

public interface TodoTopicService {
    TodoTopic createTodoTopic(TodoTopicSaveRequest todoTopicSaveRequest);

    TodoTopic updateTodoTopic(TodoTopicUpdateRequest todoTopicUpdateRequest);

    TodoTopic findTodoTopicBySvcNo(String svcNo);

    TodoTopic deleteTodoTopic(String svcNo);
}
