package com.chalchal.chalchalserver.todo.repository;

import com.chalchal.chalchalserver.todo.entity.TodoTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoTopicRepository extends JpaRepository<TodoTopic, String> {
    Optional<TodoTopic> findBySvcNo(String svcNo);
}
