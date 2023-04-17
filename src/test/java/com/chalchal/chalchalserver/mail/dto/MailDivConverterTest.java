package com.chalchal.chalchalserver.mail.dto;

import com.chalchal.chalchalserver.global.exception.BaseException;
import com.chalchal.chalchalserver.mail.domain.MailDiv;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MailDivConverterTest {
    private final MailDivConverter converter = new MailDivConverter();

    @Test
    void shouldConvertToMailDiv() {
        // given
        String contents = "signup";

        // when
        MailDiv mailDiv = converter.convert(contents);

        // then
        assertEquals(mailDiv, MailDiv.SIGN_UP);
    }

    @Test
    void shouldThrowExceptionForInvalidMailDiv() {
        // given
        String param = "invalid";

        // when, then
        assertThrows(BaseException.class, () -> converter.convert(param));
    }
}