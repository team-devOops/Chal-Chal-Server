package com.chalchal.chalchalsever.domain.todo.repository;

import com.chalchal.chalchalsever.domain.todo.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoList, String> {
    TodoList findBySvcNo(String svcNo);

    List<TodoList> findByRegId(Long id);
}
