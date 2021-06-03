package com.community.user.domain;

import com.community.user.entity.UserEntity;
import lombok.*;

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
}
