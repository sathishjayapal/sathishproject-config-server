package me.sathish.sathishprojectconfigserver.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("auth")
    public String hello() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication a = context.getAuthentication();
        System.out.println("The auth is " + a.getPrincipal());
        return "Hello, " + a.getName() + "!";
    }
}
