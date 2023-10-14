package com.palak.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.palak.entities.*;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	Page<Post> findByCategory(Category category, Pageable p);

	List<Post> findByTitleContaining(String title);
}
