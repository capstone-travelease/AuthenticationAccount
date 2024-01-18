package com.example.demo.Repositories;

import com.example.demo.Enities.UserEnity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private static UserEnity createUserEntity(String email) {
        return new UserEnity(null, email, 43534534, "Khoi Nguyen", true, "4324234", null, null, 3);
    }

    @BeforeEach
    void setUp() {
        userRepository.save(createUserEntity( "khoinguyen5555@gmail.com"));
    }

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    void check_findByEmail_returnUser() {
        Optional<String> result = userRepository.findByEmail("khoinguyen5555@gmail.com");

        assertEquals("khoinguyen5555@gmail.com", result.get());
    }
    @Test
    void check_findByEmail_userNotExist() {
        Optional<String> result = userRepository.findByEmail("aaaa@gmail.com");

        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    void check_findByUserName_returnUserEntity() {
        Optional<UserEnity> result = userRepository.findByUserName("khoinguyen5555@gmail.com");

        assertThat(result.isEmpty()).isFalse();
    }

    @Test
    void check_findByName_returnMapUser() {
        Map<String, Object> result = userRepository.findByName("khoinguyen5555@gmail.com");

        System.out.println(result);
        assertThat(result.isEmpty()).isFalse();
    }

    @Test
    void check_findByUserId_returnString() {
        String result = userRepository.findByUserId(5);

        Optional<UserEnity> user = userRepository.findByUserName("khoinguyen5555@gmail.com");

        System.out.println(user.get().getPassword());
        System.out.println(("password: "+ result));
        assertThat(result).isNotNull();
    }

    @Test
    void check_updatePassword_returnNewPassword() {
        userRepository.updatePassword(1, "hah32da34");

        String newPassword = userRepository.findByUserId(1);

        System.out.println("new User: " + newPassword);
        assertThat(newPassword).isEqualTo("hah32da34");
    }
}