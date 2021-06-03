package com.community.AuthServer.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Utility {

    @Autowired
    private static BCryptPasswordEncoder passwordEncoder;

    public Boolean doPasswordsMatch(String rawPassword,String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}