package com.greatlearning.service;

import java.util.List;

import com.greatlearning.model.Role;
import com.greatlearning.model.User;

public interface UserRoleService {

	void addRoleToUser(User user, Role role);

	void removeRoleFromUser(User user, Role role);

	List<Role> getUserRoles(Long userId);

}
