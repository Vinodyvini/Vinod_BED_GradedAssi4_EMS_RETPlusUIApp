package com.greatlearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.greatlearning.model.Role;
import com.greatlearning.model.User;
import com.greatlearning.service.RoleService;
import com.greatlearning.service.UserService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class RestUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        // Check if username is provided
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Username is required");
        }

        // Check if password is provided
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Password is required");
        }

        // Check if roles are provided and valid
        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            Role existingRole = roleService.getRoleById(role.getId());
            if (existingRole == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found with id: " + role.getId());
            }
            roles.add(existingRole);
        }
        user.setRoles(roles);

        // Save the user
        userService.saveUser(user);

        return ResponseEntity.ok("User created successfully");
    }
}