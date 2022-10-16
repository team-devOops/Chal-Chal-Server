package com.chalchal.chalchalsever.auth.service;

import com.chalchal.chalchalsever.domain.UserAuth;
import com.chalchal.chalchalsever.dto.UserAuthRequest;

public interface UserAuthService {
    UserAuth createUserAuth(UserAuthRequest userAuthRequest);
}
