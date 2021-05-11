package com.jordan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordan.customexception.BusinessException;
import com.jordan.customexception.EmptyInputException;
import com.jordan.entity.Employee;
import com.jordan.repos.EmployeeCrudRepo;

@Service
public class EmployeeService implements IEmployeeService{
	
	@Autowired
	private EmployeeCrudRepo empCrud;

	@Override
	public Employee addEmployee(Employee employee) {
		if(employee.getName().length()==0 || employee.getName().isEmpty()) 
			throw new EmptyInputException("601","Invalid name passed, cannot save it");
			return empCrud.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> empList=  empCrud.findAll();
		if(empList.isEmpty()) throw new BusinessException("604", "No Employee list found");

			return empList; 
		
	}

	@Override
	public Employee getEmployeeById(Long id) {
		Employee employee = empCrud.findById(id).get();
			return employee;

		
		
	}

	@Override
	public Employee deleteEmployee(Long id) {
			Employee removedEmp= getEmployeeById(id);
			empCrud.deleteById(id);
			return removedEmp;


	}

	@Override
	public void updateEmployee(Long id, String name) {
		if(id.equals(null)|| name.equals(null)) 
			throw new EmptyInputException("601","Invalid Id or Name passed, cannot save it");
			empCrud.saveEmployee(id,name);

		
	}




}
