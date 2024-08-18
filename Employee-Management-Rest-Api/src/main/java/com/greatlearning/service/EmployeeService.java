// EmployeeService.java
package com.greatlearning.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.greatlearning.model.Employee;

public interface EmployeeService {
	Page<Employee> searchEmployeesByFirstName(String firstName, int page, int size);

	Page<Employee> getAllEmployeesSortedByFirstNameAsc(int page, int size);

	Page<Employee> getAllEmployeesSortedByFirstNameDesc(int page, int size);

	Employee saveEmployee(Employee employee);

	Page<Employee> getAllEmployees(int page, int size);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(Long id);

	Employee updateEmployee(Employee employee);

	void deleteEmployee(Long id);

	List<Employee> searchEmployeesByFirstName(String firstName);

	List<Employee> getAllEmployeesSorted(String order);
}
