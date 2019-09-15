package com.anserx.pengyan.oauth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Arrays;

@RestController
@Slf4j
public class UserService implements UserDetailsService, Serializable {

    @Override
    public UserDetails loadUserByUsername(String userCode) throws UsernameNotFoundException {
        User user = new User("111111", "$2a$10$cKRbR9IJktfmKmf/wShyo.5.J8IxO/7YVn8twuWFtvPgruAF8gtKq", Arrays.asList(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "1111111";
            }
        }));
        return user;
    }
}
