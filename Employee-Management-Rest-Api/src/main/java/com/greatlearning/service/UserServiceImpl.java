// UserServiceImpl.java
package com.greatlearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.model.User;
import com.greatlearning.repository.RoleRepository;
import com.greatlearning.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public boolean isAdmin(String username) {
		// Retrieve the user by username from the database
		User user = userRepository.findByUsername(username);
		if (user == null) {
			// User not found
			return false;
		}

		// Check if the user has the admin role
		return user.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN"));
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.orElse(null);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
