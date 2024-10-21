package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="employeenew")
@Entity
public class EmployeeEntity {
	@Id
	@Column(name="empidnew")
	private int empid;
	@Column(name="empnamenew")
	private String empname;
	@Column(name="salarynew")
	private int salary;
	@Column(name="deptnonew")
	private int deptno;
	
	public EmployeeEntity() {}
	public EmployeeEntity(int empid, String empname, int salary, int deptno) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.salary = salary;
		this.deptno = deptno;
	}
	@Override
	public String toString() {
		return "EmployeeEntity [empid=" + empid + ", empname=" + empname + ", salary=" + salary + ", deptno=" + deptno
				+ "]";
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	
}
