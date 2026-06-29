package com.sharmashivansh2907.SecurityAPp.SecurityApplication.controller;


import com.sharmashivansh2907.SecurityAPp.SecurityApplication.DTO.PostDTO;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class postController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping(path = "/{ID}")
    public ResponseEntity<PostDTO> getPostByID(@PathVariable Long ID){
        Optional<PostDTO> postDTO = postService.getPostByID(ID);
        return postDTO
                .map(postDTO1 -> ResponseEntity.ok(postDTO1))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/newPosts")
    public ResponseEntity<PostDTO> createNewPost(@RequestBody @Valid PostDTO inputPost){
        PostDTO postDTO = postService.createNewPost(inputPost);
        return new ResponseEntity<>(postDTO,HttpStatus.CREATED);

    }

    @PutMapping(path = "/updatedPost/{ID}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody @Valid PostDTO inputPost , @PathVariable Long ID){
        return ResponseEntity.ok(postService.updatePost(inputPost,ID));
    }

    @DeleteMapping(path = "/deletePost/{ID}")
    public ResponseEntity<Boolean> deletePostByID(@PathVariable Long ID){
        boolean gotDeleted = postService.deleteByID(ID);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

}
