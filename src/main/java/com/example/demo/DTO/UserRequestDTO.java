package com.example.demo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class UserRequestDTO {


    private Integer id;

    @NotNull
    @Email
    private String email;

    @NotNull

    private String name;

    @NotNull
    private String password;

    @NotNull
    private Integer phonenumber;

    @NotNull
    private boolean gender;
    private String birthday;
    private Integer role;
}
