package com.chalchal.chalchalsever.dto;

import com.chalchal.chalchalsever.domain.Mail;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MailRequest {
    private String to;
    private String subject;
    private String text;

    public MailRequest(String to, String subject, String text) {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public static MailRequest from(Mail mail) {
        return new MailRequest(mail.getTo(), mail.getSubject(), mail.getText());
    }
}