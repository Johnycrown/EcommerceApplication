package com.pheonix.pheonix.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAthorizationFilter extends BasicAuthenticationFilter {
    public JWTAthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
       // super.doFilterInternal(request, response, chain);
        String header = request.getHeader("Authorization");
        if(header== null || !header.startsWith("Bearer ")){
            chain.doFilter(request,response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(getAuthenticationToken(request));
    }

    private Authentication getAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token != null){
            String email = JWT.require(Algorithm.HMAC256("MyAppScret@!"))
                            .build()
                            .verify(token.replace("Bearer ",  ""))
                            .getSubject();
            if (email!=null){
                return  new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
            }

        }
        return  null;
    }
}
