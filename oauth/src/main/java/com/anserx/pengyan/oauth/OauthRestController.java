package com.anserx.pengyan.oauth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthRestController {

    @GetMapping(value = "/index")
    public String index(){
        return "index";
    }

}
