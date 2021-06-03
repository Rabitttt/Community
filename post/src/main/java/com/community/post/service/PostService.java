package com.community.post.service;

import com.community.post.domain.Post;
import com.community.post.domain.User;
import com.community.post.entity.PostEntity;
import com.community.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void deneme(){
        postRepository.addOwnerToPost();
        return;
    }

    public void createNewPost(Post post){
        PostEntity postEntity = post.toEntity();

        postRepository.save(postEntity);
    }

    public void addOwner(Post post){
        PostEntity postEntity = post.toEntity();
    }

}
