package com.bookStore.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/login")
    public String userLogin(){
        return "";
    }
    @PostMapping("/forgot-password")
    public String forgotPassword(){
        return "";
    }
    @PostMapping("/register")
    public String userRegister(){
        return "";
    }
    
}
