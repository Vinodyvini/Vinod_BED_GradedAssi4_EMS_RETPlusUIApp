// SecurityConfig.java
package com.greatlearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import com.greatlearning.service.UserDtlServiceImpl;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDtlServiceImpl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider ssrsDaoAuthenticationProvider() {

		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.requestMatchers("/welcome", "/", "/employees/list", "/employees/viewEmployee", "/users/profile")
				.hasAnyAuthority("USER", "ADMIN")
				.requestMatchers("/", "/employees/list", "/employees/{id}", "/employees/addEmployeeForm",
						"/employees/createEmployee", "/employees/editEmployeeForm", "/employees/updateEmployee",
						"/employees/deleteEmployee", "/users/list", "/users/{id}", "/users/addUserForm",
						"/users/create", "/users/editUserForm", "/users/update", "/users/delete/{id}",
						"/roles/{userId}/list", "/roles/{userId}/{id}", "/roles/{userId}/addRoleForm",
						"/roles/{userId}/create", "/userRoles/addRole/{userId}", "/userRoles/addUserRole",
						"/userRoles/removeUserRole", "/admin/users")
				.hasAuthority("ADMIN")
				// .requestMatchers("/employees/displayEmployeesForm_Update","/employees/delete").hasAuthority("ADMIN")
				.anyRequest().authenticated().and().formLogin().loginProcessingUrl("/login")
				.successForwardUrl("/welcome").permitAll().and().logout().logoutSuccessUrl("/login").permitAll().and()
				.exceptionHandling().accessDeniedPage("/employees/error/403").and().cors().and().csrf().disable();

		http.authenticationProvider(ssrsDaoAuthenticationProvider());
		return http.build();
	}

}