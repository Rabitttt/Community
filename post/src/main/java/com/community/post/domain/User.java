package com.community.post.domain;


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

}
