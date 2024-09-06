package com.harolrodriguez.FutecaManagger.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.harolrodriguez.FutecaManagger.DTO.UserLoginDTO;
import com.harolrodriguez.FutecaManagger.DTO.UserRegisterDTO;
import com.harolrodriguez.FutecaManagger.model.User;
import com.harolrodriguez.FutecaManagger.service.CloudinaryService;
import com.harolrodriguez.FutecaManagger.service.UserService;
import com.harolrodriguez.FutecaManagger.utils.BCryptSecurity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/futecaManager/v1/auth")
public class UserController {
    @Autowired

    UserService userService;
    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    BCryptSecurity bCryptSecurity;
     /**
      * Metodo para listar usuarios 
      * @return ResponseEntitty con las diferentes respustas
      */
      @GetMapping()
      public ResponseEntity<?> getMethodName() {
          Map<String, Object> res = new HashMap<>();
          try {
              return ResponseEntity.ok().body(userService.listUsers());
          } catch (CannotCreateTransactionException err) {
              res.put("message", "Error al momento de conectar con la DB");
              res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
              return ResponseEntity.status(503).body(res);
          } catch (DataAccessException err) {
              res.put("message", "Error al momento de consultar a la base de datos");
              res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
              return ResponseEntity.status(503).body(res);
          } catch (Exception err) {
              res.put("message", "Error general al obtener los datos");
              res.put("Error", err.getMessage());
              return ResponseEntity.internalServerError().body(res);
          }
      }
   
      @PostMapping("/register")
      public ResponseEntity<?> register(
              @RequestPart("profilePicture") MultipartFile profilePicture,
              @Valid @ModelAttribute UserRegisterDTO user,
              BindingResult result
      ) {
          Map<String, Object> res = new HashMap<>();
          if (result.hasErrors()) {
              List<String> errors = result.getFieldErrors()
                      .stream()
                      .map(error -> error.getDefaultMessage())
                      .collect(Collectors.toList());
                      res.put("message", "Error con las validaciones, por favor ingresar todos los campos");
                      res.put("Errores", errors);
                      return ResponseEntity.badRequest().body(res);
          }
          try {
              Map<String, Object> uploadResult = cloudinaryService.uploadProfilePicture(profilePicture, "profilesFuteca");
              String img = uploadResult.get("url").toString();
              Long id = null;
              User newUser = new User(
                  id,
                  user.getName(),
                  user.getSurname(),
                  user.getUsername(),
                  user.getEmail(),
                  user.getPassword(),
                  img
              );
              userService.register(newUser);
              res.put("message", "Usuario recibido correctamente");
              return ResponseEntity.ok(res);
          }catch(Exception err){
              res.put("message", "Error al guardar el usuario, intente de nuevo mas tarde");
              res.put("error", err.getMessage());
              return ResponseEntity.internalServerError().body(res);
          }
      }
      @PostMapping("/login")
      public ResponseEntity<?> login(@RequestBody UserLoginDTO user){
        Map<String, Object> res = new HashMap<>();
        try {
            if (userService.login(user.getUsername(), user.getPassword())) {
                res.put("message", "Usuario logeado correctamente");
                return ResponseEntity.ok(res);
            }else{
                res.put("message", "Credenciales invalidas");
                return ResponseEntity.status(401).body(res);
            }
        } catch (Exception e) {
            res.put("message", "Error general al iniciar sesion");
            res.put("error", e);
            return ResponseEntity.internalServerError().body(res);
        }
      }
  }
