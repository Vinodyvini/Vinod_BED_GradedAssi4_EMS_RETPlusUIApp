package com.greatlearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.greatlearning.model.Employee;
import com.greatlearning.model.User;
import com.greatlearning.service.EmployeeService;
import com.greatlearning.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/profile")
	public String viewUserProfile(Model model, Principal principal) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		List<Employee> employees = employeeService.getAllEmployees();
		model.addAttribute("employees", employees);
		model.addAttribute("user", user);
		return "users/profile"; // JSP view for displaying user profile
	}

	@GetMapping("/list")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String getAllUsers(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "users/userList"; // View name
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String getUserById(@PathVariable Long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "users/viewUser"; // View name
	}

	@GetMapping("/addUserForm")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String createUserForm() {
		return "users/addUserForm"; // View name
	}

	@PostMapping("/create")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String createUser(@ModelAttribute User user) {
		userService.saveUser(user);
		return "redirect:/users/list"; // Redirect to user list
	}

	@GetMapping("/editUserForm")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editUserForm(@RequestParam Long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "users/editUserForm"; // View name
	}

	@PostMapping("/update")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String updateUser(@ModelAttribute User user) {
		userService.updateUser(user);
		return "redirect:/users/list"; // Redirect to user list
	}

	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return "redirect:/users/list"; // Redirect to user list
	}

}
