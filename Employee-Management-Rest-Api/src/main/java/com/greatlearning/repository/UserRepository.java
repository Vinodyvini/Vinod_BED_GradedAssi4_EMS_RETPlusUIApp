// UserRepository.java
package com.greatlearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greatlearning.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	@Query("SELECT ur.role.name FROM UserRole ur WHERE ur.user.id = :userId")
	List<String> getUserAuthorities(Long userId);

}
