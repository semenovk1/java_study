package com.study.backend.config;

import com.study.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
public class SecurityConfig {
    private static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
        new AntPathRequestMatcher("/v3/api-docs/**"),
        new AntPathRequestMatcher("/configuration/**"),
        new AntPathRequestMatcher("/swagger*/**"),
        new AntPathRequestMatcher("/webjars/**"),
//        new AntPathRequestMatcher("/api/manager"),
        new AntPathRequestMatcher("/error"));
    private static final RequestMatcher PROTECTED_URLS = new NegatedRequestMatcher(
        new AntPathRequestMatcher("/api/**"));

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(PUBLIC_URLS).permitAll()
                .requestMatchers(PROTECTED_URLS).hasAnyAuthority("TEST").anyRequest().authenticated())

            .httpBasic(Customizer.withDefaults())
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return true;
            }
        };//NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Autowired
    public DaoAuthenticationProvider authProvider(PasswordEncoder passwordEncoder, UserService userService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder);
        authProvider.setUserDetailsService(userService);
        return authProvider;
    }
}
