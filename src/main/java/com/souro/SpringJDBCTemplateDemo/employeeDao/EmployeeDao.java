package com.souro.SpringJDBCTemplateDemo.employeeDao;

import java.util.List;

import com.souro.SpringJDBCTemplateDemo.employee.Employee;

public interface EmployeeDao {
	public void insertEmployee_opt1(Employee employee);
	public void insertEmployee_opt2(Employee employee);
	public void insertEmployee_opt3(Employee employee);
	public Boolean insertEmployee_opt4(Employee employee);
	public Object insertEmployee_opt5(Employee employee);
	
	public Employee getEmployeebyEmpId_opt1(int empId);
	public Employee getEmployeebyEmpId_opt2(int empId);
	public Employee getEmployeebyEmpId_opt3(int empId);
	public Employee getEmployeebyEmpId_opt4(int empId);
	
	public void deleteEmployeebyEmpId_opt1(int empId);
	public void deleteEmployeebyEmpId_opt2(int empId);
	public void deleteEmployeebyEmpId_opt3(int empId);
	public Boolean deleteEmployeebyEmpId_opt4(int empId);
	public void deleteEmployeebyEmpId_opt5(int empId);
}
