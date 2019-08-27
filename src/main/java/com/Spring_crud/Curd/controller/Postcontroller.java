package com.Spring_crud.Curd.controller;


import com.Spring_crud.Curd.model.Post;
import com.Spring_crud.Curd.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
public class Postcontroller {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable){

        return postRepository.findAll(pageable);
    }

    @PostMapping("/posts")
    public Post cretePost(@Valid @RequestBody Post post){
        return postRepository.save(post);
    }
    //update Mapping
    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) throws Exception {
        return postRepository.findById(postId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setDescription(postRequest.getDescription());
            post.setContent(postRequest.getContent());

            return postRepository.save(post);
        }).orElseThrow(() -> new Exception("cannot update"));
    }

    //Delete Post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) throws Exception {
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();

        }).orElseThrow(()->new Exception("Cannot delte"));
    }

}
