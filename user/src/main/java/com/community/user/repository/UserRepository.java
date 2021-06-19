package com.community.user.repository;

import com.community.user.entity.UserEntity;
import com.sun.istack.Nullable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<UserEntity,Long> {
    @Nullable
    UserEntity findUserByUserName(String username);

    @Query(value = "CREATE (n:User {userName: $userName, password: $password})")
    UserEntity createUser(String userName,String password);

}
