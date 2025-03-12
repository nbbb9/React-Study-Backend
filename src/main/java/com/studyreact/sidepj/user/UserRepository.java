package com.studyreact.sidepj.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    @Query("""
           SELECT u.email
           FROM User u
           WHERE u.name = :name
    """)
    String findEmailByName(@Param("name") String name);

    boolean existsByNameAndEmail(String name, String email);

    @Query("""
        SELECT u
        FROM User u
        WHERE u.id = :id
    """)
    Optional<User> findByUserId(String Id);
}