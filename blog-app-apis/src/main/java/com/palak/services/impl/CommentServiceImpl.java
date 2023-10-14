package com.palak.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palak.entities.Comment;
import com.palak.entities.Post;
import com.palak.exceptions.ResourceNotFoundException;
import com.palak.payloads.CommentDto;
import com.palak.services.CommentService;
import com.palak.repositories.*;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

		Comment comment = this.modelMapper.map(commentDto, Comment.class);

		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment com = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Commment", "CommentId", commentId));

		this.commentRepo.delete(com);
	}

	@Override
	public void deleteCommentByPostId(int postId) {
		for (Comment cmt : commentRepo.findAll())
			if (cmt.getPost().getPostId() == postId)
				deleteComment(cmt.getId());
	}
}
