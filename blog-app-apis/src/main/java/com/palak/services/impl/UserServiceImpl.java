package com.palak.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.palak.config.AppConstants;
import com.palak.entities.*;
import com.palak.payloads.*;
import com.palak.repositories.*;
import com.palak.services.UserService;
import com.palak.exceptions.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {

		// Check existing user
		boolean existUser = this.userRepo.findAll().stream()
				.filter((u) -> u.getName().equals(userDto.getName()) &&
						u.getEmail().equals(userDto.getEmail()))
				.findAny().isPresent();
		if (existUser)
			throw new AlreadyExistsException("User already exists with given details");

		User user = this.dtoToUser(userDto);

		// default user image
		user.setImage("default.png");
		// encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		// roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		if (userDto.getName() != null)
			user.setName(userDto.getName());

		if (userDto.getEmail() != null)
			user.setEmail(userDto.getEmail());

		if (userDto.getPassword() != null)
			user.setPassword(userDto.getPassword());

		if (userDto.getImage() != null)
			user.setImage(userDto.getImage());

		User updatedUser = this.userRepo.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
	}

	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		// user.setId(userDto.getId());
		// user.setName(userDto.getName());
		// user.setEmail(userDto.getEmail());
		// user.setAbout(userDto.getAbout());
		// user.setPassword(userDto.getPassword());

		return user;

	}

	private UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		// userDto.setId(user.getId());
		// userDto.setName(user.getName());
		// userDto.setEmail(user.getEmail());
		// userDto.setAbout(user.getAbout());
		// userDto.setPassword(user.getPassword());
		return userDto;
	}

	@Override
	public UserDto loginUser(LoginUser user) {

		User existUser = this.userRepo.findAll().stream().filter((u) -> u.getEmail().equals(user.getEmail()) &&
				u.getPassword().equals(user.getPassword())).findAny()
				.orElseThrow(() -> new AlreadyExistsException("User Details not found"));

		return this.userToDto(existUser);
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);

		// encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		// roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

		user.getRoles().add(role);
		User newUser = this.userRepo.save(user);
		return this.modelMapper.map(newUser, UserDto.class);
	}
}
