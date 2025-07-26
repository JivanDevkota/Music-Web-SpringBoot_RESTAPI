package com.nsu.mymusic.controller;


import com.nsu.mymusic.dtos.AuthenticationRequest;
import com.nsu.mymusic.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {


    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody AuthenticationRequest authenticationRequest){
        String login = userService.login(authenticationRequest);
        return ResponseEntity.ok(login);
    }
}
