package com.saul.login.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String password;
    private UsuarioRol rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Aquí debes proporcionar los roles del usuario como instancias de GrantedAuthority
        // Puedes obtener los roles de tu clase Usuario y convertirlos en instancias de GrantedAuthority
        return List.of((GrantedAuthority) () -> getRol().name());
    }

    @Override
    public String getPassword() {
        return  this.password;
    }

    @Override
    public String getUsername() {
        return this.nombre;
    }

    // Otros métodos de UserDetails: isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled
    // Debes implementar estos métodos según corresponda para tu aplicación

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implementa la lógica adecuada según tus requisitos
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implementa la lógica adecuada según tus requisitos
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implementa la lógica adecuada según tus requisitos
    }

    @Override
    public boolean isEnabled() {
        return true; // Implementa la lógica adecuada según tus requisitos
    }

}
