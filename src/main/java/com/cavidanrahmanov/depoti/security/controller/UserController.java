package com.cavidanrahmanov.depoti.security.controller;

import com.cavidanrahmanov.depoti.security.dto.LoginRequestDTO;
import com.cavidanrahmanov.depoti.security.dto.UserRequestDTO;
import com.cavidanrahmanov.depoti.security.model.Users;
import com.cavidanrahmanov.depoti.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public Users register(@RequestBody UserRequestDTO userDTO){
        return userService.register(userDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO loginRequest){
        return userService.verify(loginRequest);
    }

}
