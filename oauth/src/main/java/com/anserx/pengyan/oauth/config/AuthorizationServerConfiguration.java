package com.anserx.pengyan.oauth.config;


import com.anserx.pengyan.oauth.exception.SelfWebResponseExceptionTranslator;
import com.anserx.pengyan.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    /**
     * mysql
     */
    @Autowired
    private DataSource dataSource;

    /**
     * access token 存储在redis中
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 认证管理器
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * UserService 登录验证
     */
    @Autowired
    private UserService userService;

    /**
     * 密码错误异常提醒
     */
    @Autowired
    private SelfWebResponseExceptionTranslator selfWebResponseExceptionTranslator;

    @Bean
    RedisTokenStore redisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean // 声明 ClientDetails实现
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).clients(clientDetails());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.tokenStore(redisTokenStore());
        endpoints.userDetailsService(userService);
        endpoints.setClientDetailsService(clientDetails());
        endpoints.tokenServices(tokenServices())
                .exceptionTranslator(selfWebResponseExceptionTranslator);
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(redisTokenStore());
        // 是否允许refresh token
        tokenServices.setSupportRefreshToken(true);
        // 复用refresh token
        tokenServices.setReuseRefreshToken(false);
        tokenServices.setClientDetailsService(clientDetails());
        // token有效期自定义设置，默认24小时
        tokenServices.setAccessTokenValiditySeconds(60*60*24);
        //默认7天，这里修改
        tokenServices.setRefreshTokenValiditySeconds(60*60*24*7);
        return tokenServices;
    }
}
