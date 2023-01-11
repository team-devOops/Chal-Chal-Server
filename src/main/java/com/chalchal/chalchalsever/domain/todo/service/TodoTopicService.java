package com.chalchal.chalchalsever.domain.todo.service;

import com.chalchal.chalchalsever.domain.todo.dto.TodoTopicSaveRequest;
import com.chalchal.chalchalsever.domain.todo.dto.TodoTopicUpdateRequest;
import com.chalchal.chalchalsever.domain.todo.entity.TodoTopic;

public interface TodoTopicService {
    TodoTopic createTodoTopic(TodoTopicSaveRequest todoTopicSaveRequest);

    TodoTopic updateTodoTopic(TodoTopicUpdateRequest todoTopicUpdateRequest);

    TodoTopic findTodoTopicBySvcNo(String svcNo);

    TodoTopic deleteTodoTopic(String svcNo);
}
