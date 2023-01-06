package com.chalchal.chalchalsever.global.mail.repository;

import com.chalchal.chalchalsever.global.mail.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository  extends JpaRepository<Mail, Long> {

}
