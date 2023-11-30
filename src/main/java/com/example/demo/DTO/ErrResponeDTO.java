package com.example.demo.DTO;



import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrResponeDTO {
    private Integer code;
    private String message;
}
