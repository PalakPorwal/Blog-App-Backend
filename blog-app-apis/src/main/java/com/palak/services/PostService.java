package com.palak.services;

import java.util.List;

import com.palak.payloads.PostDto;
import com.palak.payloads.PostResponse;

public interface PostService {

	// create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// update
	PostDto updatePost(PostDto postDto, Integer postId);

	// delete
	void deletePostById(Integer postId);

	// get all posts
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	// get single post
	PostDto getPostById(Integer postId);

	// get all posts by category
	PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy,
			String sortDir);

	// get all posts by user
	List<PostDto> getPostsByUser(Integer userId);

	// search posts
	List<PostDto> searchPosts(String keyword);
}
