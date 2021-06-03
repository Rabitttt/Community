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

        UserEntity userEntityStart = userService.getRequestedUser().toEntity();
        UserEntity userEntityEnd = userRepository.findById(followRequestedUserId).orElseThrow();

        List<UserEntity> follower = new ArrayList<>();
        List<UserEntity> followed = new ArrayList<>();
        follower.add(userEntityStart);
        followed.add(userEntityStart);
        userEntityEnd.setFollower(follower);
        userEntityEnd.setFollowed(followed);
        userRepository.save(userEntityEnd);

    }

}
