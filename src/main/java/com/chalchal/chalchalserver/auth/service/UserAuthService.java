package com.chalchal.chalchalserver.auth.service;

import com.chalchal.chalchalserver.auth.domain.UserJoinAuth;
import com.chalchal.chalchalserver.auth.dto.UserAuthRequest;

public interface UserAuthService {
    UserJoinAuth createUserAuth(UserAuthRequest userAuthRequest);

    boolean isAuth(Long id);

    UserJoinAuth getUserJoinAuth(Long id);

    UserJoinAuth compareAuthNum(Long id, String authNum);

    UserJoinAuth successAuth(UserJoinAuth userJoinAuth);
}
