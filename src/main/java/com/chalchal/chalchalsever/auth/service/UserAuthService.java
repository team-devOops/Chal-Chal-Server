package com.chalchal.chalchalsever.auth.service;

import com.chalchal.chalchalsever.domain.UserJoinAuth;
import com.chalchal.chalchalsever.dto.UserAuthRequest;

public interface UserAuthService {
    UserJoinAuth createUserAuth(UserAuthRequest userAuthRequest);
}
