package com.chalchal.chalchalserver.todo.dto;

import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.todo.entity.TodoMst;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TodoMstResponse {
    private String svcNo;
    private String topicKey;
    private String title;
    private String memo;
    private Flag useYn;
    private Flag successYn;


    @Builder
    public TodoMstResponse(String svcNo, String topicKey, String title, String memo, Flag useYn, Flag successYn) {
        this.svcNo = svcNo;
        this.topicKey = topicKey;
        this.title = title;
        this.memo = memo;
        this.useYn = useYn;
        this.successYn = successYn;
    }

    public static TodoMstResponse from(TodoMst todoMst) {
        return TodoMstResponse.builder()
                    .svcNo(todoMst.getSvcNo())
                    .topicKey(todoMst.getTopicKey())
                    .title(todoMst.getTitle())
                    .memo(todoMst.getMemo())
                    .useYn(todoMst.getUseYn())
                    .successYn(todoMst.getSuccessYn())
                .build();
    }
}
