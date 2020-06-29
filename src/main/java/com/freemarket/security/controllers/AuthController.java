package com.freemarket.security.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.xml.ws.Binding;

import com.freemarket.global.classes.ValidErrorHandler;
import com.freemarket.global.models.JsonResp;
import com.freemarket.security.dto.NuevoUsuario;
import com.freemarket.security.entity.Rol;
import com.freemarket.security.entity.Usuario;
import com.freemarket.security.enums.RolNombre;
import com.freemarket.security.jwt.JwtProvider;
import com.freemarket.security.services.interfaces.RolService;
import com.freemarket.security.services.interfaces.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private ValidErrorHandler validErrorHandler;
    
    @Autowired
    PasswordEncoder passwrodEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService userSrv;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;


    @PostMapping("/new")
    public ResponseEntity<JsonResp> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingReult) {

        System.out.println(bindingReult.hasErrors());
        if (bindingReult.hasErrors()) {

            // List<String> errors = validErrorHandler.getErrorList(bindingReult);
            
            JsonResp resp = new JsonResp(false, "Datos invalidos", null, "Falta infromacion en los datos enviados" );
            return new ResponseEntity<JsonResp>(resp, HttpStatus.BAD_REQUEST);
        }

        String userName = generateUserName(nuevoUsuario.getEmail());

        Usuario usuario = userSrv.findByUserName(userName);

        if (usuario != null) {
            JsonResp resp = new JsonResp(false, "El usuario ya se encuentra registrado en la base de datos", null, null);
            return new ResponseEntity<JsonResp>(resp, HttpStatus.BAD_REQUEST);
        }

       Usuario newUsuario = new Usuario(nuevoUsuario.getName(), userName, passwrodEncoder.encode(nuevoUsuario.getPassword()), nuevoUsuario.getEmail());

       Set<Rol> roles = new HashSet<>();
       roles.add(rolService.findByRolName(RolNombre.ROLE_USER));

       if (newUsuario.getRoles().contains("admin")) {
           roles.add(rolService.findByRolName(RolNombre.ROLE_ADMIN));
       }

       newUsuario.setRoles(roles);
       Usuario userCreated = userSrv.save(newUsuario);

       JsonResp resp = new JsonResp(true , "Usuario registrado de manera exitosa", userCreated, null);
       return new ResponseEntity<>(resp, HttpStatus.CREATED);

    }

    public String generateUserName(String email) {
        System.out.println(email);
        String[] elements = email.split("@"); 
        return elements[0];
    }

}