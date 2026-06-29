package com.sharmashivansh2907.SecurityAPp.SecurityApplication.serviceImpl;


import com.sharmashivansh2907.SecurityAPp.SecurityApplication.DTO.PostDTO;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.Repo.PostRepo;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.entity.PostEntity;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.exception.ResourceNotFoundException;
import com.sharmashivansh2907.SecurityAPp.SecurityApplication.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;
    private final ModelMapper modelMapper;


    public void isExistPostByID(Long ID){
        boolean exist = postRepo.existsById(ID);
        if(!exist) throw new ResourceNotFoundException("Post not found "+ ID);
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost){
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        return modelMapper.map(postRepo.save(postEntity),PostDTO.class);
    }

    @Override
    public Optional<PostDTO> getPostByID(Long ID){
        isExistPostByID(ID);
        return postRepo.findById(ID)
                .map(postEntity -> modelMapper.map(postEntity,PostDTO.class));
    }

    @Override
    public List<PostDTO> getAllPosts(){
        return postRepo
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity,PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO updatePost(PostDTO inputPost , Long ID){
        PostEntity existingPost = postRepo.findById(ID).orElseThrow(() -> new ResourceNotFoundException("Post not found "+ ID));
        inputPost.setID(ID);
        modelMapper.map(inputPost,existingPost);
        PostEntity savePost  = postRepo.save(existingPost);
        return modelMapper.map(savePost,PostDTO.class);
    }

    @Override
    public Boolean deleteByID(Long ID){
        isExistPostByID(ID);
        postRepo.deleteById(ID);
        return true;
    }


}
