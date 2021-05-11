package com.jordan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jordan.customexception.BusinessException;
import com.jordan.customexception.ControllerException;
import com.jordan.entity.Employee;
import com.jordan.service.IEmployeeService;

@RestController
@RequestMapping("/code")
public class EmployeeController {
	
	@Autowired
	private	IEmployeeService empSer;
	
	@PostMapping("/save")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee){
			Employee saveEmployee = empSer.addEmployee(employee);
			return new ResponseEntity<Employee>(saveEmployee, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployees(){
			List<Employee> allEmployees = empSer.getAllEmployee();
			return new ResponseEntity<List<Employee>>(allEmployees, HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable ("id") Long id){

			Employee employee = empSer.getEmployeeById(id);
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@DeleteMapping("ET/{id}")
		public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){

				Employee exEmp = empSer.deleteEmployee(id);
				return new ResponseEntity<Employee>(exEmp, HttpStatus.ACCEPTED);
			
		}
	
	
	@PutMapping("change/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee){

			empSer.updateEmployee(id, employee.getName());
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
}
