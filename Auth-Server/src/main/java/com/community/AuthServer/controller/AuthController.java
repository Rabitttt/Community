package com.community.AuthServer.controller;

import com.community.AuthServer.model.AuthResponse;
import com.community.AuthServer.model.RequestUser;
import com.community.AuthServer.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String login(){
        return "Selam canim ben amcanim";
    }

    @PostMapping("/login")
    public String Login(@RequestBody RequestUser requestUser) throws Exception {
        return authService.login(requestUser);
    }
}
