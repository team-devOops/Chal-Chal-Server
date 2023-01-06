package com.chalchal.chalchalsever.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@Entity
@AllArgsConstructor
@Table(name = "t_mail_history")
@NoArgsConstructor
public class Mail extends BaseDomain {
    @Id
    @Column(name = "svc_no", nullable = false)
    private String svcNo;

    @Column(name = "from_mail")
    private String fromMail;

    @Column(name = "to_mail")
    private String toMail;

    @Column(name = "subject")
    private String subject;

    @Column(name = "content")
    private String content;
}
