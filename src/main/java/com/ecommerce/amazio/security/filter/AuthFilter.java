package com.ecommerce.amazio.security.filter;

import com.ecommerce.amazio.enums.UserRole;
import com.ecommerce.amazio.security.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class AuthFilter extends OncePerRequestFilter {

    JwtService jwtService;

    @Autowired
    public AuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization"); //token from the incoming request

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {

            String token = bearerToken.substring(7);

            // 1. Validate token
            if (jwtService.validateToken(token)) {

                // 2. Extract email + role from token
                String email = jwtService.extractEmail(token);
                UserRole role  = jwtService.extractRole(token); // ADMIN / CUSTOMER

                // 3. Set Authentication in SecurityContext
                UsernamePasswordAuthenticationToken authentication = //represents the user’s identity (username + authorities).
                        new UsernamePasswordAuthenticationToken(
                                email, //username/email = "alice@example.com"
                                null,
                                Collections.singleton(() -> "ROLE_" + role.name()) //authorities = ["ROLE_ADMIN"]
                        );


                SecurityContextHolder //SecurityContextHolder is Spring Security’s central storage for the current user’s authentication information.
                        .getContext()  //gets the current request’s SecurityContext. SecurityContext is an object that lives inside the SecurityContextHolder.
                        .setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
