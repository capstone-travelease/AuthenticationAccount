package com.example.demo.DTO;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class AuthenticationRequestDTO {
    private String email;
    private String password;
}
