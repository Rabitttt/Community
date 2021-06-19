package com.community.user.service;

import com.community.user.domain.Post;
import com.community.user.domain.User;
import com.community.user.entity.UserEntity;
import com.community.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    WebClient.Builder webClientBuilder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createNewUser(User user) throws Exception {
        if(getUserByUserName(user.getUserName()) != null){
            throw new Exception("Username Already Exists.");
        }
        user.setPassword( passwordEncoder.encode(user.getPassword())); //encode password with bycript algorithm
        userRepository.createUser(user.getUserName(),user.getPassword());
    }
    public User getUserById(long id){
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
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
        long id = Long.parseLong(  attr.getRequest().getHeader("id") );
        return getUserById(id);
    }

    public User getUserProfile(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        long id = Long.parseLong(  attr.getRequest().getHeader("id") );
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        User user = User.fromEntity(userEntity);
        user.setFollowed(User.followedListFromEntity(userEntity));
        return user;

    }

}
