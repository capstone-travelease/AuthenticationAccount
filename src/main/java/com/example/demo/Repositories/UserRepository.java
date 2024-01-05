package com.example.demo.Repositories;

import com.example.demo.Enities.UserEnity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEnity,Integer> {

    @Query(value = "SELECT email FROM Users WHERE Users.email = ?1",nativeQuery = true)
    Optional<String> findByEmail(String email);

    @Query(value = "SELECT * FROM Users WHERE Users.email = ?1",nativeQuery = true)
    Optional<UserEnity> findByUserName(String email);

    @Query(value = "SELECT user_id,password FROM Users WHERE Users.email = ?1",nativeQuery = true)
    Map<String,Object> findByName(String email);

    @Query(value = "SELECT password FROM Users WHERE Users.user_id = ?1",nativeQuery = true)
    String findByUserId(Integer iduser);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE public.users\n" +
            "\tSET password=?2\n" +
            "\tWHERE users.user_id=?1",nativeQuery = true)
    void updatePassword(Integer iduser,String newPassword);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE public.users\n" +
            "\tSET password=?2\n" +
            "\tWHERE users.email=?1",nativeQuery = true)
    void forgotPassword(String email, String password);
}
