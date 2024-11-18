package com.example.backendvillasystem.auth.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Generamos una clave segura de 512 bits utilizando la clase Keys
    private final SecretKey jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final long jwtExpirationMs = 86400000; // 24 horas

    /**
     * Genera un token JWT para el usuario autenticado.
     *
     * @param username El nombre de usuario (generalmente el email)
     * @param role     El rol del usuario
     * @return El token JWT generado
     */
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role) // Incluimos el rol del usuario como un claim
                .setIssuedAt(new Date()) // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // Expiración
                .signWith(jwtSecretKey) // Firmamos con la clave secreta
                .compact();
    }

    /**
     * Extrae el nombre de usuario (subject) del token JWT.
     *
     * @param token El token JWT
     * @return El nombre de usuario extraído del token
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Valida un token JWT.
     *
     * @param token El token JWT
     * @return true si el token es válido; false en caso contrario
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Si ocurre alguna excepción, el token no es válido
            return false;
        }
    }

    /**
     * Extrae el rol del usuario desde el token JWT.
     *
     * @param token El token JWT
     * @return El rol del usuario extraído del token
     */
    public String getRoleFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }
}