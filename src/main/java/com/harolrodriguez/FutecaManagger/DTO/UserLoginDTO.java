package com.harolrodriguez.FutecaManagger.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class UserLoginDTO {
    @Email
    @NotBlank(message = "El nombre de usuario no puede ir vacio")
    private String username;
    @NotBlank(message = "La password no puede ir vacia")
    private String password;
}

