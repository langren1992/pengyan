package com.anserx.pengyan.server1.controller;

import com.anserx.pengyan.constants.SecurityConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @GetMapping(value = "/index")
    public String index(HttpServletRequest request){
        String header = request.getHeader(SecurityConstants.USER_HEADER);
        return "重中国index,token:"+header;
    }
}
