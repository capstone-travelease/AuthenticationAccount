package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Data
public class UserResponeDTO {
    private Integer code;
    private Map data;
    private String message;
}
