package com.palak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.palak.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
