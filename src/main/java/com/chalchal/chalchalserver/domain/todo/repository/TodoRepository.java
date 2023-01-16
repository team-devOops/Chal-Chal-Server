package com.chalchal.chalchalserver.domain.todo.repository;

import com.chalchal.chalchalserver.domain.todo.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoList, String> {
    TodoList findBySvcNo(String svcNo);

    List<TodoList> findByRegId(Long id);
}
