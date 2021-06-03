package com.community.AuthServer.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RequestUser {
    private String id;
    private String userName;
    private String password;
}
