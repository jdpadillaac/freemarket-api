package com.freemarket.security.dto;

import javax.validation.constraints.NotBlank;

public class LoginUsuario {
    
    @NotBlank(message = "Debe agregar un nombre de usuario para el inicio de sesion")
    private String userName;

    @NotBlank(message = "Debe agregar una contraseña para el inicio de sesion")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}