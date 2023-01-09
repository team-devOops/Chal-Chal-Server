package com.chalchal.chalchalsever.domain.todo.entity;

import com.chalchal.chalchalsever.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * to do 내용에 대한 도메인
 */
@Builder
@Getter
@Entity
@AllArgsConstructor
@Table(name = "t_todo_list")
@NoArgsConstructor
public class TodoList extends BaseDomain {

    @Id
    @Comment("서비스번호")
    @Column(name = "SVC_NO", nullable = false, columnDefinition = "varchar(32)")
    private String svcNo;

    @Comment("참조서비스번호")
    @Column(name = "RE_SVC_NO", columnDefinition = "varchar(32)")
    private String reSvcNo;

    @Comment("그룹키")
    @Column(name = "GROUP_KEY", columnDefinition = "varchar(32)")
    private String groupKey;

    @Comment("정렬 시퀀스")
    @Column(name = "ORDER_SEQ", columnDefinition = "bigint")
    private long orderSeq;

    @Comment("할 일 제목")
    @Column(name = "TITLE", columnDefinition = "varchar(128)")
    private String title;

    @Comment("할 일 메모")
    @Column(name = "MEMO", columnDefinition = "varchar(256)")
    private String memo;

    @Comment("사용여부")
    @Column(name = "USE_YN", columnDefinition = "char(1)")
    private char useYn;

    @Comment("완료여부")
    @Column(name = "SUCCESS_YN", columnDefinition = "char(1)")
    private char successYn;

    @Comment("완료일자")
    @Column(name = "SUCCESS_DATE", columnDefinition = "datetime")
    private DateTime successDate;
}
