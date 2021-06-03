package com.community.ApiGateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator { //List of open api's

    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/login",
            "/user/register",
            "/user/profile/from/username"

            // "/user/profile"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
