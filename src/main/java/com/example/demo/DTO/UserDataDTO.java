package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.Objects;

@Data
@AllArgsConstructor
public class UserDataDTO {
  private Integer code;
  private Map<String, Objects> data;
  private String message;
}
