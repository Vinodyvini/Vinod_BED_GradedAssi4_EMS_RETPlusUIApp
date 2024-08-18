package com.greatlearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.model.Employee;
import com.greatlearning.service.EmployeeService;

import java.security.Principal;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/list")
	public String getAllEmployees(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "2") int size, Model model) {
		Page<Employee> employeePage = employeeService.getAllEmployees(page, size);
		model.addAttribute("employees", employeePage.getContent());
		model.addAttribute("totalPages", employeePage.getTotalPages());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalElements", employeePage.getTotalElements());
		model.addAttribute("size", size);

		int startRecord = page * size + 1;
		int endRecord = Math.min((page + 1) * size, (int) employeePage.getTotalElements());
		model.addAttribute("startRecord", startRecord);
		model.addAttribute("endRecord", endRecord);

		return "employees/employeesList"; // View name
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String getEmployeeById(@PathVariable Long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "employees/employeeDetails"; // View name
	}

	@GetMapping("/addEmployeeForm")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String createEmployeeForm() {
		return "employees/addEmployeeForm"; // View name
	}

	@PostMapping("/createEmployee")
	public String createEmployee(@ModelAttribute Employee employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/employees/list"; // Redirect to employee list
	}

	@GetMapping("/editEmployeeForm")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editEmployeeForm(@RequestParam Long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "employees/editEmployeeForm"; // View name
	}

	@PostMapping("/updateEmployee")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String updateEmployee(@ModelAttribute Employee employee) {
		employeeService.updateEmployee(employee);
		return "redirect:/employees/list"; // Redirect to employee list
	}

	@GetMapping("/viewEmployee")
	public String viewEmployee(@RequestParam("id") Long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "employees/viewEmployee";
	}

	@GetMapping("/deleteEmployee")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteEmployee(@RequestParam Long id) {
		employeeService.deleteEmployee(id);
		return "redirect:/employees/list"; // Redirect to employee list
	}

	@GetMapping("/search")
	public String searchEmployees(@RequestParam(defaultValue = "") String firstName,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
		Page<Employee> employeePage = employeeService.searchEmployeesByFirstName(firstName, page, size);
		model.addAttribute("employees", employeePage.getContent());
		model.addAttribute("totalPages", employeePage.getTotalPages());
		model.addAttribute("currentPage", page);
		model.addAttribute("firstName", firstName);
		return "employees/employeeSearch";
	}

	@GetMapping("/sort")
	public String sortEmployees(@RequestParam(defaultValue = "asc") String order,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
		Page<Employee> employeePage;
		if ("asc".equals(order)) {
			employeePage = employeeService.getAllEmployeesSortedByFirstNameAsc(page, size);
		} else {
			employeePage = employeeService.getAllEmployeesSortedByFirstNameDesc(page, size);
		}
		model.addAttribute("employees", employeePage.getContent());
		model.addAttribute("totalPages", employeePage.getTotalPages());
		model.addAttribute("currentPage", page);
		model.addAttribute("order", order);
		return "employees/employeeSort";
	}

	@RequestMapping(value = "/error/403")
	public ModelAndView handleAccessDeniedError(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page!");
		}

		model.setViewName("authorization-error-403");
		return model;
	}
}
