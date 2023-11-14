package com.palak.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.palak.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndIdNot(String email, int userId);
}
