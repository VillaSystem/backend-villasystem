package com.example.backendvillasystem.auth.domain.model;

import com.example.backendvillasystem.client_management.domain.model.aggregates.Clients;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private final Clients client;

    public UserDetailsImpl(Clients client) {
        this.client = client;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Usamos el nombre del rol desde la entidad Roles
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + client.getRole().getName().toUpperCase())
        );
    }

    @Override
    public String getPassword() {
        return client.getPassword();
    }

    @Override
    public String getUsername() {
        return client.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return client.getId();
    }

    public String getRoleName() {
        return client.getRole().getName();
    }
}
