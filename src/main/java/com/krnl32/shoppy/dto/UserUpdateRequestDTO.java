package com.krnl32.shoppy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateRequestDTO {
    @NotBlank(message = "Email is Required")
    @Email(message = "Email Must Be Valid")
    private String email;

    @NotBlank(message = "Username is Required")
    @Size(min = 5, max = 30, message = "Username Must be Between 5-25 Characters")
    private String username;
}
