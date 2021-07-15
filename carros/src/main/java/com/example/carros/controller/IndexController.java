package com.example.carros.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String GetSpring() {
        return "API de Carros";
    }


    @GetMapping("userinfo")
    public UserDetails userInfo(@AuthenticationPrincipal UserDetails user){
        return user;
    }


}
