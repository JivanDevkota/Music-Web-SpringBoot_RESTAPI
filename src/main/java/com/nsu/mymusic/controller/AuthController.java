package com.nsu.mymusic.controller;

import com.nsu.mymusic.dtos.AuthenticationRequest;
import com.nsu.mymusic.dtos.AuthenticationResponse;
import com.nsu.mymusic.entity.Users;
import com.nsu.mymusic.jwt.JwtUtils;
import com.nsu.mymusic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private JwtUtils utils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>generateToken(@RequestBody AuthenticationRequest request){

        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken
                        (request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = utils.generateToken(authenticate.getName());
        String refreshToken = utils.generateRefreshToken(authenticate.getName());
        String role = authenticate.getAuthorities().iterator().next().getAuthority();
        Optional<Users> user = userRepository.findByUsername(request.getUsername());
        System.out.println(token);

        AuthenticationResponse response=new AuthenticationResponse(token,"Success",role,user.get().getId(),refreshToken);
        return ResponseEntity.ok(response);

    }



}
