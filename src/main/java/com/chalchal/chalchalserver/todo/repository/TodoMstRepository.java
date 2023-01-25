package com.chalchal.chalchalserver.todo.repository;

import com.chalchal.chalchalserver.todo.entity.TodoMst;
import com.chalchal.chalchalserver.global.dto.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoMstRepository extends JpaRepository<TodoMst, String> {
    Optional<TodoMst> findBySvcNoAndUseYn(String svcNo, Flag useYn);

    List<TodoMst> findByRegIdAndUseYn(Long id, Flag useYn);

    int countByRegId(Long id);
}
