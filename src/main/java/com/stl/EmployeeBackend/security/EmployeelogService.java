package com.stl.EmployeeBackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stl.EmployeeBackend.model.Employee;
import com.stl.EmployeeBackend.repository.EmployeeRepository;

@Service
public class EmployeelogService implements UserDetailsService{
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Employee employee = employeeRepository.findById(username).get();
        if(employee == null) {
            return null;
        }
        
        return new Employeelog(employee);
    }
    
}
