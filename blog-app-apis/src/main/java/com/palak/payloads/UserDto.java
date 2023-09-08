package com.palak.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;

	@NotEmpty(message = "Name must not be empty")
	@Size(min = 3, message = "Name must be more than 3 characters")
	private String name;

	@NotEmpty(message = "Email is required")
	@Email(message = "Email is not valid")
	private String email;

	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be min 3 & max 10 characters")
	private String password;

	@NotEmpty
	@Size(min = 3, message = "About must be more than 3 characters")
	private String about;
}
