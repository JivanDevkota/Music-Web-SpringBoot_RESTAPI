package com.nsu.mymusic.service.userServices;


import com.nsu.mymusic.dtos.AuthenticationRequest;

public interface UserService {
    String login(AuthenticationRequest request);
}
