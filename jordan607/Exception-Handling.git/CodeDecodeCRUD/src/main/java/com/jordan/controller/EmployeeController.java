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
		try {
			Employee saveEmployee = empSer.addEmployee(employee);
			return new ResponseEntity<Employee>(saveEmployee, HttpStatus.CREATED);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			ControllerException ce = new ControllerException("701","Something went wrong in controller while saving");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployees(){
//		try {
			List<Employee> allEmployees = empSer.getAllEmployee();
			return new ResponseEntity<List<Employee>>(allEmployees, HttpStatus.OK);
//		} catch (BusinessException e) {
//			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
//			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
//		}catch(Exception e) {
//			ControllerException ce = new ControllerException("701","Something went wrong in controller while saving");
//			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
//		}
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable ("id") Long id){
		
		try {
			Employee employee = empSer.getEmployeeById(id);
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			ControllerException ce = new ControllerException("701","Something went wrong in controller while saving");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("ET/{id}")
		public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
			
			try {
				Employee exEmp = empSer.deleteEmployee(id);
				return new ResponseEntity<Employee>(exEmp, HttpStatus.ACCEPTED);
			} catch (BusinessException e) {
				ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
				return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
			}catch(Exception e) {
				ControllerException ce = new ControllerException("701","Something went wrong in controller while saving");
				return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
			}
			
		}
	
	
	@PutMapping("change/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee){
		
		try {
			empSer.updateEmployee(id, employee.getName());
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			ControllerException ce = new ControllerException("701","Something went wrong in controller while saving");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	
}
