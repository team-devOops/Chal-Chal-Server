package com.chalchal.chalchalsever.auth.service;

import com.chalchal.chalchalsever.domain.User;
import com.chalchal.chalchalsever.dto.UserRequest;

import java.util.List;

public interface UserService {
    User createUser(UserRequest params);

    User findUser(String email);

    User findByEmailAndPassword(String email, String password);

    List<User> findAll();
}
