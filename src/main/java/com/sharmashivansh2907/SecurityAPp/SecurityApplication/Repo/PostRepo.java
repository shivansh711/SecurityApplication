package com.sharmashivansh2907.SecurityAPp.SecurityApplication.Repo;


import com.sharmashivansh2907.SecurityAPp.SecurityApplication.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<PostEntity, Long> {
}
