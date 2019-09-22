package com.anserx.pengyan.oauth.api;

import com.anserx.pengyan.common.Result;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;

//@FeignClient(name = "uaa")
public interface OauthAccessApi {

//    @PostMapping("/access/check")
    Result accessCheck(String accessToken);
}
