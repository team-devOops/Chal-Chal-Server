package com.chalchal.chalchalsever.auth.service;

import com.chalchal.chalchalsever.auth.repository.UserRepository;
import com.chalchal.chalchalsever.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Override
    public User saveUser(User params) {
        String encodePassword = bCryptPasswordEncoder.encode(params.getPw());

        return userRepository.save(
            User.builder()
                .pw(encodePassword)
                .id(params.getId())
                .email(params.getEmail())
                .build()
        );
    }
}
