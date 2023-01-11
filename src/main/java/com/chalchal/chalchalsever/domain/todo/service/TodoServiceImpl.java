package com.chalchal.chalchalsever.domain.todo.service;

import com.chalchal.chalchalsever.domain.todo.dto.TodoGroupSaveRequest;
import com.chalchal.chalchalsever.domain.todo.dto.TodoGroupUpdateRequest;
import com.chalchal.chalchalsever.domain.todo.dto.TodoListSaveRequest;
import com.chalchal.chalchalsever.domain.todo.dto.TodoListUpdateRequest;
import com.chalchal.chalchalsever.domain.todo.entity.TodoGroup;
import com.chalchal.chalchalsever.domain.todo.entity.TodoList;
import com.chalchal.chalchalsever.domain.todo.repository.TodoGroupRepository;
import com.chalchal.chalchalsever.domain.todo.repository.TodoRepository;
import com.chalchal.chalchalsever.global.dto.Flag;
import com.chalchal.chalchalsever.global.generate.SvcNo;
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
    private final TodoGroupRepository todoGroupRepository;

    @Override
    public TodoList createTodoList(TodoListSaveRequest todoListSaveRequest) {
        return todoRepository.save(TodoList.builder()
                        .svcNo(SvcNo.getSvcNo())
                        .reSvcNo(null)
                        .groupKey(todoListSaveRequest.getGroupKey())
                        .orderSeq(1)
                        .title(todoListSaveRequest.getTitle())
                        .memo(todoListSaveRequest.getMemo())
                        .useYn(Flag.Y)
                        .successYn(Flag.N)
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
        todoList.changeUseYn(Flag.Y);
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

    @Override
    public TodoGroup createTodoGroup(TodoGroupSaveRequest todoGroupSaveRequest) {
        return todoGroupRepository.save(TodoGroup.builder()
                .svcNo(SvcNo.getSvcNo())
                        .emoji(todoGroupSaveRequest.getEmoji())
                        .bgColor(todoGroupSaveRequest.getBgColor())
                        .useYn(Flag.Y)
                .build());
    }

    @Override
    @Transactional
    public TodoGroup updateTodoGroup(TodoGroupUpdateRequest todoGroupUpdateRequest) {
        TodoGroup todoGroup = this.findTodoGroupBySvcNo(todoGroupUpdateRequest.getSvcNo());

        todoGroup.changeEmoji(todoGroupUpdateRequest.getEmoji());
        todoGroup.changeBgColor(todoGroupUpdateRequest.getBgColor());
        todoGroup.changeUseYn(todoGroupUpdateRequest.getUseYn());

        return todoGroup;
    }

    @Override
    public TodoGroup findTodoGroupBySvcNo(String svcNo) {
        return todoGroupRepository.findBySvcNo(svcNo);
    }

    @Override
    @Transactional
    public TodoGroup deleteTodoGroup(String svcNo) {
        TodoGroup todoGroup = this.findTodoGroupBySvcNo(svcNo);
        todoGroup.changeUseYn(Flag.Y);
        return todoGroup;
    }
}
