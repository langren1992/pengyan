package com.anserx.pengyan.oauth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义异常
 * @author zengrui
 */
@JsonSerialize(using = SelfOauthExceptionSerializer.class)
public class SelfOauthException extends OAuth2Exception {

    public SelfOauthException(String msg) {
        super(msg);
    }
}