package com.exam.config;

import com.exam.helper.JWTUtil;
import com.exam.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl customUserDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String requestTokenHeader = httpServletRequest.getHeader("Authorization");

        String username=null;
        String jwtToken=null;

        if (requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer "))
        {
            jwtToken = requestTokenHeader.substring(7);

            try
            {
                username=this.jwtUtil.getUsernameFromToken(jwtToken);

            }catch (Exception e)
            {
                e.printStackTrace();
            }

//            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);

            if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
            {

                UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);

                

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
//            else
//            {
//                System.out.println("Token is not Validated...");
//            }
         //   filterChain.doFilter(httpServletRequest,httpServletResponse);


        }


     filterChain.doFilter(httpServletRequest,httpServletResponse);


    }


}
