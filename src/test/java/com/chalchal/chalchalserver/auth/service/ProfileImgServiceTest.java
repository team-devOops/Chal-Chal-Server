package com.chalchal.chalchalserver.auth.service;

import com.chalchal.chalchalserver.auth.domain.UserProfileImg;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProfileImgServiceTest {
    @Autowired
    private ProfileImgService profileImgService;

    @Test
    @DisplayName("profileImg 마지막 저장된 orderSeq 조회")
    void getLastOrderSeq() {
        //given
        final int requestTry = 3;
        Long id = 5L;

        for (int i = 0; i < requestTry; i++) {
            profileImgService.saveFirstProfileImg(id);
        }

        //when
        int result = profileImgService.getLastOrderSeq(id);

        //then
        assertThat(result).isEqualTo(requestTry);

    }

    @Test
    @DisplayName("profileImg 생성")
    void saveFirstProfileImg() {
        //given
        Long id = 3L;

        //when
        UserProfileImg result = profileImgService.saveFirstProfileImg(id);

        //then
        assertThat(result).isNotNull();
    }
}