package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@Data
public class AuthenticationResponeDTO {
    private Integer code;
    private Map data;
    private String message;


}
