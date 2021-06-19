package com.community.post.repository;

import com.community.post.entity.PostEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends Neo4jRepository<PostEntity,Long> {
    @Query(value = "MATCH (uu:User),(pp:Post) WHERE id(uu) = $ownerId AND id(pp) = $postId CREATE (uu)-[:owner]->(pp)")
    void addOwnerToPost(long ownerId,long postId);

    @Query(value = "MATCH (uu:User),(pp:Post) WHERE id(uu) = $userId AND id(pp) = $postId CREATE (uu)-[:liked]->(pp)")
    void likePost(long userId,long postId);

    @Query(value = "MATCH (User {userName: 'userName'})-[:liked]->(pp) Return pp")
    List<PostEntity> getUserPosts(String userName);
}