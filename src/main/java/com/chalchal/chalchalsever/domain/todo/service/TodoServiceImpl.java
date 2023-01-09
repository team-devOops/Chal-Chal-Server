package com.chalchal.chalchalsever.domain.todo.service;

import com.chalchal.chalchalsever.domain.todo.dto.TodoListSaveRequest;
import com.chalchal.chalchalsever.domain.todo.dto.TodoListUpdateRequest;
import com.chalchal.chalchalsever.domain.todo.entity.TodoList;
import com.chalchal.chalchalsever.domain.todo.repository.TodoRepository;
import com.chalchal.chalchalsever.global.generate.SvcNo;
import com.chalchal.chalchalsever.global.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public TodoList createTodoList(TodoListSaveRequest todoListSaveRequest) {
        return todoRepository.save(TodoList.builder()
                        .svcNo(SvcNo.getSvcNo())
                        .reSvcNo(null)
                        .groupKey(todoListSaveRequest.getGroupKey())
                        .orderSeq(1)
                        .title(todoListSaveRequest.getTitle())
                        .memo(todoListSaveRequest.getMemo())
                        .useYn("Y")
                        .successYn("N")
                        .successDate(null)
                .build());
    }

    @Override
    @Transactional
    public TodoList updateTodoList(TodoListUpdateRequest todoListUpdateRequest) {
        TodoList todoList = this.findTodoListBySvcNo(todoListUpdateRequest.getSvcNo());

        todoList.changeGroupKey(todoListUpdateRequest.getGroupKey());
        todoList.changeTitle(todoListUpdateRequest.getTitle());
        todoList.changeMemo(todoListUpdateRequest.getMemo());
        todoList.changeUseYn(todoListUpdateRequest.getUseYn());
        todoList.changeSuccessYn(todoListUpdateRequest.getSuccessYn());

        return todoList;
    }

    @Override
    @Transactional
    public TodoList deleteTodoList(String svcNo) {
        TodoList todoList = this.findTodoListBySvcNo(svcNo);
        todoList.changeUseYn("Y");
        return todoList;
    }

    @Override
    public TodoList findTodoListBySvcNo(String svcNo) {
        return Optional.ofNullable(todoRepository.findBySvcNo(svcNo))
                .orElseThrow(() -> new NoSuchElementException("검색된 결과가 없습니다."));
    }

    @Override
    public List<TodoList> findTodoListByRegId(Long id) {
        return Optional.ofNullable(todoRepository.findByRegId(id))
                .orElseThrow(() -> new NoSuchElementException("등록된 일정 없음"));
    }

}
