package com.chalchal.chalchalserver.todo.service;

import com.chalchal.chalchalserver.todo.dto.TodoMstSaveRequest;
import com.chalchal.chalchalserver.todo.dto.TodoMstUpdateRequest;
import com.chalchal.chalchalserver.todo.entity.TodoMst;
import com.chalchal.chalchalserver.todo.repository.TodoMstRepository;
import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.global.generate.SvcNo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.chalchal.chalchalserver.global.exception.BaseException.*;

@Service
@RequiredArgsConstructor
public class TodoMstService {

    private final TodoMstRepository todoMstRepository;

    /**
     * TodoMst 할 일 생성
     * 할 일 내용 save
     *
     * @return TodoMst 저장 된 내용 반환
     */
    public TodoMst createTodoMst(TodoMstSaveRequest todoMstSaveRequest, Long id) {
        return todoMstRepository.save(TodoMst.builder()
                    .svcNo(SvcNo.getSvcNo())
                    .reSvcNo(null)
                    .topicKey(todoMstSaveRequest.getTopicKey())
                    .title(todoMstSaveRequest.getTitle())
                    .memo(todoMstSaveRequest.getMemo())
                    .useYn(Flag.Y)
                    .successYn(Flag.N)
                    .successDate(null)
                .build());
    }

    /**
     * TodoMst 할 일 수정
     * SVC_NO에 해당하는 내역 조회해서 내용 수정
     *
     * @return TodoMst 수정 된 내용 반환
     */
    @Transactional
    public TodoMst updateTodoMst(TodoMstUpdateRequest todoMstUpdateRequest) {
        TodoMst todoMst = this.findTodoMstBySvcNo(todoMstUpdateRequest.getSvcNo());

        todoMst.changeTopicKey(todoMstUpdateRequest.getTopicKey());
        todoMst.changeTitle(todoMstUpdateRequest.getTitle());
        todoMst.changeMemo(todoMstUpdateRequest.getMemo());
        todoMst.changeUseYn(todoMstUpdateRequest.getUseYn());
        todoMst.changeSuccessYn(todoMstUpdateRequest.getSuccessYn());

        return todoMst;
    }

    /**
     * TodoMst 할 일 삭제
     * SVC_NO에 해당하는 할 일 USE_YN을 'N'으로 수정
     *
     * @return TodoMst 삭제 처리 된 내역 반환
     */
    @Transactional
    public TodoMst deleteTodoMst(String svcNo) {
        TodoMst todoMst = this.findTodoMstBySvcNo(svcNo);
        todoMst.changeUseYn(Flag.N);
        return todoMst;
    }

    /**
     * TodoMst 조회
     * SVC_NO, USE_YN이 'Y'인 할 일 내역 단건 조회
     *
     * @return TodoMst 조회 된 내용 출력
     */
    @Transactional(readOnly = true)
    public TodoMst findTodoMstBySvcNo(String svcNo) {
        return todoMstRepository.findBySvcNoAndUseYn(svcNo, Flag.Y)
                .orElseThrow(() -> RESOURCE_NOT_FOUND_EXCEPTION);
    }

    /**
     * TodoMst 조회
     * ID, USE_YN이 'Y'인 할 일 내역 리스트 조회
     *
     * @return List<TodoMst> 조회 된 할 일 내역 리스트 출력
     */
    @Transactional(readOnly = true)
    public List<TodoMst> findTodoMstByRegId(Long id) {
        return todoMstRepository.findByRegIdAndUseYn(id, Flag.Y);
    }

    @Transactional(readOnly = true)
    public int getTodoMstCount(Long id) {
        return todoMstRepository.countByRegId(id);
    }
}
