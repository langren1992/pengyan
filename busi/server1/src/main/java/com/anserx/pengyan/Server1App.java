package com.anserx.pengyan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 业务测试服务1启动类
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
@Slf4j
public class Server1App {
    public static void main( String[] args ) {
        SpringApplication.run(Server1App.class, args);

    }
}
