package com.sharmashivansh2907.SecurityAPp.SecurityApplication.service;



import com.sharmashivansh2907.SecurityAPp.SecurityApplication.DTO.PostDTO;

import java.util.List;
import java.util.Optional;

public interface PostService {

         List<PostDTO> getAllPosts();
         PostDTO createNewPost(PostDTO inputPost);
         Optional<PostDTO> getPostByID(Long ID);
         PostDTO updatePost(PostDTO inputPost, Long ID);
         Boolean deleteByID(Long ID);

}
