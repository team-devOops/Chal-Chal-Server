package com.chalchal.chalchalserver.global.config.security;

import com.chalchal.chalchalserver.auth.jwt.JwtAuthenticationFilter;
import com.chalchal.chalchalserver.auth.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtUtils jwtUtils;

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
           .antMatchers("/h2-console/**", "/swagger-ui/**"); // h2-console
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
            .csrf().disable()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/api/auth/sign-in"
                            , "/api/auth/join"
                            , "/api/auth/refresh"
                            , "/api/auth/mail"
                            , "/swagger-ui/**"
                            , "/swagger-resources/**"
                            , "/swagger-ui.html"
                            , "/v2/api-docs").permitAll()
                //.anyRequest().hasRole("USER")
                .anyRequest().authenticated()
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(jwtUtils), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(customAuthenticationEntryPoint)
            .and()
            .formLogin().disable().headers().frameOptions().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
