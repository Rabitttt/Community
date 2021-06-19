package com.community.user.domain;


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
    private String image;
    private long ownerId;

}

