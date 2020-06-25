package com.freemarket.security.services.interfaces;

import com.freemarket.security.entity.Rol;
import com.freemarket.security.enums.RolNombre;

public interface RolService {
    

    public Rol findByRolName(RolNombre rolName);
}