package com.freemarket.security.services.implementations;

import javax.transaction.Transactional;

import com.freemarket.security.dao.RolDao;
import com.freemarket.security.entity.Rol;
import com.freemarket.security.enums.RolNombre;
import com.freemarket.security.services.interfaces.RolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class RolServiceImpl implements RolService {

    @Autowired
    private RolDao rolDao;

    @Override
    public Rol findByRolName(RolNombre rolName) {
        return rolDao.findByRolNombre(rolName);
    }
    
}