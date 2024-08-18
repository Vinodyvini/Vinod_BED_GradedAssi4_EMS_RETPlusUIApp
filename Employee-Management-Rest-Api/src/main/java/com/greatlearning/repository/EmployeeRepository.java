// EmployeeRepository.java
package com.greatlearning.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByFirstName(String firstName);

	Page<Employee> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);

	Page<Employee> findAllByOrderByFirstNameAsc(Pageable pageable);

	Page<Employee> findAllByOrderByFirstNameDesc(Pageable pageable);
}
