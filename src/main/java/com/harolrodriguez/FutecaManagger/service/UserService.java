package com.harolrodriguez.FutecaManagger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harolrodriguez.FutecaManagger.model.User;
import com.harolrodriguez.FutecaManagger.repository.UserRepository;
import com.harolrodriguez.FutecaManagger.service.IService.IUserService;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> listUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id){
        return userRepository.findById(id).orElse(null);

    }

    @Override
    public User register(User user){
        return userRepository.save(user);
    }

    @Override
    public boolean login(String username, String password){
        // mas logica
        //validar que exista el usuario con ese nombre
        //validar que ese usuario traiga la contraseña que envio el usuario
        return false;
    }
}
