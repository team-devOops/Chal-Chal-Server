package com.chalchal.chalchalserver.mail.service;

import com.chalchal.chalchalserver.mail.domain.Mail;
import com.chalchal.chalchalserver.mail.dto.MailRequest;
import com.chalchal.chalchalserver.mail.repository.MailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.sql.SQLException;

/**
 * 메일 발송 관련 service
 *
 * @author zinzoddari
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {
    @Value("${spring.mail.username}")
    private final String MAIL_FROM;

    public static final String MAIL_SEND_ENCODING = "UTF-8";
    private final JavaMailSender mailSender;

    private final MailRepository mailRepository;

    public void mailSend(MailRequest mailRequest) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, MAIL_SEND_ENCODING);
            helper.setFrom(MAIL_FROM);
            helper.setTo(mailRequest.getTo());
            helper.setSubject(mailRequest.getSubject());
            helper.setText(mailRequest.getContent(), true);
            mailSender.send(message);

            crateMail(mailRequest);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Email 전송 내역 DB에 저장
     */
    @Transactional(readOnly = true)
    @Retryable(value = SQLException.class)
    public Mail crateMail(MailRequest mailRequest) {
        return mailRepository.save(Mail.builder()
                .svcNo(mailRequest.getSvcNo())
                .toMail(mailRequest.getTo())
                .fromMail(MAIL_FROM)
                .subject(mailRequest.getSubject())
                .content(mailRequest.getContent())
                .build());
    }
}
