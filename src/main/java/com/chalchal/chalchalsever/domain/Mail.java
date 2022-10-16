package com.chalchal.chalchalsever.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Getter
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seq_no", nullable = false)
    private Long seqNo;

    @Column(name = "from")
    private String from;

    @Column(name = "to")
    private String to;

    @Column(name = "subject")
    private String subject;

    @Column(name = "text")
    private String text;
}
