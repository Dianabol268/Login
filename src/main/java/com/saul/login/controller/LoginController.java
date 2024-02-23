package com.saul.login.controller;

import com.saul.login.dto.LoginResponse;
import com.saul.login.dto.UsuarioIn;
import com.saul.login.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponse> login(@RequestBody UsuarioIn usuario) {
        return ResponseEntity.ok(usuarioService.login(usuario));
    }

    @PostMapping(value = "register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponse> register(@RequestBody UsuarioIn usuario) {
        return ResponseEntity.ok(usuarioService.register(usuario));
    }

}
