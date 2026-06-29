package com.sharmashivansh2907.SecurityAPp.SecurityApplication.Repo;

import com.sharmashivansh2907.SecurityAPp.SecurityApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
