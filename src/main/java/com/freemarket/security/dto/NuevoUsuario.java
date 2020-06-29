package com.freemarket.security.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NuevoUsuario {

    @NotBlank(message = "Debe agregar un nombre")
    private String name;
    
    @NotBlank(message = "Debe agregar una constraseña")
    private String password;

    @NotBlank(message = "Debe agregar un email")
    @Email(message = "Debe agregar un emial")
    private String email;

    private Set<String> roles = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}