package com.greatlearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greatlearning.model.User;
import com.greatlearning.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDtlServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		List<String> roles = userRepository.getUserAuthorities(user.getId());
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return org.springframework.security.core.userdetails.User.builder().username(username)
				.password(user.getPassword()).authorities(authorities).accountLocked(false).build();
	}
}
