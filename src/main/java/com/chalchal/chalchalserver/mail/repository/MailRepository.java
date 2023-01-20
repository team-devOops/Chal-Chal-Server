package com.chalchal.chalchalserver.mail.repository;

import com.chalchal.chalchalserver.mail.domain.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository  extends JpaRepository<Mail, Long> {

}
