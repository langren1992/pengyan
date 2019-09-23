package com.anserx.pengyan.oauth.exception;

import com.alibaba.fastjson.JSON;
import com.anserx.pengyan.common.Result;
import com.anserx.pengyan.common.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失效异常捕获处理
 */
@Component
@Slf4j
public class SelfOAuth2AuthExceptionEntryPoint extends OAuth2AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        if (e.getCause() instanceof InvalidTokenException) {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(JSON.toJSONString(Result.error(ResultEnum.CODE_401)));
        }else if (e instanceof InsufficientAuthenticationException){
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(JSON.toJSONString(Result.error(ResultEnum.CODE_403)));
        }else {
            super.commence(request,response,e);
        }
    }
}
