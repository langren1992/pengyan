package com.anserx.pengyan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 权限验证启动类
 *
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableDiscoveryClient
@SpringBootApplication
@Slf4j
public class OauthWebApp {
    public static void main( String[] args ) {
        SpringApplication.run(OauthWebApp.class, args);
    }
}
