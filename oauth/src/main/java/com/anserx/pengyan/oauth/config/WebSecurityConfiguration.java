package com.anserx.pengyan.oauth.config;

import com.anserx.pengyan.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    private final UserService userService;


    @Autowired
    public WebSecurityConfiguration(UserService userService) {
        this.userService = userService;
    }

}
