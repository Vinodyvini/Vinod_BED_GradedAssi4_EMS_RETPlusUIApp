// RoleServiceImpl.java
package com.greatlearning.service;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.model.Role;
import com.greatlearning.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Role getRoleById(Long id) {
		Optional<Role> optionalRole = roleRepository.findById(id);
		return optionalRole.orElse(null);
	}

	@Override
	public Role updateRole(Role role) {
		return roleRepository.save(role);
	}

	@Transactional
	public boolean existsByName(String name) {
		return roleRepository.existsByName(name);
	}

	@Transactional
	public boolean existsByNameExcludingId(String name, Long id) {
		return roleRepository.existsByNameAndIdNot(name, id);
	}

	@Override
	public void deleteRole(Long id) {
		roleRepository.deleteById(id);
	}
}
