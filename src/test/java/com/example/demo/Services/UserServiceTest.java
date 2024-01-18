package com.example.demo.Services;

import com.example.demo.DTO.ResquestChangePasswordDTO;
import com.example.demo.DTO.UserRequestDTO;
import com.example.demo.Enities.UserEnity;
import com.example.demo.Repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    UserRequestDTO userRequestDTO;

    @BeforeEach
    void setUp() {
        userRequestDTO = new UserRequestDTO(
                5,
                "khoinguyen000@gmail.com",
                "Nguyen",
                "n32hif2j3",
                3432432,
                true,
                null
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        userRequestDTO = null;
        userRepository.deleteAll();
    }



    @Test
    @DisplayName("check_signUp_successfully")
    void check_signUpService_successfully() {
        Map<String, String> expected = userService.SignUpService(userRequestDTO);

        System.out.println(expected);
        assertThat(expected.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("check_signUp_emailInvalid")
    void check_signUpService_emailInvalid() {
        UserRequestDTO invalidEmailRequest  = new UserRequestDTO(
                5,
                "innuendoing",
                "Nguyen",
                "n32hif2j3",
                3432432,
                true,
                null
        );

        Map<String, String> expected = userService.SignUpService(invalidEmailRequest);

        System.out.println(expected);
        assertThat(expected.isEmpty()).isFalse();
    }


    @Test
    @DisplayName("check_signUp_emailExist")
    void check_signUpService_emailExist() {
        when(userRepository.findByEmail("khoinguyen000@gmail.com")).thenReturn(Optional.of("khoinguyen000@gmail.com"));

        Map<String, String> expected = userService.SignUpService(userRequestDTO);

        System.out.println(expected);
        assertThat(expected.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("check_updatePassword_successfully")
    void check_changePassword_successfully() {
        String encodedOldPassword = new BCryptPasswordEncoder().encode("n32hif2j3");
        UserEnity userEnity = new UserEnity(
                5,
                "khoinguyen000@gmail.com",
                43534534,
                "Khoi Nguyen",
                true,
                encodedOldPassword,
                null,
                null,
                3
        );
        when(userRepository.findByUserId(5)).thenReturn(userEnity.getPassword());

        ResquestChangePasswordDTO resquestChangePasswordDTO = new ResquestChangePasswordDTO("n32hif2j3", "h4h4h4h4");
        Boolean expected = userService.ChangePassword(5, resquestChangePasswordDTO);
        assertThat(expected).isTrue();
        verify(userRepository).updatePassword(eq(5), anyString());
    }

    @Test
    @DisplayName("check_updatePassword_wrongPassword")
    void check_changePassword_wrongPassword() {
        String encodedOldPassword = new BCryptPasswordEncoder().encode("n32hif2j3");
        UserEnity userEnity = new UserEnity(
                5,
                "khoinguyen000@gmail.com",
                43534534,
                "Khoi Nguyen",
                true,
                encodedOldPassword,
                null,
                null,
                3
        );
        when(userRepository.findByUserId(5)).thenReturn(userEnity.getPassword());

        ResquestChangePasswordDTO resquestChangePasswordDTO = new ResquestChangePasswordDTO("n32hif", "h4h4h4h4");
        Boolean expected = userService.ChangePassword(5, resquestChangePasswordDTO);

        assertThat(expected).isFalse();
        verify(userRepository, never()).updatePassword(anyInt(), anyString());
    }

    @Test
    @DisplayName("check_updatePassword_userNotExist")
    void check_changePassword_userNotExist() {
        String encodedOldPassword = new BCryptPasswordEncoder().encode("n32hif2j3");
        UserEnity userEnity = new UserEnity(
                5,
                "khoinguyen000@gmail.com",
                43534534,
                "Khoi Nguyen",
                true,
                encodedOldPassword,
                null,
                null,
                3
        );
        when(userRepository.findByUserId(5)).thenReturn(null);

        ResquestChangePasswordDTO resquestChangePasswordDTO = new ResquestChangePasswordDTO("n32hif", "h4h4h4h4");
        Boolean expected = userService.ChangePassword(5, resquestChangePasswordDTO);

        assertThat(expected).isFalse();
        verify(userRepository, never()).updatePassword(anyInt(), anyString());
    }

}