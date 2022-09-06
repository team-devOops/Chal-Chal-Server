package com.chalchal.chalchalsever.auth.service;

import com.chalchal.chalchalsever.auth.repository.UserRepository;
import com.chalchal.chalchalsever.domain.User;
import com.chalchal.chalchalsever.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Override
    public User createUser(UserRequest userRequest) {
        User user = userRepository.save(User.builder()
                .email(userRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(userRequest.getPassword()))
                .name(userRequest.getName())
                .nickName(StringUtils.isEmpty(userRequest.getNickName()) ? userRequest.getName() : userRequest.getNickName())
                .phoneNo(userRequest.getPhoneNo().replaceAll("-", ""))
                .userRole(userRequest.getUserRole())
                .build());

        return user;
    }

    @Override
    public User findUser(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(()->new BadCredentialsException("유효하지 않은 아이디입니다."));
    }

    @Override
    public User findUserById(long id) {
        User user = Optional.ofNullable(userRepository.findById(id)).orElseThrow(()->new BadCredentialsException("유효하지 않은 아이디입니다."));
        return user;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User user = Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(()->new BadCredentialsException("이메일이나 비밀번호를 확인해주세요."));

        if (bCryptPasswordEncoder.matches(password, user.getPassword()) == false) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

    @Override
    public boolean validateRegister(String email) {
        if(userRepository.countByEmail(email) > 0) {
            return false;
        }

        return true;
    }
}
