// RoleRepository.java
package com.greatlearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	boolean existsByName(String name);

	boolean existsByNameAndIdNot(String name, Long id);
}
