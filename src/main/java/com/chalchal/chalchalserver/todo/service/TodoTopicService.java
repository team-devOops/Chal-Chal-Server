package com.chalchal.chalchalserver.todo.service;

import com.chalchal.chalchalserver.todo.dto.TodoTopicSaveRequest;
import com.chalchal.chalchalserver.todo.dto.TodoTopicUpdateRequest;
import com.chalchal.chalchalserver.todo.entity.TodoTopic;
import com.chalchal.chalchalserver.todo.repository.TodoTopicRepository;
import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.global.generate.SvcNo;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

import static com.chalchal.chalchalserver.global.exception.BaseException.*;

@Service
@RequiredArgsConstructor
public class TodoTopicService {
    private final TodoTopicRepository todoTopicRepository;

    /**
     * TodoTopic 할 일 주제 생성
     * 할 일 주제 save
     *
     * @return TodoTopic 저장 된 내용 반환
     */
    @Retryable(value = SQLException.class)
    public TodoTopic createTodoTopic(TodoTopicSaveRequest todoTopicSaveRequest) {
        return todoTopicRepository.save(TodoTopic.builder()
                    .svcNo(SvcNo.getSvcNo())
                    .emoji(todoTopicSaveRequest.getEmoji())
                    .bgColor(todoTopicSaveRequest.getBgColor())
                    .useYn(Flag.Y)
                .build());
    }

    /**
     * TodoTopic 할 일 주제 수정
     * 할 일 주제 update
     *
     * @return TodoTopic 수정 된 내용 반환
     */
    @Transactional
    @Retryable(value = SQLException.class)
    public TodoTopic updateTodoTopic(TodoTopicUpdateRequest todoTopicUpdateRequest) {
        TodoTopic todoTopic = findTodoTopicBySvcNo(todoTopicUpdateRequest.getSvcNo());

        todoTopic.changeEmoji(todoTopicUpdateRequest.getEmoji());
        todoTopic.changeBgColor(todoTopicUpdateRequest.getBgColor());
        todoTopic.changeUseYn(todoTopicUpdateRequest.getUseYn());

        return todoTopic;
    }

    /**
     * TodoTopic 할 일 주제 조회
     * SVC_NO를 기준으로 할 일 주제 조회
     *
     * @return TodoTopic SVC_NO에 해당하는 단건 반환
     */
    @Transactional(readOnly = true)
    public TodoTopic findTodoTopicBySvcNo(String svcNo) {
        return todoTopicRepository.findBySvcNo(svcNo)
                .orElseThrow(() -> RESOURCE_NOT_FOUND_EXCEPTION);
    }

    /**
     * TodoTopic 할 일 주제 삭제
     * SVC_NO를 기준으로 할 일 주제 USE_YN 'N'으로 수정
     *
     * @return TodoTopic 수정 된 할 일 주제 반환
     */
    @Transactional
    @Retryable(value = SQLException.class)
    public TodoTopic deleteTodoTopic(String svcNo) {
        TodoTopic todoTopic = this.findTodoTopicBySvcNo(svcNo);
        todoTopic.changeUseYn(Flag.N);

        return todoTopic;
    }
}
