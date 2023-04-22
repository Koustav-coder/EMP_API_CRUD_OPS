package com.educon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long emp_id;     //column name emp_id

	@Column(name = "emp_name")
	private String empname;

	@Column(name = "emp_salary")
	private Float empsalary;

	@Column(name = "emp_age")
	private int empage;

	@Column(name = "emp_city")
	private String empcity;

	public Long getEmpid() {
		return emp_id;
	}

	public void setEmpid(Long empid) {
		this.emp_id = empid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public Float getEmpsalary() {
		return empsalary;
	}

	public void setEmpsalary(Float empsalary) {
		this.empsalary = empsalary;
	}

	public int getEmpage() {
		return empage;
	}

	public void setEmpage(int empage) {
		this.empage = empage;
	}

	public String getEmpcity() {
		return empcity;
	}

	public void setEmpcity(String empcity) {
		this.empcity = empcity;
	}

	public Employee(Long empid, String empname, Float empsalary, int empage, String empcity) {
		this.emp_id = empid;
		this.empname = empname;
		this.empsalary = empsalary;
		this.empage = empage;
		this.empcity = empcity;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + emp_id + ", empname=" + empname + ", empsalary=" + empsalary + ", empage=" + empage
				+ ", empcity=" + empcity + "]";
	}

	public Employee() {

	}
}
