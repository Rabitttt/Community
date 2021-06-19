package com.community.user.service;


import com.community.user.domain.User;
import com.community.user.entity.UserEntity;
import com.community.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final UserRepository userRepository;
    private final UserService userService;

    public void followUser(long followRequestedUserId){

        User user = userService.getRequestedUser();
        UserEntity userEntityStart = userRepository.findById(user.getId()).orElseThrow();
        UserEntity userEntityEnd = userRepository.findById(followRequestedUserId).orElseThrow();
        System.out.println(userEntityStart.getFollowed());
       // List<UserEntity> follower = new ArrayList<>();
       // List<UserEntity> followed = new ArrayList<>();
       // follower.add(userEntityStart);
        //followed.add(userEntityEnd);
       // userEntityEnd.setFollower(follower);
        userEntityStart.getFollowed().add(userEntityEnd);
        UserEntity asd = userRepository.save(userEntityStart);

        System.out.println(asd);
    }

}
