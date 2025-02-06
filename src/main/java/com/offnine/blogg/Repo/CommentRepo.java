package com.offnine.blogg.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offnine.blogg.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment ,Integer>{



}
