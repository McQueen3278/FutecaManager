package com.harolrodriguez.FutecaManagger.service.IService;

import java.util.List;

import com.harolrodriguez.FutecaManagger.model.User;

public interface IUserService {
    List<User> listUsers();

    User getUser(Long id);

    User register (User user);

    boolean login(String username, String password);
}

