package com.community.user.entity;

import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.List;

@RelationshipProperties
@NoArgsConstructor
public class FollowEntity {
    @TargetNode
    private List<UserEntity> followers;
}
