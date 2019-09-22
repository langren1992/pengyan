package com.anserx.pengyan.oauth;

import com.anserx.pengyan.common.Result;
import com.anserx.pengyan.common.ResultEnum;
import com.anserx.pengyan.oauth.api.OauthAccessApi;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Service
public class OauthAccessApiImpl implements OauthAccessApi {

    @Autowired
    private RedisTokenStore redisTokenStore;

    @Override
    public Result accessCheck(String accessToken) {
        OAuth2AccessToken oAuth2AccessToken = redisTokenStore.readAccessToken(accessToken);
        if (oAuth2AccessToken == null){
            return Result.error(ResultEnum.CODE_401);
        }
        return Result.success();
    }
}
