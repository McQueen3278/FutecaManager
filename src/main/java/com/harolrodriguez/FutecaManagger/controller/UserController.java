package com.harolrodriguez.FutecaManagger.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.harolrodriguez.FutecaManagger.DTO.UserRegisterDTO;
import com.harolrodriguez.FutecaManagger.service.UserService;

@RestController
@RequestMapping("/futecaManager/v1/auth")
public class UserController {
    @Autowired
    UserService userService;
     /**
      * Metodo para listar usuarios 
      * @return ResponseEntitty con las diferentes respustas
      */
    @GetMapping()
    public ResponseEntity<?> getMethodName() {
        Map<String, Object> res = new HashMap<>();
        try{
            return ResponseEntity.ok().body(userService.listUsers());
        }catch(CannotCreateTransactionException e){
            res.put("message", "Erorr al momento de conectarse a la base de datos");
            res.put("Error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);
        }catch(DataAccessException e){
            res.put("message", "Error al momento de consultar a la base de datos");
            res.put("Error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);

        }catch(Exception e){
            res.put("message", "Error general de la base de datos");
            res.put("Error", e.getMessage());
            return ResponseEntity.status(503).body(res);
        }

    }

    @PostMapping("/register")
    public ResponseEntity<?> RegisterMethod(
      @RequestPart("profilePicture") MultipartFile profilePicture,
      @RequestBody UserRegisterDTO user  
    ){
        Map<String, Object> res = new HashMap<>();
        try {
            System.out.println(user);
            res.put("message", "Usario recibido correctamente");
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("message", "Error al guardar el usuario");
            res.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(res);
        }
    }
}
