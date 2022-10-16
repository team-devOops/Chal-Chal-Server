package com.chalchal.chalchalsever.auth.service;

import com.chalchal.chalchalsever.auth.repository.UserAuthRepository;
import com.chalchal.chalchalsever.domain.UserAuth;
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
    public UserAuth createUserAuth(UserAuthRequest userAuthRequest) {
        return userAuthRepository.save(UserAuth.builder()
                    .reqSvcNo("@@@")
                    .id(userAuthRequest.getId())
                    .email(userAuthRequest.getEmail())
                    .code(userAuthRequest.getCode())
                    .limitDate(userAuthRequest.getLimitDate())
                    .limitTime(userAuthRequest.getLimitTime())
                    .authYn("N")
                .build());
    }
}
