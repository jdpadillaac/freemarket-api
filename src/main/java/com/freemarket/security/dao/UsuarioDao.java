package com.freemarket.security.dao;

import com.freemarket.security.entity.Usuario;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioDao extends CrudRepository<Usuario, Long>{
    
    public Usuario findByUsername(String username);
}