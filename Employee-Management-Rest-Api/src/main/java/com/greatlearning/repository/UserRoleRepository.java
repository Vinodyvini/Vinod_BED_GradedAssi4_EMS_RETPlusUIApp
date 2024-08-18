package com.greatlearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greatlearning.model.Role;
import com.greatlearning.model.User;
import com.greatlearning.model.UserRole;
import com.greatlearning.model.UserRoleId;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
	void deleteByUserAndRole(User user, Role role);

	@Query("SELECT ur.role FROM UserRole ur WHERE ur.user.id = :userId")
	List<Role> findRolesByUserId(Long userId);
}
