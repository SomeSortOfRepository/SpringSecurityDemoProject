package ru.dolinini.springsecuritydemoproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthorizationController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
    @GetMapping("/success")
    public String getSuccessAuthorizationPage() {
        return "successfulauth";
    }

}
