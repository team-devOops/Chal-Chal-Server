package com.chalchal.chalchalserver.auth.service;

import com.chalchal.chalchalserver.auth.domain.ProfileImg;
import com.chalchal.chalchalserver.auth.repository.ProfileImgRepository;
import com.chalchal.chalchalserver.global.generate.SvcNo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileImgService {
    private final ProfileImgRepository profileImgRepository;

    /**
     * 프로필 사진 마지막 저장 번호 조회
     *
     * @return count로 order_seq 조회
     */
    public int getLastOrderSeq(Long id) {
        return profileImgRepository.countById(id);
    }

    public ProfileImg saveFirstProfileImg(Long id) {
        return profileImgRepository.save(ProfileImg.builder()
                    .svcNo(SvcNo.getSvcNo())
                    .orderSeq(0)
                    .id(id)
                    .url("https://api.dicebear.com/5.x/identicon/svg?seed=" + id)
                .build());
    }
}
