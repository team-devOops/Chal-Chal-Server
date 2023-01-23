package com.chalchal.chalchalserver.todo.repository;

import com.chalchal.chalchalserver.todo.entity.TodoList;
import com.chalchal.chalchalserver.global.dto.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<TodoList, String> {
    Optional<TodoList> findBySvcNoAndUseYn(String svcNo, Flag useYn);

    List<TodoList> findByRegIdAndUseYn(Long id, Flag useYn);
}
