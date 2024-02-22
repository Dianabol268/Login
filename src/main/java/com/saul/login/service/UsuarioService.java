package com.saul.login.service;

import com.saul.login.dto.LoginResponse;
import com.saul.login.dto.UsuarioIn;
import com.saul.login.model.entity.Usuario;
import com.saul.login.model.entity.UsuarioRol;
import com.saul.login.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginResponse login(UsuarioIn request) throws UsernameNotFoundException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getNombre(), request.getPassword()));
        UserDetails user=usuarioRepository.findByNombre(request.getNombre()).orElseThrow();
        String token=jwtService.getToken( generateExtraClaims(user), user);
        return LoginResponse.builder()
                .token(token)
                .build();
    }


    public Map<String, Object> generateExtraClaims(UserDetails user){
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("rol", user.getAuthorities());
        extraClaims.put("username", user.getUsername());
        return extraClaims;
    }

    public LoginResponse register(UsuarioIn usuario) {
        // deberias pasarlo a un mapper :)
         Usuario user= new Usuario();
         user.setNombre(usuario.getNombre());
         user.setPassword(passwordEncoder.encode(usuario.getPassword()));
         user.setRol(UsuarioRol.USER);

                 // por defecto el rol es usuario basiquinchi
         usuarioRepository.save(user);
        return LoginResponse.builder().token(jwtService.getToken( generateExtraClaims(user), user)).build();
    }

}
