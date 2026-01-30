package com.bcefit.projet.exposition.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization");
        DecodedJWT decodedJWT;

        if (bearerToken==null ||!bearerToken.startsWith("Bearer "))
        {
            filterChain.doFilter(request, response);
            return;
        }

        //decode token
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("monSecret123456789")).build();
        String token = bearerToken.substring("Bearer ".length());


        // Bloc pour tester verify() qui lancera une exception si le token est expiré
        try {

            decodedJWT = verifier.verify(token);

            // récupéreration du loggin pour l'ajouter à la requetes => sera utilisé par les services backend pour l'identification user
            String username = decodedJWT.getSubject();

            request.setAttribute("userEmail",username);
            response.setHeader("userEmail",username);

            // récupère les rôles
            List<String> roles = decodedJWT.getClaims().get("claims").asList(String.class);
            Collection <GrantedAuthority> authorities = new ArrayList<>();

            for (String r :roles)
                authorities.add(new SimpleGrantedAuthority(r));

            // authentifie l'utilisateur
            UsernamePasswordAuthenticationToken user =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(user);

            filterChain.doFilter(request, response);
            return;

        } catch (TokenExpiredException tokenExpiredException) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "JWT_expired");
        }

    }

}
