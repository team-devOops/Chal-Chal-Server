package com.chalchal.chalchalserver.todo.dto;

import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.todo.entity.TodoMst;
import lombok.Getter;

@Getter
public class TodoMstSaveResponse {
    private String svcNo;
    private String topicKey;
    private String title;
    private String memo;
    private Flag useYn;
    private Flag successYn;

    public TodoMstSaveResponse(String svcNo, String topicKey, String title, String memo, Flag useYn, Flag successYn) {
        this.svcNo = svcNo;
        this.topicKey = topicKey;
        this.title = title;
        this.memo = memo;
        this.useYn = useYn;
        this.successYn = successYn;
    }

    public static TodoMstSaveResponse from(TodoMst todoMst) {
        return new TodoMstSaveResponse(todoMst.getSvcNo(), todoMst.getTopicKey(), todoMst.getTitle(), todoMst.getMemo(), todoMst.getUseYn(), todoMst.getSuccessYn());
    }
}
