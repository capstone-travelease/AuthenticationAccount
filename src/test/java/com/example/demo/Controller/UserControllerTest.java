package com.example.demo.Controller;

import com.example.demo.DTO.AuthenticationRequestDTO;
import com.example.demo.Services.AuthenticationService;
import com.example.demo.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.print.attribute.standard.Media;

import java.awt.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private  AuthenticationService authenticationService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void login() throws  Exception {
        AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO();
        when(authenticationService.authenticate(authenticationRequestDTO)).thenReturn(Map.of("token", "", "iduser", ""));

        ResultActions response = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authenticationRequestDTO))
        );

        response.andExpect(MockMvcResultMatchers.status().isOk());

    }
}