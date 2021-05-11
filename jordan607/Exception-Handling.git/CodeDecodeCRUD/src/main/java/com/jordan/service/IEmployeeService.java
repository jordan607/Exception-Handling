package com.jordan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.jordan.entity.Employee;

public interface IEmployeeService {

	public Employee addEmployee(Employee employee);

	public List<Employee> getAllEmployee();

	public Employee getEmployeeById(Long id);

	public Employee deleteEmployee(Long id);

	public void updateEmployee(Long id, String name);

}
