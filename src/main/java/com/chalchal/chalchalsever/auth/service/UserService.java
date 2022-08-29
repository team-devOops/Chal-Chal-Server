package com.chalchal.chalchalsever.auth.service;

import com.chalchal.chalchalsever.domain.User;

import java.util.List;

public interface UserService {
    User createUser(User params);

    User findUser(String email);

    User findByEmailAndPw(String email, String password);

    List<User> findAll();
}
