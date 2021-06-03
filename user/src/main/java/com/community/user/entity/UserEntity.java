package com.community.user.entity;
import com.community.user.enums.Gender;
import lombok.*;
import org.springframework.data.neo4j.core.schema.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

import javax.persistence.*;
import java.util.List;


@Node("User")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Property("userName")
    @Column(unique = true)
    private String userName;

    @Property("password")
    private String password;

    @Property("gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Property("description")
    private String description;

    @Property
    private String profilePictureUrl;

    @Relationship(type = "FOLLOWED",direction = Relationship.Direction.OUTGOING)
    private List<UserEntity> followed;

    @Relationship(type = "FOLLOWER",direction = Relationship.Direction.INCOMING)
    private List<UserEntity> follower;
}
