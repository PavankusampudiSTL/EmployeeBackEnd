package com.stl.EmployeeBackend.service;

import java.util.List;

import com.stl.EmployeeBackend.model.Employee;


public interface EmployeeService {
	String saveEmployee(Employee employee);
	List<Employee> getAllEmployees();
	Employee getEmployeebyId(String email);
	Employee updateEmployee(Employee employee, String email);
	void deleteEmployee(String email);
}
