package com.community.AuthServer.service;

import com.community.AuthServer.model.AuthResponse;
import com.community.AuthServer.model.RequestUser;
import com.community.AuthServer.security.Utility;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    WebClient.Builder webClientBuilder;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    Utility utility;

    @Autowired
    private JwtUtil jwtUtil;
    //private AuthResponse authResponse;


    public String login(RequestUser requestUser) throws Exception {

        RequestUser user = webClientBuilder.build()
                .get()
                .uri("http://localhost:8080/user/profile/from/username/" + requestUser.getUserName())
                .retrieve()
                .bodyToMono(RequestUser.class)
                .block();

        System.out.println(user);

        if(user.getUserName() != null && passwordEncoder.matches(requestUser.getPassword(), user.getPassword())){ //Username and Password control for login
            String myToken = jwtUtil.generate(user);
            System.out.println(myToken);
            return myToken;
        }
        throw new Exception("username wrong...");
    }
}