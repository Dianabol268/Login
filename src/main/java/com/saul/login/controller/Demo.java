package com.saul.login.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Demo {
    @GetMapping("/demo")
    @PreAuthorize("isAuthenticated()")
    public String demo() {
        return "Demo";
    }


    @GetMapping("/demo2")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String demo2() {
        return "Demo2";
    }

}
