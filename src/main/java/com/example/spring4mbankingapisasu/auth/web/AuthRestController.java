package com.example.spring4mbankingapisasu.auth;

import com.example.spring4mbankingapisasu.base.BaseApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {
    @PostMapping("/register")
    public BaseApi<?> register(){
        return null;
    }
}
