package com.chalchal.chalchalserver.domain.auth.service;

import com.chalchal.chalchalserver.domain.auth.repository.UserRepository;
import com.chalchal.chalchalserver.global.dto.Flag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * EMAIL 기준으로 USE_YN이 'Y'인 회원 조회
     *
     * @return UserDetails 조회 된 내용 출력
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return Optional.ofNullable(userRepository.findByEmailAndUseYn(email, Flag.Y)).orElseThrow(() -> new BadCredentialsException("이메일 주소를 확인해주세요."));
    }
}