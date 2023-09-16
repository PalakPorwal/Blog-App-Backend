package com.palak.services;

import com.palak.payloads.LoginUser;
import com.palak.payloads.UserDto;
import java.util.*;

public interface UserService {

	UserDto registerNewUser(UserDto user);

	UserDto createUser(UserDto user);

	UserDto loginUser(LoginUser user);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	void deleteUser(Integer userId);
}
