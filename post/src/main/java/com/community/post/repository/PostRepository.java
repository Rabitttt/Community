package com.community.post.repository;

import com.community.post.entity.PostEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends Neo4jRepository<PostEntity,Long> {
    //@Query(value = "Match (id,Post) Where id ='1' ",nativeQuery=true)
    //MATCH (uu:User {userName:"deneme"}) MATCH (pp:Post {description:"dadaf"}) CREATE (uu)-[:owner]->(pp)
    @Query(value = "MATCH (uu:User {userName:'emma'}) MATCH (pp:Post {description:'makale'}) CREATE (uu)-[:owner]->(pp)")
    void addOwnerToPost();
}