package com.example.banking.config;

import com.example.banking.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String auth = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if(auth != null && auth.startsWith("Bearer ")){
            token = auth.substring(7);
            username = jwtService.getUserName(token);
        }
        System.out.println("key "  + auth);
        System.out.println("token " + token);
        System.out.println("username " + username);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            System.out.println("Inside first check");
            UserDetails userDetails = applicationContext.getBean(UserDetailsServiceConfig.class).loadUserByUsername(username);

            System.out.println("Got user details " + userDetails.getUsername() + " " + userDetails.getPassword() );
            if(jwtService.validateToken(token,userDetails)){
                System.out.println("Inside second check");
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
