package com.example.demo.Repositories;

import com.example.demo.Enities.UserEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEnity,Integer> {

    @Query(value = "SELECT email FROM Users WHERE Users.email = ?1",nativeQuery = true)
    Optional<String> findByEmail(String email);

    @Query(value = "SELECT * FROM Users WHERE Users.email = ?1",nativeQuery = true)
    Optional<UserEnity> findByUserName(String email);

    @Query(value = "SELECT user_id FROM Users WHERE Users.email = ?1",nativeQuery = true)
    String findByName(String email);
}
