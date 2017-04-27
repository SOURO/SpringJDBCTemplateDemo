package com.souro.SpringJDBCTemplateDemo.employeeDaoImpl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.souro.SpringJDBCTemplateDemo.employee.Employee;
import com.souro.SpringJDBCTemplateDemo.employeeDao.EmployeeDao;

public class EmployeeDaoImpl implements EmployeeDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insertEmployee(Employee employee) {
		String insertQuery = "INSERT INTO SOUROTB (EMPID, NAME) VALUES (?, ?)";
		Object[] queryParams = new Object[] { employee.getEmpId(),
				employee.getName() };
		jdbcTemplate.update(insertQuery, queryParams);
	}

	public Employee getEmployeebyEmpId(int empId) {
		String retrieveQuery = "SELECT * FROM SOUROTB WHERE EMPID = ?";
		Object[] queryParams = new Object[] { empId };
		Employee employee = jdbcTemplate.queryForObject(retrieveQuery,
				queryParams,
				new BeanPropertyRowMapper<Employee>(Employee.class));
		return employee;
	}

	public void deleteEmployeebyEmpId(int empId) {
		String deleteQuery = "DELETE FROM SOUROTB WHERE EMPID=?";
		Object[] queryParams = new Object[] { empId };
		jdbcTemplate.update(deleteQuery, queryParams);
	}
}
