package com.chalchal.chalchalserver.mail.dto;

import com.chalchal.chalchalserver.global.generate.SvcNo;
import com.chalchal.chalchalserver.mail.domain.Mail;
import com.chalchal.chalchalserver.global.util.StringUtils;
import com.chalchal.chalchalserver.mail.domain.MailDiv;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MailRequest {
    private String svcNo;

    private MailDiv mailDiv;
    private String to;
    private String subject;
    private String content;

    @Builder
    public MailRequest(String svcNo, MailDiv mailDiv, String to, String subject, String content) {
        this.svcNo = validSvcNo(svcNo);
        this.mailDiv = mailDiv;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    private String validSvcNo(String svcNo) {
        if(StringUtils.isEmpty(svcNo)) {
            return SvcNo.getSvcNo();
        }

        return svcNo;
    }

    public static MailRequest from(Mail mail) {
        return new MailRequest(mail.getSvcNo(), mail.getMailDiv(), mail.getToMail(), mail.getSubject(), mail.getContent());
    }
}
