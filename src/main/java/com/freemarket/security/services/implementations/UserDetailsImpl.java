package com.freemarket.security.services.implementations;

import com.freemarket.security.entity.Usuario;
import com.freemarket.security.entity.UsuarioPrincipal;
import com.freemarket.security.services.interfaces.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioSrv;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioSrv.findByUserName(username);
        return UsuarioPrincipal.build(usuario);
    }
    
}