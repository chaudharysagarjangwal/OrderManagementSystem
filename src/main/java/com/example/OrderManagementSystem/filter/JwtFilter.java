package com.example.OrderManagementSystem.filter;

import com.example.OrderManagementSystem.Utils.JwtUtil;
import com.example.OrderManagementSystem.reposistory.UserReposistory;
import com.example.OrderManagementSystem.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private UserReposistory userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get the header
        final String authHeader = request.getHeader("Authorization");
        final String token;
        final String email;
        //check if authorization header is present and starts wuth beare4r
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        //Extract jwt token from header
        token = authHeader.substring(7);
        email = jwtUtil.extractEmail(token);
        //check if we have username and no authentication exist yet
        if (email != null) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

            //check if we have username and no authentication exist yet

            if (jwtUtil.isTokenValid(token, userDetails.getUsername())) {
//                UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(userDetails);
              //  List<SimpleGrantedAuthority> authorities = userDetails.get.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                //SetDetails of authencitation
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //update the security  context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
           // filterChain.doFilter(request,response);
        }
        filterChain.doFilter(request,response);
    }
}
