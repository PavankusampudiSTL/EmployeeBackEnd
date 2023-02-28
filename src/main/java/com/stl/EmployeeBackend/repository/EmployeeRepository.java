package com.stl.EmployeeBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stl.EmployeeBackend.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
