package com.greatlearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.model.Role;
import com.greatlearning.model.User;
import com.greatlearning.model.UserRole;
import com.greatlearning.repository.UserRoleRepository;

import jakarta.transaction.Transactional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public void addRoleToUser(User user, Role role) {
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		userRoleRepository.save(userRole);
	}

	@Transactional
	@Override
	public void removeRoleFromUser(User user, Role role) {
		userRoleRepository.deleteByUserAndRole(user, role);
	}

	@Override
	public List<Role> getUserRoles(Long userId) {
		return userRoleRepository.findRolesByUserId(userId);
	}
}
