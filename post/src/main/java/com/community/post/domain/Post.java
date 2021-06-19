package com.community.post.domain;

import com.community.post.entity.PostEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Post {

    private long id;
    private String description;
    private long ownerId;
    private String image;
    private List<User> likedUsers;

    public static Post fromEntity(PostEntity postEntity){
        return Post.builder()
                .id(postEntity.getId())
                .description(postEntity.getDescription())
                //.ownerId(postEntity.getOwnerId())
                .image(postEntity.getImage())
                .build();
    }

    public PostEntity toEntity(){
        return PostEntity.builder()
                .id(id)
                .description(description)
                .image(image)
                //.ownerId(ownerId)
                .build();
    }

}
