package com.chalchal.chalchalsever.config.jwt;

import com.chalchal.chalchalsever.auth.repository.UserTokenInfoRepository;
import com.chalchal.chalchalsever.auth.service.UserTokenInfoService;
import com.chalchal.chalchalsever.domain.User;
import com.chalchal.chalchalsever.domain.UserTokenInfo;
import com.chalchal.chalchalsever.dto.TokenResponse;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtConfig {
    @Value("${jwt.token.access}")
    private String secretKey;

    @Value("${jwt.token.refresh}")
    private String refreshKey;

    @Value("${jwt.expire.time}")
    private long expireTime;

    private final UserDetailsService userDetailsService;
    private final UserTokenInfoRepository userTokenInfoRepository;
    private final UserTokenInfoService userTokenInfoService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String userEmail, List<String> roleList) {
        Date now = new Date();
        return Jwts.builder()
                .setClaims(createClaims(userEmail, roleList))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expireTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public long createRefreshToken(User user) {
        Date now = new Date();
        String refreshToken = Jwts.builder()
                .setSubject(user.getEmail())
                .setHeader(createHeader())
                .setClaims(createClaims(user.getEmail(), Arrays.asList(user.getUserRole().getValue())))
                .setExpiration(createExpireDate(1000 * 60 * 10))
                //.setExpiration(new Date(now.getTime() + expireTime))
                .signWith(SignatureAlgorithm.HS256, refreshKey)
                .compact();

        TokenResponse tokenResponse = TokenResponse.builder()
                .id(user.getId())
                .REFRESH_TOKEN(refreshToken)
                .build();

        return userTokenInfoService.createUserTokenInfo(tokenResponse).getTokenIndex();
    }

    public Authentication getAuthentication(String token) {
        String email = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }

    public boolean validateToken(HttpServletRequest request, String jwtToken) {
        log.debug("jwtToken : " + jwtToken);
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return claims.getBody().getExpiration().before(new Date()) == false;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }

        return false;
    }

    public boolean validateRefreshToken(UserTokenInfo userTokenInfo) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(refreshKey).parseClaimsJws(userTokenInfo.getRefreshToken());
            return claims.getBody().getExpiration().before(new Date()) == false;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("refreshToken 재발급");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }

        return false;
    }

    private Map<String, Object> createClaims(String email, List<String> roleList) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", roleList);
        return claims;
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "ACCESS_TOKEN");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());

        return header;
    }

    private Date createExpireDate(long expireDate) {
        long curTime = System.currentTimeMillis();
        return new Date(curTime + expireDate);
    }

    private Key createSigningKey(String key) {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public boolean existsRefreshToken(String refreshToken) {
        return userTokenInfoRepository.existsByRefreshToken(refreshToken);
    }

    public Boolean reCreateRefreshToken(String email) throws Exception {
        return true;
    }

    public long getRefreshTokenByCookieIndex(HttpServletRequest httpServletRequest, String cookieName) {
        Cookie[] cookies = httpServletRequest.getCookies();

        for (Cookie cookie : cookies) {
            if(cookieName.equals(cookie.getName()))
                return Long.parseLong(cookie.getValue());
        }

        return 0;
    }
}
