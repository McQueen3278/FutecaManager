package com.harolrodriguez.FutecaManagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harolrodriguez.FutecaManagger.model.User;

public interface UserRepository extends JpaRepository <User, Long> {


}
