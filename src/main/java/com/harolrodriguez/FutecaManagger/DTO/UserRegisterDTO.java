package com.harolrodriguez.FutecaManagger.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String username;
    @Email
    @NotBlank
    private String email;
    @Email
    @NotBlank
    private String password;
}
