package com.jordan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordan.customexception.BusinessException;
import com.jordan.entity.Employee;
import com.jordan.repos.EmployeeCrudRepo;

@Service
public class EmployeeService implements IEmployeeService{
	
	@Autowired
	private EmployeeCrudRepo empCrud;

	@Override
	public Employee addEmployee(Employee employee) {
		if(employee.getName().length()==0 || employee.getName().isEmpty()) 
			throw new BusinessException("601","Invalid name passed, cannot save it");
		try {
			return empCrud.save(employee);
		}catch(IllegalArgumentException e) {
			throw new BusinessException("602","user name is missing or wrong, cannot save it");
		}catch(Exception e) {
			throw new BusinessException("603","Somethin went wrong during saving");
		}
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> empList=  empCrud.findAll();
		if(empList.isEmpty()) throw new BusinessException("604", "No Employee list found");
		try {
			return empList; 
		}catch(Exception e) {
			throw new BusinessException("603","Somethin went wrong, while retriving list");
		}
		
	}

	@Override
	public Employee getEmployeeById(Long id) {
		
		Employee employee = empCrud.findById(id).get();
		if(employee.equals(null))
			throw new BusinessException("604","No Employee found, while retriving employee");
		try {
			return employee;
		}catch(IllegalArgumentException e) {
			throw new BusinessException("602","Invalid id passed, for retrival, while retriving employee");
		}catch(Exception e) {
			throw new BusinessException("603","Somethin went wrong, while retriving employee");
		}
		
		
		
	}

	@Override
	public Employee deleteEmployee(Long id) {
		
		try {
			Employee removedEmp= getEmployeeById(id);
			empCrud.deleteById(id);
			return removedEmp;
			
		}catch(IllegalArgumentException e) {
			throw new BusinessException("602","Invalid Id passed, for deletion");
		}catch(Exception e) {
			throw new BusinessException("603","Somethin went wrong, unable to delete");
		}

	}

	@Override
	public void updateEmployee(Long id, String name) {
		try {
			empCrud.saveEmployee(id,name);
		}catch(IllegalArgumentException e) {
			throw new BusinessException("602","user id, name is missing or wrong, for updation");
		}catch(Exception e) {
			throw new BusinessException("603","Somethin went wrong, while upadating");
		}
		
	}




}
