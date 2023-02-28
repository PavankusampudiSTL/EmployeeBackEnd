package com.stl.EmployeeBackend.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.stl.EmployeeBackend.model.Employee;

@SuppressWarnings("serial")
public class Employeelog implements UserDetails{
    Employee employee = new Employee();
    
    public Employeelog(Employee employee){
        this.employee=employee;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return Collections.singleton(new SimpleGrantedAuthority("EMPLOYEE"));
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return employee.getPassword();
    }

    

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return employee.getEmail();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
