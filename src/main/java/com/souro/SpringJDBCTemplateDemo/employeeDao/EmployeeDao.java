package com.souro.SpringJDBCTemplateDemo.employeeDao;

import com.souro.SpringJDBCTemplateDemo.employee.Employee;

public interface EmployeeDao {
	public void insertEmployee(Employee employee);
	public Employee getEmployeebyEmpId(int empId);
	public void deleteEmployeebyEmpId(int empId);
}
