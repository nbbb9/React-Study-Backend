package com.studyreact.sidepj.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Posts, UUID> {
}