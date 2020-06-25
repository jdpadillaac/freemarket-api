package com.freemarket.security.dao;

import com.freemarket.security.entity.Rol;
import com.freemarket.security.enums.RolNombre;

import org.springframework.data.repository.CrudRepository;

public interface RolDao extends CrudRepository<Rol, Long> {
    
    public Rol findByRolNombre(RolNombre rolNombre);
}