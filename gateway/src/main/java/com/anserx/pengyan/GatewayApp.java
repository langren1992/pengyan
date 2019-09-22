package com.anserx.pengyan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
//import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 网关配置启动类
 */
@EnableZuulProxy
@EnableOAuth2Sso
@EnableDiscoveryClient
@SpringBootApplication
//@EnableFeignClients(basePackages = "com.anserx.pengyan")
@Slf4j
public class GatewayApp {
    public static void main( String[] args ) {
        SpringApplication.run(GatewayApp.class, args);
    }
}
