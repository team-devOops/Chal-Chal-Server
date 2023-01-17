package com.chalchal.chalchalserver.domain.todo.repository;

import com.chalchal.chalchalserver.domain.todo.entity.TodoList;
import com.chalchal.chalchalserver.global.dto.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoList, String> {
    TodoList findBySvcNoAndUseYn(String svcNo, Flag useYn);

    List<TodoList> findByRegIdAndUseYn(Long id, Flag useYn);
}
