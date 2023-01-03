package com.chalchal.chalchalsever.auth.service;

import com.chalchal.chalchalsever.auth.repository.UserAuthRepository;
import com.chalchal.chalchalsever.domain.UserJoinAuth;
import com.chalchal.chalchalsever.dto.UserAuthRequest;
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
                    .reqSvcNo("@@@")
                    .id(userAuthRequest.getId())
                    .sendEmail(userAuthRequest.getEmail())
                    .authCode(userAuthRequest.getCode())
                    .validDate(userAuthRequest.getLimitDate())
                    .validTime(userAuthRequest.getLimitTime())
                    .authYn("N")
                .build());
    }
}
