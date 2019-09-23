package com.anserx.pengyan.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class OauthRestController{



    @GetMapping(value = "/index")
    public String index(){
        return "index";
    }

    @GetMapping("/user")
    public Principal principal(Principal user){
        return user;
    }
}
