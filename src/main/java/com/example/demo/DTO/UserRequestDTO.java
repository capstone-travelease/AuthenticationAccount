package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;



@AllArgsConstructor
@Data
public class UserRequestDTO {

    private Integer id;
    private String email;
    private String name;
    private String password;
    private Integer phonenumber;
    private boolean gender;
    private String birthday;
    private Integer role;
}
