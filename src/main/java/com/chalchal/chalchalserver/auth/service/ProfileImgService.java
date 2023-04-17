package com.chalchal.chalchalserver.auth.service;

import com.chalchal.chalchalserver.auth.domain.UserProfileImg;
import com.chalchal.chalchalserver.auth.repository.ProfileImgRepository;
import com.chalchal.chalchalserver.global.generate.SvcNo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileImgService {
    private final ProfileImgRepository profileImgRepository;
    private final String PROFILE_IMG = "https://api.dicebear.com/5.x/identicon/svg?seed=";

    /**
     * 프로필 사진 마지막 저장 번호 조회
     *
     * @return count로 order_seq 조회
     */
    @Transactional(readOnly = true)
    public int getLastOrderSeq(Long id) {
        return profileImgRepository.countById(id);
    }

    /**
     * 프로필 사진 기본 값 저장
     */
    public UserProfileImg saveFirstProfileImg(Long id) {
        return profileImgRepository.save(UserProfileImg.builder()
                    .svcNo(SvcNo.getSvcNo())
                    .orderSeq(0)
                    .id(id)
                    .url(PROFILE_IMG + id)
                .build());
    }
}
