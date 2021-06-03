package com.community.AuthServer.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AuthResponse {
    private String accessToken;
}
