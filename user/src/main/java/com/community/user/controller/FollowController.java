package com.community.user.controller;

import com.community.user.domain.User;
import com.community.user.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FollowController {
    private final FollowService followService;

    /*
    @PostMapping("/follow/{id}")
    public void followUser(@RequestParam("id") int followRequestUserId){
        followService.followUser(followRequestUserId);
    }
    */

    @PostMapping("/follow/{id}")
    public void followUser(@PathVariable("id") int followRequestUserId){
        followService.followUser(followRequestUserId);
    }

}
