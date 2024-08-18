package com.greatlearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.greatlearning.model.Employee;
import com.greatlearning.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class RestEmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/create")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
		// Check if mandatory fields are provided
		if (employee.getFirstName() == null || employee.getFirstName().trim().isEmpty()) {
			return ResponseEntity.badRequest().body("First name is required");
		}
		if (employee.getLastName() == null || employee.getLastName().trim().isEmpty()) {
			return ResponseEntity.badRequest().body("Last name is required");
		}
		if (employee.getEmail() == null || employee.getEmail().trim().isEmpty()) {
			return ResponseEntity.badRequest().body("Email is required");
		}

		// Save the employee
		employeeService.saveEmployee(employee);

		return ResponseEntity.status(HttpStatus.CREATED).body("Employee created successfully");
	}

	// Endpoint to get all employees
	@GetMapping("/list")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	// Endpoint to get employee details by ID
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		if (employee != null) {
			return ResponseEntity.ok(employee);
		} else {
			return ResponseEntity.notFound().build(); // Returns 404 if the employee is not found
		}

	}

	// Endpoint to update an existing employee by ID
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
		Employee existingEmployee = employeeService.getEmployeeById(id);

		if (existingEmployee != null) {
			// Update the fields of the existing employee
			existingEmployee.setFirstName(updatedEmployee.getFirstName());
			existingEmployee.setLastName(updatedEmployee.getLastName());
			existingEmployee.setEmail(updatedEmployee.getEmail());

			// Save the updated employee back to the database
			employeeService.updateEmployee(existingEmployee);

			return ResponseEntity.ok(existingEmployee); // Return the updated employee in the response body
		} else {
			return ResponseEntity.notFound().build(); // Return 404 if the employee is not found
		}
	}

	// Endpoint to delete an employee by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
		Employee employee = employeeService.getEmployeeById(id);

		if (employee != null) {
			employeeService.deleteEmployee(id);
			return ResponseEntity.ok("Deleted employee id - " + id); // Return confirmation message
		} else {
			return ResponseEntity.notFound().build(); // Return 404 if the employee is not found
		}
	}

	// Endpoint to search employees by first name
	@GetMapping("/search/{firstName}")
	public ResponseEntity<List<Employee>> searchEmployeesByFirstName(@PathVariable String firstName) {
		List<Employee> employees = employeeService.searchEmployeesByFirstName(firstName);

		if (employees.isEmpty()) {
			return ResponseEntity.notFound().build(); // Return 404 if no employees are found
		} else {
			return ResponseEntity.ok(employees); // Return the list of employees
		}
	}

	@GetMapping("/sort")
	public Page<Employee> sortEmployees(@RequestParam(defaultValue = "asc") String order,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Page<Employee> employeePage;
		if ("asc".equalsIgnoreCase(order)) {
			employeePage = employeeService.getAllEmployeesSortedByFirstNameAsc(page, size);
		} else {
			employeePage = employeeService.getAllEmployeesSortedByFirstNameDesc(page, size);
		}
		return employeePage;
	}
}