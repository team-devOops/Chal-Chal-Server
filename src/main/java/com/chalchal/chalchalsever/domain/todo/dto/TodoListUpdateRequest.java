package com.chalchal.chalchalsever.domain.todo.dto;

import com.chalchal.chalchalsever.global.dto.Flag;
import lombok.Getter;

/**
 * 할 일 수정시 필요한 객체
 */
@Getter
public class TodoListUpdateRequest {
    private String svcNo;
    private String topicKey;
    private String title;
    private String memo;
    private Flag useYn;
    private Flag successYn;
}
