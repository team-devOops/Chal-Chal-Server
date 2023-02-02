package com.chalchal.chalchalserver.global.config.security;

import com.chalchal.chalchalserver.mail.dto.MailDivConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfig {
    @Bean
    public MailDivConverter mailDivConverter() {
        return new MailDivConverter();
    }
}
