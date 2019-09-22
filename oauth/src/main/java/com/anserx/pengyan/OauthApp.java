package com.anserx.pengyan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 权限验证启动类
 *
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableDiscoveryClient
//@EnableFeignClients(basePackages = "com.anserx.pengyan")
@SpringBootApplication
@Slf4j
public class OauthApp {
    public static void main( String[] args ) {
        SpringApplication.run(OauthApp.class, args);
    }
}
