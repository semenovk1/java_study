package com.study.backend.service;


import com.study.backend.enity.Users;
import com.study.backend.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class UserService implements UserDetailsService {
    @Autowired
    protected UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            List<Users> Lusers=usersRepository.getUserbylogin(username);
            if (Lusers.isEmpty())
                throw new UsernameNotFoundException("Пользователь не найден");

            return new AuthUser(Lusers.get(0));


        } catch (UsernameNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Failed to get user data", ex);
            throw ex;
        }
    }
}
