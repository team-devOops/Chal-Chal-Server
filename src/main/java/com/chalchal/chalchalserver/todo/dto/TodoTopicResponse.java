package com.chalchal.chalchalserver.todo.dto;

import com.chalchal.chalchalserver.global.dto.Flag;
import com.chalchal.chalchalserver.todo.entity.TodoTopic;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoTopicResponse {
    private String svcNo;
    private String emoji;
    private String bgColor;
    private Flag useYn;

    @Builder
    public TodoTopicResponse(String svcNo, String emoji, String bgColor, Flag useYn) {
        this.svcNo = svcNo;
        this.emoji = emoji;
        this.bgColor = bgColor;
        this.useYn = useYn;
    }

    public static TodoTopicResponse from(TodoTopic todoTopic) {
        return TodoTopicResponse.builder()
                .svcNo(todoTopic.getSvcNo())
                .emoji(todoTopic.getEmoji())
                .bgColor(todoTopic.getBgColor())
                .useYn(todoTopic.getUseYn())
                .build();
    }
}
