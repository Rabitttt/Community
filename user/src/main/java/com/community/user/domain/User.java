package com.community.user.domain;

import com.community.user.entity.UserEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
    private long id;
    private String userName;
    private String password;
    private String description;
    private String profilePictureUrl;
    private List<User> followed;
    private List<Post> posts;

    public static User fromEntity(UserEntity userEntity){
        return User.builder()
                .id(userEntity.getId())
                .userName(userEntity.getUserName())
                .description(userEntity.getDescription())
                .password(userEntity.getPassword())
                .profilePictureUrl(userEntity.getProfilePictureUrl())
                .build();
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .id(id)
                .userName(userName)
                .password(password)
                .description(description)
                .profilePictureUrl(profilePictureUrl)
                .build();
    }

    public static List<User> followedListFromEntity(UserEntity userEntity){
        List<User> followedUsers = new ArrayList<>();
        userEntity.getFollowed().forEach(user -> {
            followedUsers.add( fromEntity(user) );
         });
        return followedUsers;
    }

}
