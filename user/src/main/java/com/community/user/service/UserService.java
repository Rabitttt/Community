package com.community.user.service;

import com.community.user.domain.User;
import com.community.user.entity.UserEntity;
import com.community.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createNewUser(User user) throws Exception {

        if(getUserByUserName(user.getUserName()) != null){
            throw new Exception("Username Already Exists.");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword( passwordEncoder.encode(user.getPassword())); //encode password with bycript algorithm
        userEntity.setUserName(user.getUserName());
        userRepository.save(userEntity);
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
        System.out.println("deneme");
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        //System.out.println(  attr.getRequest().getHeader("id") );
        long id = Long.parseLong(  attr.getRequest().getHeader("id") );
        return getUserById(id);
    }


}
