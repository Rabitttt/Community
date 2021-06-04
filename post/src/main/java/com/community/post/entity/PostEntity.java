package com.community.post.entity;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;

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

    @Property("owner")
    private long ownerId;
}
