package com.stl.EmployeeBackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stl.EmployeeBackend.exception.ResourceNotFoundException;
import com.stl.EmployeeBackend.model.Employee;
import com.stl.EmployeeBackend.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}



	@Override
	public String saveEmployee(Employee employee) {
		if(!employeeRepository.existsById(employee.getEmail())) {
			employeeRepository.save(employee);
			
			return employee.getFirstname() + " Your Registration is successfull!";
		}
		else {
			System.out.println("Email id already exists!!");
			return "Email id already exists!!";
		}
	
		
	}



	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}



	@Override
	public Employee getEmployeebyId(String email) {
//		Optional<Employee> employee = employeeRepository.findById(email);
//		if(employee.isPresent()) {
//			employee.get();
//			return true;
//		}else {
//			return false;
//		}
		return employeeRepository.findById(email).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", email));
		
	}



	@Override
	public Employee updateEmployee(Employee employee, String email) {
		Employee existingEmployee = employeeRepository.findById(email).orElseThrow(() -> new ResourceNotFoundException("Employee","Id",email));
		existingEmployee.setFirstname(employee.getFirstname());
		existingEmployee.setLastname(employee.getLastname());
		existingEmployee.setEmail(employee.getEmail());
		existingEmployee.setPhonenumber(employee.getPhonenumber());
		existingEmployee.setPassword(employee.getPassword());
//		existingEmployee.setAddressline1(employee.getAddressline1());
//		existingEmployee.setAddressline2(employee.getAddressline2());
//		existingEmployee.setCity(employee.getCity());
//		existingEmployee.setState(employee.getState());
//		existingEmployee.setPassword(employee.getPassword());
//		existingEmployee.setConfirmpassword(employee.getConfirmpassword());
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}



	@Override
	public void deleteEmployee(String email) {
		employeeRepository.findById(email).orElseThrow(() -> new ResourceNotFoundException("Employee","Id",email));
		employeeRepository.deleteById(email);
	}

}



