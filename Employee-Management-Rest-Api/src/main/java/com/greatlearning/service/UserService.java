// UserService.java
package com.greatlearning.service;

import java.util.List;

import com.greatlearning.model.User;

public interface UserService {
	User findByUsername(String username);

	boolean isAdmin(String username);

	User saveUser(User user);

	List<User> getAllUsers();

	User getUserById(Long id);

	User updateUser(User user);

	void deleteUser(Long id);
}
