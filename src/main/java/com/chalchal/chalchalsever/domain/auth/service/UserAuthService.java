package com.chalchal.chalchalsever.domain.auth.service;

import com.chalchal.chalchalsever.domain.auth.entity.UserJoinAuth;
import com.chalchal.chalchalsever.domain.auth.dto.UserAuthRequest;

public interface UserAuthService {
    UserJoinAuth createUserAuth(UserAuthRequest userAuthRequest);

    boolean isAuth(Long id);

    UserJoinAuth getUserJoinAuth(Long id);

    UserJoinAuth compareAuthNum(Long id, String authNum);

    UserJoinAuth successAuth(UserJoinAuth userJoinAuth);
}
