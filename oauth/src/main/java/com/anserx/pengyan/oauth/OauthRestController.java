package com.anserx.pengyan.oauth;

import com.anserx.pengyan.common.Result;
import com.anserx.pengyan.common.ResultEnum;
import com.anserx.pengyan.oauth.api.OauthAccessApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class OauthRestController{



    @GetMapping(value = "/index")
    public String index(){
        return "index";
    }

    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }
}
