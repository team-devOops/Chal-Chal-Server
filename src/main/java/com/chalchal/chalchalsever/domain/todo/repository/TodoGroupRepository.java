package com.chalchal.chalchalsever.domain.todo.repository;

import com.chalchal.chalchalsever.domain.todo.entity.TodoGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoGroupRepository extends JpaRepository<TodoGroup, String> {
    TodoGroup findBySvcNo(String svcNo);
}
