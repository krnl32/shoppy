package com.krnl32.shoppy.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequestDTO {
    @NotBlank(message = "Email is Required")
    @Email(message = "Email Must Be Valid")
    private String email;

    @NotBlank(message = "Username is Required")
    @Size(min = 5, max = 30, message = "Username Must be Between 5-25 Characters")
    private String username;

    @NotBlank(message = "Password is Required")
    @Size(min = 8, max = 25, message = "Password Must be Between 8-25 Characters")
    private String password;
}
