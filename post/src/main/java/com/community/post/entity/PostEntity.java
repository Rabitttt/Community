package com.community.post.entity;

import com.community.post.domain.User;
import lombok.*;
import org.springframework.data.neo4j.core.schema.*;
import java.util.List;



@Node("Post")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class PostEntity {
    @Id
    @GeneratedValue
    private long id;

    @Property("description")
    private String description;

    @Property("image")
    private String image;

    @Property("owner")
    private long ownerId;

    private List<User> likedUsers;

}
