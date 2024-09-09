package com.harolrodriguez.FutecaManagger.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterDTO {
    @NotBlank(message = "El nombre no puede estar vacio")
    private String name;
    @NotBlank(message = "El apellido no puede ir vacio")
    private String surname;
    @NotBlank(message = "El nombre de usuario no puede ir vacio")
    private String username;
    @Email
    @NotBlank(message = "El correo no puede ir vacio")
    private String email;
    @NotBlank(message = "La password no puede ir vacia")
    private String password;
}
