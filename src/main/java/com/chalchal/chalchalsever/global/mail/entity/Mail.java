package com.chalchal.chalchalsever.global.mail.entity;

import com.chalchal.chalchalsever.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@Table(name = "T_MAIL_HISTORY")
@NoArgsConstructor
public class Mail extends BaseDomain {

    @Id
    @Comment("서비스번호")
    @Column(name = "SVC_NO", nullable = false, columnDefinition = "varchar(32)")
    private String svcNo;

    @Comment("보낸 이메일")
    @Column(name = "FROM_MAIL", columnDefinition = "varchar(64)")
    private String fromMail;

    @Comment("받는 이메일")
    @Column(name = "TO_MAIL", columnDefinition = "varchar(64)")
    private String toMail;

    @Comment("제목")
    @Column(name = "SUBJECT", columnDefinition = "varchar(128)")
    private String subject;

    @Comment("내용")
    @Column(name = "CONTENT", columnDefinition = "text")
    private String content;
}
