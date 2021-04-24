package com.doctorfast.org.controller;

import com.doctorfast.org.repository.UsuarioRepository;
import com.doctorfast.org.requests.AuthenticationRequest;
import com.doctorfast.org.requests.AuthenticationResponse;
import com.doctorfast.org.security.jwt.JwtUtils;
import com.doctorfast.org.service.UsuarioService;
import com.doctorfast.org.service.implementation.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin( origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtil;

    private UsuarioService usuarioService;

    @Autowired
    public AuthenticationController (
            UsuarioService usuarioService
    ) {
        this.usuarioService = usuarioService;
    }




    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        Authentication authentication;
        try {
             authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        }
        catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userdetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userdetails);
        Integer usuario_id = usuarioService.getUsuarioByCorreo(userdetails.getUsername()).getIdUser();
        return ResponseEntity.ok(new AuthenticationResponse(token, userdetails.getUsername(), usuario_id, userdetails.getAuthorities()));

    }

}
