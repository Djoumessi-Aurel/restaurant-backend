package com.aurel.carlib.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aurel.carlib.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

    public Iterable<Comment> findByContentLike(String content);
    
}
