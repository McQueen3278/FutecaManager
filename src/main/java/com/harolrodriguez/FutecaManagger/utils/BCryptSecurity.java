package com.harolrodriguez.FutecaManagger.utils;

import org.springframework.stereotype.Component;

import at.favre.lib.crypto.bcrypt.BCrypt;

//Libreria para encriptar contraseñas
@Component
public class BCryptSecurity {

    // Metodo para encriptar la password

    //Metodo para verificar si coincide el texto plano con el hash (password encriptado)
    public String encodePassword(String password) {
        return  BCrypt.withDefaults().hashToString(11, password.toCharArray());

    }

    public boolean checkPassword(String password, String hashedPassword){
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
        return result.verified;
        
    }
}
