package com.palak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.palak.entities.User;

public interface UserRepo extends JpaRepository<User,Integer>{

	
}
