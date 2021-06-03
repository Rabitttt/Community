package com.community.user.service;

import com.community.user.domain.User;
import com.community.user.entity.UserEntity;
import com.community.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ServerWebExchange;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createNewUser(User user) throws Exception {

        if(getUserByUserName(user.getUserName()) != null){
            throw new Exception("Username Already Exists.");
        }
        UserEntity userEntity = user.toEntity();
        userEntity.setPassword( passwordEncoder.encode(user.getPassword()) ); //encode password with bycript algorithm
        userRepository.save(userEntity);

    }
    public User getUserById(long id){
        UserEntity userEntity = userRepository.findById((long) id).orElseThrow();
        return User.fromEntity(userEntity);
    }

    public UserEntity getUserByUserName(String userName){
        System.out.println(userName);
        UserEntity userEntity = userRepository.findUserByUserName(userName);
        System.out.println(userEntity);
        return userEntity;
    }

    public User getRequestedUser() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        //System.out.println(  attr.getRequest().getHeader("id") );
        long id = Long.parseLong(  attr.getRequest().getHeader("id") );
        return getUserById(id);
    }


}
