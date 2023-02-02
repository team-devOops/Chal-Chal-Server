package com.chalchal.chalchalserver.mail.dto;

import com.chalchal.chalchalserver.mail.domain.MailDiv;
import org.springframework.core.convert.converter.Converter;

public class MailDivConverter implements Converter<String, MailDiv> {
    @Override
    public MailDiv convert(String source) {
        return MailDiv.of(source);
    }
}
