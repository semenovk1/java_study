package com.study.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.backend.config.TokenAuthentication;
import com.study.backend.dto.LoginDto;
import com.study.backend.enity.Users;
import com.study.backend.repository.UsersRepository;
import com.study.backend.service.AuthUser;
import com.study.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/login", produces = APPLICATION_JSON_VALUE)
@Slf4j
public class LoginController {

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("signin/{token}")
    public ResponseEntity<Users> login(@PathVariable("token") String token) throws Exception{
        log.info("Login for token {}", token);

        byte[] decoded = Base64.getDecoder().decode(token);
        String decodedStr = new String(decoded, StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();

        LoginDto loginDto = mapper.readValue(decodedStr, LoginDto.class);

        log.info("Login for token {}, {}", loginDto.getUser(), loginDto.getPassword());

        Optional<Users> user = usersRepository.getUserByLogin(loginDto.getUser()).stream().findAny();
        user.orElseThrow(() -> new UsernameNotFoundException("Not found user"));

        TokenAuthentication authToken = new TokenAuthentication(user.get());
        authToken.setAuthenticated(passwordEncoder.matches(user.get().getPassword(), loginDto.getPassword()));

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authToken);
        SecurityContextHolder.setContext(securityContext);

        return ResponseEntity.ok(user.get());
    }

    @GetMapping("logout")
    public ResponseEntity logout() throws Exception {
        SecurityContextHolder.clearContext();

        return ResponseEntity.ok().build();
    }
}
