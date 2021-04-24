package com.doctorfast.org.service.implementation;

import com.doctorfast.org.model.Usuario;
import com.doctorfast.org.model.UsuarioRol;
import com.doctorfast.org.repository.UsuarioRepository;
import com.doctorfast.org.repository.UsuarioRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioRolRepository usuarioRolRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> roles = null;

        Usuario usuario = usuarioRepository.findByCorreo(username);
        if(usuario != null){
            UsuarioRol usuarioRol = usuarioRolRepository.findByUserId(usuario.getIdUser());
            roles = Arrays.asList(new SimpleGrantedAuthority(usuarioRol.getRol().getNombre()));
            return new User(usuario.getCorreo(),usuario.getPassword(),roles);
        }
        throw new UsernameNotFoundException("correo de usuario no encontrado" + username);

    }
}
