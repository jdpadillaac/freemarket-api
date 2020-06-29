package com.freemarket.security.services.interfaces;

import com.freemarket.security.entity.Usuario;

public interface UsuarioService {
    
    public Usuario findByUserName(String username);

    public Usuario save(Usuario usuario);

}