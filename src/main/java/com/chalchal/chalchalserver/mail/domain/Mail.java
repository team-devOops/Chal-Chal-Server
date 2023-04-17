package com.chalchal.chalchalserver.mail.domain;

import com.chalchal.chalchalserver.global.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "MAIL_HISTORY")
@NoArgsConstructor
public class Mail extends BaseDomain {

    @Id
    @Comment("서비스번호")
    @Column(name = "SVC_NO", nullable = false, columnDefinition = "varchar(32)")
    private String svcNo;

    @Comment("발송 구분")
    @Enumerated(EnumType.STRING)
    @Column(name = "MAIL_DIV", columnDefinition = "varchar(16)")
    private MailDiv mailDiv;

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

    @Builder
    public Mail(String svcNo, MailDiv mailDiv, String fromMail, String toMail, String subject, String content) {
        this.svcNo = svcNo;
        this.mailDiv = mailDiv;
        this.fromMail = fromMail;
        this.toMail = toMail;
        this.subject = subject;
        this.content = content;
    }
}
