package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResquestChangePasswordDTO {

    @NotNull
    private String oldPassword;

    @NotNull
    private String newPassword;
}
