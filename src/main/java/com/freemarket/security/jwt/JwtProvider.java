package com.freemarket.security.jwt;

import java.nio.charset.MalformedInputException;
import java.util.Date;

import com.freemarket.security.entity.UsuarioPrincipal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {
    
    private Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;



    public String generateToken(Authentication auth) {
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) auth.getPrincipal();
        return Jwts.builder()
        .setSubject(usuarioPrincipal.getUsername())
        .setIssuedAt(new Date())
        .setExpiration( new Date(new Date().getTime() + expiration * 1000) )
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
    }


    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }


    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token mal formado");
        }
        catch (UnsupportedJwtException e) {
            logger.error("Token no soportado");
        }
        catch (ExpiredJwtException e) {
            logger.error("Token expirado");
        }
        catch (IllegalArgumentException e) {
            logger.error("Token mal vacio");
        }
        catch (SignatureException e) {
            logger.error("Fallo en la firma");
        }

        return false;
    }

}