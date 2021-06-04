package com.community.post.service;

import com.community.post.domain.Post;
import com.community.post.domain.User;
import com.community.post.entity.PostEntity;
import com.community.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Service
@RequiredArgsConstructor //makes dependency injection
public class PostService {

   // @Autowired
  //  WebClient.Builder webClientBuilder;

    private final PostRepository postRepository;

    public void addOwner(PostEntity postEntity){
        postRepository.addOwnerToPost(postEntity.getOwnerId(),postEntity.getId());
    }

    public void createNewPost(Post post){
        System.out.println("post id = " + post.getId());
        PostEntity postEntity = post.toEntity();
        System.out.println(postEntity);

        postRepository.save(postEntity);

        System.out.println(postEntity);

        long userId = getRequestedUserIdFromHeader();

        postEntity.setOwnerId(userId);
        addOwner(postEntity); //Make graph db relation (Edge between Post and User Nodes)
    }
    private long getRequestedUserIdFromHeader(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return Long.parseLong(  attributes.getRequest().getHeader("id") );
    }

}
