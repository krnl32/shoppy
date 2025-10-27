package com.krnl32.shoppy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserPatchRequestDTO {
    @Email(message = "Email Must Be Valid")
    private String email;

    @Size(min = 5, max = 30, message = "Username Must be Between 5-25 Characters")
    private String username;
}
