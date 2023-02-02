package com.chalchal.chalchalserver.global.config.web;

import com.chalchal.chalchalserver.mail.dto.MailDivConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final MailDivConverter mailDivConverter;

    public WebConfig(MailDivConverter mailDivConverter) {
        this.mailDivConverter = mailDivConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(mailDivConverter);
    }
}
