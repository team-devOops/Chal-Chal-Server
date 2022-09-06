package com.chalchal.chalchalsever.auth.service;

import com.chalchal.chalchalsever.domain.User;
import com.chalchal.chalchalsever.dto.TokenResponse;
import com.chalchal.chalchalsever.dto.UserRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    User createUser(UserRequest userRequest);

    User findUser(String email);
    User findUserById(long id);

    User findByEmailAndPassword(String email, String password);

    User resignUser(long id);

    boolean validateRegister(String email);

    HttpHeaders setLogout(HttpServletRequest httpServletRequest);
    ResponseCookie setCookie(TokenResponse tokenResponse);
}
