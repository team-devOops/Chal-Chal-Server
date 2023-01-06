package com.chalchal.chalchalsever.dto;

import com.chalchal.chalchalsever.domain.Mail;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MailRequest {
    private String to;
    private String subject;
    private String content;

    public MailRequest(String to, String subject, String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public static MailRequest from(Mail mail) {
        return new MailRequest(mail.getToMail(), mail.getSubject(), mail.getContent());
    }
}
