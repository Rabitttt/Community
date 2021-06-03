package com.community.post.domain;

import com.community.post.entity.PostEntity;
import lombok.*;

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

    public static Post fromEntity(PostEntity postEntity){
        return Post.builder()
                .id(postEntity.getId())
                .description(postEntity.getDescription())
                //.ownerId(postEntity.getOwnerId())
                .build();
    }

    public PostEntity toEntity(){
        return PostEntity.builder()
                .id(id)
                .description(description)
                //.ownerId(ownerId)
                .build();
    }
}
