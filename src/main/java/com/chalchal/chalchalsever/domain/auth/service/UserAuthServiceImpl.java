package com.chalchal.chalchalsever.domain.auth.service;

import com.chalchal.chalchalsever.domain.auth.repository.UserAuthRepository;
import com.chalchal.chalchalsever.domain.auth.entity.UserJoinAuth;
import com.chalchal.chalchalsever.domain.auth.dto.UserAuthRequest;
import com.chalchal.chalchalsever.global.dto.Flag;
import com.chalchal.chalchalsever.global.generate.SvcNo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final UserAuthRepository userAuthRepository;

    @Override
    public UserJoinAuth createUserAuth(UserAuthRequest userAuthRequest) {
        return userAuthRepository.save(UserJoinAuth.builder()
                    .reqSvcNo(SvcNo.getSvcNo())
                    .id(userAuthRequest.getId())
                    .sendEmail(userAuthRequest.getEmail())
                    .authCode(userAuthRequest.getCode())
                    .validDate(userAuthRequest.getValidDate())
                    .validTime(userAuthRequest.getValidTime())
                    .authYn(Flag.N)
                .build());
    }
}
