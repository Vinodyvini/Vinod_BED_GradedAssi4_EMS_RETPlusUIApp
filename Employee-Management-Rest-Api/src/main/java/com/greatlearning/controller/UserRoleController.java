package com.greatlearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.greatlearning.model.Role;
import com.greatlearning.model.User;
import com.greatlearning.service.RoleService;
import com.greatlearning.service.UserRoleService;
import com.greatlearning.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/userRoles")
public class UserRoleController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRoleService userRoleService;

	@GetMapping("/addRole/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addRoleForm(@PathVariable Long userId, Model model) {
		User user = userService.getUserById(userId);
		List<Role> roles = roleService.getAllRoles();
		List<Role> assignedRoles = userRoleService.getUserRoles(userId);
		// String assignedRole = userService.getAssignedRoleName(userId);
		model.addAttribute("userId", userId);
		model.addAttribute("roles", roles);
		model.addAttribute("assignedRoles", assignedRoles);
		// model.addAttribute("assignedRole", assignedRole);
		return "userRoles/addUserRoleForm";
	}

//    @GetMapping("/addRole/{userId}")
//    public String showAddRoleForm(@PathVariable("userId") Long userId, Model model) {
//        User user = userService.getUserById(userId);
//        List<Role> roles = roleService.getAllRoles();
//        model.addAttribute("user", user);
//        model.addAttribute("roles", roles);
//        return "addUserRoleForm";
//    }

	@PostMapping("/addUserRole")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addRoleToUser(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId) {
		User user = userService.getUserById(userId);
		Role role = roleService.getRoleById(roleId);
		userRoleService.addRoleToUser(user, role);
		return "redirect:/userRoles/addRole/" + userId;
	}

//    @PostMapping("create")
//    public String createRole(@RequestParam("userIdInRoleForm") Long userId,@ModelAttribute Role role) {
//        roleService.saveRole(role);
//        return "redirect:/userRoles/addRole/"+userId; // Redirect to role list
//    }
//    @PostMapping("create")
//    public String createRole(@RequestParam("userIdInRoleForm") Long userId, @RequestParam("roleName") String roleName) {
//        if (roleName == null || roleName.trim().isEmpty()) {
//            // Handle error: Role name is required
//        	return "redirect:/userRoles/addRole/" + userId+"?error=RoleNameRequired";
//            //return "redirect:/yourPage?error=RoleNameRequired";
//        }
//        
//        Role role = new Role();
//        role.setName(roleName);
//        roleService.saveRole(role);
//        return "redirect:/userRoles/addRole/" + userId;
//    }

//    @GetMapping("/removeUserRole")
//    public String showRemoveRoleForm(@RequestParam("userId") Long userId, Model model) {
//        User user = userService.getUserById(userId);
//        List<Role> userRoles = userRoleService.getUserRoles(userId);
//        model.addAttribute("user", user);
//        model.addAttribute("userRoles", userRoles);
//        return "removeUserRoleForm";
//    }

	@PostMapping("/removeUserRole")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String removeRoleFromUser(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId) {
		User user = userService.getUserById(userId);
		Role role = roleService.getRoleById(roleId);
		userRoleService.removeRoleFromUser(user, role);
		return "redirect:/userRoles/addRole/" + userId;
	}

}
