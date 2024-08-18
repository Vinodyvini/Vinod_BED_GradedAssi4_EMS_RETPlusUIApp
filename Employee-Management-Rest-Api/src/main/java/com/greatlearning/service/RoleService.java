// RoleService.java
package com.greatlearning.service;

import java.util.List;

import com.greatlearning.model.Role;

public interface RoleService {
	Role saveRole(Role role);

	List<Role> getAllRoles();

	Role getRoleById(Long id);

	Role updateRole(Role role);

	boolean existsByName(String name);

	boolean existsByNameExcludingId(String name, Long id);

	void deleteRole(Long id);

}
