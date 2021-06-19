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
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor //makes dependency injection
public class PostService {

    WebClient.Builder webClientBuilder;

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

    public void likePost(long likedPostId){
        long requestedUserId = getRequestedUserIdFromHeader();

        PostEntity postEntity = postRepository.findById(likedPostId).orElseThrow();
        postRepository.likePost(requestedUserId,postEntity.getId());
    }

    private long getRequestedUserIdFromHeader(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return Long.parseLong(  attributes.getRequest().getHeader("id") );
    }

    public Post getPostFromId(long id){
        PostEntity postEntity = postRepository.findById(id).orElseThrow();
        return Post.fromEntity(postEntity);
    }

    public List<Post> getSelectedUserPosts(String username){
        List<PostEntity> postEntities = postRepository.getUserPosts(username);
        List<Post> posts = new ArrayList<>();
        postEntities.forEach(postEntity -> {
            posts.add(Post.fromEntity(postEntity));
        });
        return posts;
    }
}
