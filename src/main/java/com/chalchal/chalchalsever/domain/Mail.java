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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seq_no", nullable = false)
    private Long seqNo;

    @Column(name = "from_mail")
    private String from;

    @Column(name = "to_mail")
    private String to;

    @Column(name = "subject")
    private String subject;

    @Column(name = "text")
    private String text;
}
