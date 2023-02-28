package com.stl.EmployeeBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stl.EmployeeBackend.jwt.JwtUtils;
import com.stl.EmployeeBackend.model.Employee;
import com.stl.EmployeeBackend.service.EmployeeService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("Employees")
public class EmployeeController {
	

	private EmployeeService employeeService;
	
	@Autowired
    AuthenticationManager authmanage;
    
    @Autowired
    JwtUtils jwtUtils;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	//Create Employees
	@PostMapping("/add")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee){
		String pass = employee.getPassword();
		employee.setPassword(new BCryptPasswordEncoder().encode(pass));
		return new ResponseEntity<String>(employeeService.saveEmployee(employee),HttpStatus.CREATED);
	}

	//Get All Employees
	@GetMapping("/get")
	public List<Employee> getAllEmployees(){
		
		return employeeService.getAllEmployees();
	}

	//Get Employees by Id
	@GetMapping("/get/{email}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("email") String email){
		
		return new ResponseEntity<Employee> (employeeService.getEmployeebyId(email), HttpStatus.OK);
	}

	//Update Employee
	@PutMapping("/update/{email}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("email") String email,@RequestBody Employee employee){
		String pass = employee.getPassword();
		employee.setPassword(new BCryptPasswordEncoder().encode(pass));
		return new ResponseEntity<Employee> (employeeService.updateEmployee(employee, email),HttpStatus.OK);
	}

	//Delete Employee
	@DeleteMapping("/delete/{email}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("email") String email){
		employeeService.deleteEmployee(email);
		return new ResponseEntity<String> ("Employee Deleted Successfully!", HttpStatus.OK);

	}

	//	@GetMapping()
	//	public String getUsers(@RequestParam(value="page") int page, @RequestParam(value="limit") int limit) {
	//		return "get users was called with page = " + page + " and limit = " +limit;
	//	}
	
	
	//login
    @PostMapping("/authenticate")
    public String authDoctor(@RequestBody Employee employee){
        try {
            @SuppressWarnings("unused")
            
            Authentication authtoken=  authmanage.authenticate(
                    new UsernamePasswordAuthenticationToken(employee.getEmail(), employee.getPassword()));
            String token = jwtUtils.generateToken(employee.getEmail().toString());
            return token;
            
        } catch (Exception e) {
            // TODO: handle exception
            return "Login Failed";
        }
    }

}
