package com.chalchal.chalchalsever.auth.service;

import com.chalchal.chalchalsever.auth.repository.UserRepository;
import com.chalchal.chalchalsever.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Override
    public User createUser(User params) {
        params = User.builder()
                .email(params.getEmail())
                .pw(bCryptPasswordEncoder.encode(params.getPw()))
                .build();

        return userRepository.save(params);
    }

    @Override
    public User findUser(String email) {
        return null;
    }

    @Override
    public User findByEmailAndPw(String email, String password) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
