package com.palak.payloads;

import java.util.*;

import com.palak.entities.Category;
import com.palak.entities.User;
import com.palak.entities.Comment;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	
	private Integer postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;

	private Set<CommentDto> comments=new HashSet<>();
}
