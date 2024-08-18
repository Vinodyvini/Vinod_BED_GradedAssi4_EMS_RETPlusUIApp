// EmployeeServiceImpl.java
package com.greatlearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.model.Employee;
import com.greatlearning.repository.EmployeeRepository;

import java.util.List;
//EmployeeServiceImpl.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Page<Employee> searchEmployeesByFirstName(String firstName, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return employeeRepository.findByFirstNameContainingIgnoreCase(firstName, pageable);
	}

	@Override
	public Page<Employee> getAllEmployeesSortedByFirstNameAsc(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return employeeRepository.findAllByOrderByFirstNameAsc(pageable);
	}

	@Override
	public Page<Employee> getAllEmployeesSortedByFirstNameDesc(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return employeeRepository.findAllByOrderByFirstNameDesc(pageable);
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Page<Employee> getAllEmployees(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return employeeRepository.findAll(pageable);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).orElse(null);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public List<Employee> searchEmployeesByFirstName(String firstName) {
		return employeeRepository.findByFirstName(firstName);
	}

	@Override
	public List<Employee> getAllEmployeesSorted(String order) {
		Sort sort = Sort.by(order.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, "firstName");
		return employeeRepository.findAll(sort);
	}
}
