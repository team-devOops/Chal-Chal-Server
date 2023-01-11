package com.chalchal.chalchalsever.domain.todo.service;

import com.chalchal.chalchalsever.domain.todo.dto.TodoTopicSaveRequest;
import com.chalchal.chalchalsever.domain.todo.dto.TodoTopicUpdateRequest;
import com.chalchal.chalchalsever.domain.todo.entity.TodoTopic;
import com.chalchal.chalchalsever.domain.todo.repository.TodoTopicRepository;
import com.chalchal.chalchalsever.global.dto.Flag;
import com.chalchal.chalchalsever.global.generate.SvcNo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TodoTopicServiceImpl implements TodoTopicService {
    private final TodoTopicRepository todoTopicRepository;

    @Override
    public TodoTopic createTodoTopic(TodoTopicSaveRequest todoTopicSaveRequest) {
        return todoTopicRepository.save(TodoTopic.builder()
                .svcNo(SvcNo.getSvcNo())
                        .emoji(todoTopicSaveRequest.getEmoji())
                        .bgColor(todoTopicSaveRequest.getBgColor())
                        .useYn(Flag.Y)
                .build());
    }

    @Override
    @Transactional
    public TodoTopic updateTodoTopic(TodoTopicUpdateRequest todoTopicUpdateRequest) {
        TodoTopic todoTopic = this.findTodoTopicBySvcNo(todoTopicUpdateRequest.getSvcNo());

        todoTopic.changeEmoji(todoTopicUpdateRequest.getEmoji());
        todoTopic.changeBgColor(todoTopicUpdateRequest.getBgColor());
        todoTopic.changeUseYn(todoTopicUpdateRequest.getUseYn());

        return todoTopic;
    }

    @Override
    public TodoTopic findTodoTopicBySvcNo(String svcNo) {
        return todoTopicRepository.findBySvcNo(svcNo);
    }

    @Override
    @Transactional
    public TodoTopic deleteTodoTopic(String svcNo) {
        TodoTopic todoTopic = this.findTodoTopicBySvcNo(svcNo);
        todoTopic.changeUseYn(Flag.Y);
        return todoTopic;
    }
}
