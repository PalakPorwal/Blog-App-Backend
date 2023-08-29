package com.palak.payloads;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor

@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;
	
	@NotBlank
	@Size(min=4,message="Min size of category title is 4")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=10,message="Min size of category description is 10")
	private String categoryDescription;
}
