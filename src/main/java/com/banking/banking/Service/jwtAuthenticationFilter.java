package com.banking.banking.Service;

import com.banking.banking.model.AppUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class jwtAuthenticationFilter  extends OncePerRequestFilter {

    private  final jwtService jwtServices;
    private  final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
         @NonNull   HttpServletResponse response,
        @NonNull    FilterChain filterChain) throws ServletException, IOException {

        String jwttoken = request.getHeader("Authorization");
        if (jwttoken == null ||!jwtServices.isTokenValid(jwttoken.substring(7))) {
            filterChain.doFilter(request, response);

        }
        jwttoken = jwttoken.startsWith("Bearer ") ? jwttoken.substring( 7): jwttoken;
        String subject = jwtServices.extractSubject(jwttoken);
        AppUser user = (AppUser) userDetailsService.loadUserByUsername(subject);
        var context = SecurityContextHolder.getContext();
        if (user!=null && context.getAuthentication() == null) {
            var authenticationToken = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
            authenticationToken.setDetails(authenticationToken);
            context.setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request , response);
    }
}

