package com.chalchal.chalchalsever.domain.todo.entity;

import com.chalchal.chalchalsever.global.dto.Flag;
import com.chalchal.chalchalsever.global.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;

/**
 * to do 그룹에 대한 도메인
 * todo_list group key와 함께 사용
 */
@Builder
@Getter
@Entity
@AllArgsConstructor
@Table(name = "t_todo_group")
@NoArgsConstructor
public class TodoGroup {
    @Id
    @Column(name = "SVC_NO", columnDefinition = "varchar(32)")
    private String svcNo;   //시퀀스로 해야 할 지, svcNo로 해야 할지 고민

    @Comment("이모지")
    @Column(name = "EMOJI", columnDefinition = "varchar(16)")
    private String emoji;

    @Comment("배경색")
    @Column(name = "BG_COLOR", columnDefinition = "varchar(8)")
    private String bgColor;

    @Comment("사용여부")
    @Enumerated(EnumType.STRING)
    @Column(name = "USE_YN", columnDefinition = "char(1)")
    private Flag useYn;

    @Comment("정렬 시퀀스")
    @Column(name = "ORDER_SEQ", columnDefinition = "bigint")
    private Long orderSeq;

    public void changeEmoji(String emoji) {
        if(StringUtils.isEmpty(emoji)) {
            return;
        }
        this.emoji = emoji;
    }

    public void changeBgColor(String bgColor) {
        if(StringUtils.isEmpty(bgColor)) {
            return;
        }
        this.bgColor = bgColor;
    }

    public void changeUseYn(Flag useYn) {
        if(ObjectUtils.isEmpty(useYn)) {
            return;
        }
        this.useYn = useYn;
    }
}
