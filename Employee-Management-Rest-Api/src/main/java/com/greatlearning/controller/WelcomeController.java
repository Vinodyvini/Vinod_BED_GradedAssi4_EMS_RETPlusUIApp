package com.greatlearning.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greatlearning.service.UserService;

@Controller
public class WelcomeController {

	@Autowired
	private UserService userService;

	@RequestMapping("/welcome")
	public String displayWelcomePage(Principal principal) {
		// Get the currently logged-in user's username
		String username = principal.getName();

		// Determine the role of the logged-in user
		boolean isAdmin = userService.isAdmin(username);

		// Redirect based on user role
		if (isAdmin) {
			return "redirect:/users/list"; // Redirect to admin page
		} else {
			return "redirect:/users/profile"; // Redirect to user profile page
		}
	}

	@RequestMapping("/")
	public String defaultApplicationHomePage() {

		return "redirect:/login";
	}
}
