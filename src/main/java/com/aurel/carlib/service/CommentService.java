package com.aurel.carlib.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurel.carlib.model.Comment;
import com.aurel.carlib.repository.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Iterable<Comment> getComments(){
        return commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(int id){
        return commentRepository.findById(id);
    }

    public Iterable<Comment> getCommentsByContentLike(String content){
        return commentRepository.findByContentLike(content);
    }

    public Comment saveComment(Comment comment){
        return commentRepository.save(comment);
    }

    public void deleteCommentById(int id){
        commentRepository.deleteById(id);
    }
    
}
