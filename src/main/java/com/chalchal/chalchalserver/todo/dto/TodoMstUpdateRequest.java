package com.chalchal.chalchalserver.todo.dto;

import com.chalchal.chalchalserver.global.dto.Flag;
import lombok.Getter;

/**
 * 할 일 수정시 필요한 객체
 */
@Getter
public class TodoMstUpdateRequest {
    private String svcNo;
    private String topicKey;
    private String title;
    private String memo;
    private Flag useYn;
    private Flag successYn;
}
