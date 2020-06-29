package com.freemarket.security.services.implementations;

import javax.transaction.Transactional;

import com.freemarket.security.dao.UsuarioDao;
import com.freemarket.security.entity.Usuario;
import com.freemarket.security.services.interfaces.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UsuarioService {

    @Autowired 
    private UsuarioDao usuarioDao;

    @Override
    public Usuario findByUserName(String username) {
        return usuarioDao.findByUsername(username);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioDao.save(usuario);
    }
    
}