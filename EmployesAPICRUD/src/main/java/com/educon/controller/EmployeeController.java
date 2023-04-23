package com.educon.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.educon.model.Employee;
import com.educon.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@PostMapping("/employees")
	public String createNewEmployee(@RequestBody Employee employee) {
		employeeRepository.save(employee);
		return "Employee Created in database";
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> empList = new ArrayList<>();
		employeeRepository.findAll().forEach(empList::add);
		return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
	}

	@GetMapping("/employees/{empid}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long empid) {
		Optional<Employee> emp = employeeRepository.findById(empid);
		if (emp.isPresent()) {
			return new ResponseEntity<Employee>(emp.get(), HttpStatus.FOUND);
		} else {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/employees/{empid}")
	public String updateEmployeeById(@PathVariable long empid, @RequestBody Employee employee) {
		Optional<Employee> emp = employeeRepository.findById(empid);
		if (emp.isPresent()) {
			Employee existEmp = emp.get();
			existEmp.setEmpage(employee.getEmpage());
			existEmp.setEmpcity(employee.getEmpcity());
			existEmp.setEmpname(employee.getEmpname());
			existEmp.setEmpsalary(employee.getEmpsalary());
			employeeRepository.save(existEmp);
			return "Employee Details against Id " + empid + " updated";
		} else {
			return "Employee Details does not exist for empid " + empid;
		}
	}

	@DeleteMapping("/employees/{empid}")
	public String deleteEmployeeByEmpId(@PathVariable Long empid) {
		employeeRepository.deleteById(empid);
		return "Employee Deleted Successfully";
	}

	@DeleteMapping("/employees")
	public String deleteAllEmployee() {
		employeeRepository.deleteAll();
		return "Employee deleted Successfully..";
	}
        // if empcity is unique then only the following will work
// 	@GetMapping("/employees/empcity")
// 	public ResponseEntity<Employee> getEmployeeByempcity(@RequestParam("emp_city") String emp_city) {
// 		Employee emp = employeeRepository.findByEmpcity(emp_city);   //if city unique it will work
// 		return new ResponseEntity<Employee>(emp, HttpStatus.FOUND);
// 	}
	// if empcity is not unique 
        @GetMapping("/employees/empcity")
	public ResponseEntity<List<Employee>> getEmployeeByempcity(@RequestParam("emp_city") String emp_city) {
		Optional<List<Employee>> empList = employeeRepository.findByEmpcity(emp_city);
		return new ResponseEntity<List<Employee>>(empList.get(), HttpStatus.FOUND);
	}
	
	@GetMapping("/employee/employeeGreaterThan")
	public ResponseEntity<List<Employee>> getEmployeeGreaterThan(@RequestParam("emp_age") int emp_age){
		Optional<List<Employee>> empList = employeeRepository.findByEmpageGreaterThan(emp_age);
		return new ResponseEntity<List<Employee>>(empList.get(), HttpStatus.FOUND);
	}
	@GetMapping("/employee/empnamelike")
	public ResponseEntity<List<Employee>> getEmployeeNameLike(@RequestParam("emp_name") String emp_name) {
		Optional<List<Employee>> empList = employeeRepository.findByEmpnameLike("%" + emp_name + "%");
		return new ResponseEntity<List<Employee>>(empList.get(), HttpStatus.FOUND);
	}
}
