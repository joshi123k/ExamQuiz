package com.exam.controller;

import com.exam.model.JWTresponse;
import com.exam.model.User;
import com.exam.service.UserDetailsServiceImpl;
import com.exam.helper.JWTUtil;

import com.exam.model.JWTrequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin( origins = "*")
public class JWTController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl customUserDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/token")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JWTrequest jwtRequest) throws Exception {
       try {

             this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));

        }catch (UsernameNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("User not Found");
        }catch (BadCredentialsException e)
        {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }

        UserDetails userDetails= this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

//        UserDetails userDetails= this.customUserDetailsService.loadUserByUsername(authentication);

        String token=this.jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JWTresponse(token));
    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal)
    {
        return ((User)this.customUserDetailsService.loadUserByUsername(principal.getName()));
    }

}
