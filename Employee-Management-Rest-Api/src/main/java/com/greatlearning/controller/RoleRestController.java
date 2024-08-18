package com.greatlearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.greatlearning.model.Role;
import com.greatlearning.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleRestController {

	@Autowired
	private RoleService roleService;

	@PostMapping("/{userId}/createRole")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> addRole(@PathVariable Long userId, @RequestBody Role role) {
		// Check if role name is provided
		if (role.getName() == null || role.getName().trim().isEmpty()) {
			return ResponseEntity.badRequest().body("Role name is required");
		}

		// Check if role name is unique
		if (roleService.existsByName(role.getName())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Role name already exists");
		}

		// Save the role
		roleService.saveRole(role);

		// Return success response
		return ResponseEntity.ok("Role created successfully");
	}
}