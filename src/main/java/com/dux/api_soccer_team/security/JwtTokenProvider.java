package com.dux.api_soccer_team.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${security.jwt.secret-key}")
    private String secretKey; // Clave secreta para firmar tokens
    @Value("${security.jwt.expiration-time}")
    private Long tokenValidityInMilliseconds; // Tiempo de validez del token en milisegundos



    public String createToken(UserDetails userDetails) {
        // Crear claims para el token
        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList());

        // Generar token usando la clave secreta y los claims
        Date now = new Date();
        Long validityInMilliseconds = now.getTime() + tokenValidityInMilliseconds;
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            // Validar el token usando la clave secreta
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            System.out.println("Token JWT mal formado");
        } catch (ExpiredJwtException e) {
            System.out.println("Token JWT expirado");
        } catch (UnsupportedJwtException e) {
            System.out.println("Token JWT no soportado");
        } catch (IllegalArgumentException e) {
            System.out.println("Argumentos inválidos para la validación del token");
        }
        return false;
    }

    public String getUserNameFromToken(String token) {
        // Extraer el nombre de usuario de los claims del token
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
}
