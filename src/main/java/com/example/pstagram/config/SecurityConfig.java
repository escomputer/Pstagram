package com.example.pstagram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
    H2 콘솔 허용 설정 추가하기
 */

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // 👉 H2 콘솔을 iframe으로 띄우기 위한 설정
                .and()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // 👉 H2 콘솔 허용!
                        .anyRequest().permitAll()
                )
                .formLogin().disable();

        return http.build();
    }
}
