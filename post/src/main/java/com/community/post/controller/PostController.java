package com.community.post.controller;

import com.community.post.domain.Post;
import com.community.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public void createPost(@RequestBody Post post){
        postService.createNewPost(post);
    }

    @GetMapping("/like/{id}")
    public void likePost(@PathVariable("id") long likedPostId){
        postService.likePost(likedPostId);
    }

    @GetMapping("/get/{username}")
    public List<Post> getUserPosts(@PathVariable("username") String username){
        return postService.getSelectedUserPosts(username);
    }
    /*
    @GetMapping("/deneme")
    public String getDeneme(){
        return "calisti";
    }

     */

}
