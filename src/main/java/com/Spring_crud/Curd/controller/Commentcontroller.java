package com.Spring_crud.Curd.controller;


import com.Spring_crud.Curd.model.Comment;
import com.Spring_crud.Curd.repository.CommentRepository;
import com.Spring_crud.Curd.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class Commentcontroller {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("posts/{postId}/comments")
    public Page<Comment> getallcommentbyPostId(@PathVariable(value = "postId") Long postId, Pageable pageable) throws Exception {

        if(!postRepository.existsById(postId)){
            throw new Exception("post not found!");
        }
        return commentRepository.findByPostId(postId, pageable);
    }

    @PostMapping("/comments/{postId}/posts")
    public Comment createComment(@PathVariable(value = "postId") Long postId,  @Valid @RequestBody Comment comment) throws Exception {
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(()-> new Exception("couldn't get message"));
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public Comment updateComment(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "commentId") Long commentId,
            @Valid @RequestBody Comment commentRequest) throws Exception {
        if(!postRepository.existsById(postId)){
            throw new Exception("not found");
        }
        return commentRepository.findById(commentId).map(comment -> {
            comment.setText(commentRequest.getText());
            return commentRepository.save(comment);
        }).orElseThrow(()->new Exception("cannot update"));
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")

    public ResponseEntity<?> deletecomment(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "commentId") Long commentId) throws Exception {
        if(!postRepository.existsById(postId)){
            throw new Exception("not found");
        }
        return commentRepository.findByIdAndPostId(commentId, postId).map(comment-> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new Exception("Connot delete"));
    }
}