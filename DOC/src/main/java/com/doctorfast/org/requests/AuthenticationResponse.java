package com.doctorfast.org.requests;

import com.doctorfast.org.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthenticationResponse {

    private String token;
    private String type = "Bearer";
    private String username;
    private Integer usuario_id;
    private Collection<? extends GrantedAuthority> authorities;



    public AuthenticationResponse(String token, String username, Integer usuario_id, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.token = token;
        this.username = username;
        this.authorities = authorities;
        this.usuario_id = usuario_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
