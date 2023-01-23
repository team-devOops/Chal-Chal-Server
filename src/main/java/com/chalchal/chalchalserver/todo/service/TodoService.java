package com.chalchal.chalchalserver.todo.service;

import com.chalchal.chalchalserver.todo.dto.TodoListSaveRequest;
import com.chalchal.chalchalserver.todo.dto.TodoListUpdateRequest;
import com.chalchal.chalchalserver.todo.entity.TodoList;
import com.chalchal.chalchalserver.todo.repository.TodoRepository;
import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.global.generate.SvcNo;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

import static com.chalchal.chalchalserver.global.exception.BaseException.*;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    /**
     * TodoList 할 일 생성
     * 할 일 내용 save
     *
     * @return TodoList 저장 된 내용 반환
     */
    @Retryable(value = SQLException.class)
    public TodoList createTodoList(TodoListSaveRequest todoListSaveRequest) {
        //TODO: orderSeq 처리 필요
        return todoRepository.save(TodoList.builder()
                    .svcNo(SvcNo.getSvcNo())
                    .reSvcNo(null)
                    .topicKey(todoListSaveRequest.getTopicKey())
                    .orderSeq(1)
                    .title(todoListSaveRequest.getTitle())
                    .memo(todoListSaveRequest.getMemo())
                    .useYn(Flag.Y)
                    .successYn(Flag.N)
                    .successDate(null)
                .build());
    }

    /**
     * TodoList 할 일 수정
     * SVC_NO에 해당하는 내역 조회해서 내용 수정
     *
     * @return TodoList 수정 된 내용 반환
     */
    @Transactional
    @Retryable(value = SQLException.class)
    public TodoList updateTodoList(TodoListUpdateRequest todoListUpdateRequest) {
        TodoList todoList = this.findTodoListBySvcNo(todoListUpdateRequest.getSvcNo());

        todoList.changeTopicKey(todoListUpdateRequest.getTopicKey());
        todoList.changeTitle(todoListUpdateRequest.getTitle());
        todoList.changeMemo(todoListUpdateRequest.getMemo());
        todoList.changeUseYn(todoListUpdateRequest.getUseYn());
        todoList.changeSuccessYn(todoListUpdateRequest.getSuccessYn());

        return todoList;
    }

    /**
     * TodoList 할 일 삭제
     * SVC_NO에 해당하는 할 일 USE_YN을 'N'으로 수정
     *
     * @return TodoList 삭제 처리 된 내역 반환
     */
    @Transactional
    @Retryable(value = SQLException.class)
    public TodoList deleteTodoList(String svcNo) {
        TodoList todoList = this.findTodoListBySvcNo(svcNo);
        todoList.changeUseYn(Flag.N);
        return todoList;
    }

    /**
     * TodoList 조회
     * SVC_NO, USE_YN이 'Y'인 할 일 내역 단건 조회
     *
     * @return TodoList 조회 된 내용 출력
     */
    @Transactional(readOnly = true)
    public TodoList findTodoListBySvcNo(String svcNo) {
        return todoRepository.findBySvcNoAndUseYn(svcNo, Flag.Y)
                .orElseThrow(() -> RESOURCE_NOT_FOUND_EXCEPTION);
    }

    /**
     * TodoList 조회
     * ID, USE_YN이 'Y'인 할 일 내역 리스트 조회
     *
     * @return List<TodoList> 조회 된 할 일 내역 리스트 출력
     */
    @Transactional(readOnly = true)
    public List<TodoList> findTodoListByRegId(Long id) {
        return todoRepository.findByRegIdAndUseYn(id, Flag.Y);
    }
}
