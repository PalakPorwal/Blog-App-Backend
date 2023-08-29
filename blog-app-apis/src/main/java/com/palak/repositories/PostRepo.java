package com.palak.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.palak.entities.Category;
import com.palak.entities.Post;
import com.palak.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);

	List<Post> findByTitleContaining(String title);
}
