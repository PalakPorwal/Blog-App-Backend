package com.palak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.palak.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
