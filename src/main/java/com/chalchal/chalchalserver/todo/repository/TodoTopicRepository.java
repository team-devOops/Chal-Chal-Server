package com.chalchal.chalchalserver.todo.repository;

import com.chalchal.chalchalserver.todo.entity.TodoTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoTopicRepository extends JpaRepository<TodoTopic, String> {
    TodoTopic findBySvcNo(String svcNo);
}
