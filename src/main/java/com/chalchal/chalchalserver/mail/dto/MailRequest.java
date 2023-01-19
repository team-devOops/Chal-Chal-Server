package com.chalchal.chalchalserver.mail.dto;

import com.chalchal.chalchalserver.global.generate.SvcNo;
import com.chalchal.chalchalserver.mail.entity.Mail;
import com.chalchal.chalchalserver.global.util.StringUtils;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MailRequest {
    private String svcNo;
    private String to;
    private String subject;
    private String content;

    public MailRequest(String svcNo, String to, String subject, String content) {
        if(StringUtils.isEmpty(svcNo)) {
           svcNo = SvcNo.getSvcNo();
        }
        this.svcNo = svcNo;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public static MailRequest from(Mail mail) {
        return new MailRequest(mail.getSvcNo(), mail.getToMail(), mail.getSubject(), mail.getContent());
    }
}
