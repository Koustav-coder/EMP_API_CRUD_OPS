package com.educon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educon.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
         // for unique emp city
	//Employee findByEmpcity(String emp_city);
	// for not unique city
	Optional<List<Employee>> findByEmpcity(String emp_city);
	Optional<List<Employee>> findByEmpageGreaterThan(int emp_age);
	Optional<List<Employee>> findByEmpnameLike(String emp_name);

}
