package com.souro.SpringJDBCTemplateDemo.employeeDaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.souro.SpringJDBCTemplateDemo.employee.Employee;
import com.souro.SpringJDBCTemplateDemo.employeeDao.EmployeeDao;

public class EmployeeDaoImpl implements EmployeeDao {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	/* Insert */
	public void insertEmployee_opt1(Employee employee) {
		String insertQuery = "INSERT INTO SOUROTB VALUES ("
				+ employee.getEmpId() + ",'" + employee.getName() + "')";
		jdbcTemplate.update(insertQuery);
	}

	public void insertEmployee_opt2(Employee employee) {
		String insertQuery = "INSERT INTO SOUROTB (EMPID, NAME) VALUES (?, ?)";
		jdbcTemplate.update(insertQuery, employee.getEmpId(),
				employee.getName());
	}

	public void insertEmployee_opt3(Employee employee) {
		String insertQuery = "INSERT INTO SOUROTB (EMPID, NAME) VALUES (?, ?)";
		Object[] queryParams = new Object[] { employee.getEmpId(),
				employee.getName() };
		jdbcTemplate.update(insertQuery, queryParams);
	}

	public Boolean insertEmployee_opt4(final Employee employee) {
		String insertQuery = "INSERT INTO SOUROTB (EMPID, NAME) VALUES (?, ?)";
		Boolean confirmation = jdbcTemplate.execute(insertQuery,
				new PreparedStatementCallback<Boolean>() {
					public Boolean doInPreparedStatement(
							PreparedStatement prepStmnt) throws SQLException,
							DataAccessException {
						prepStmnt.setInt(1, employee.getEmpId());
						prepStmnt.setString(2, employee.getName());
						return prepStmnt.execute();
					}
				});
		return confirmation;
	}

	public Object insertEmployee_opt5(Employee employee) {
		String insertQuery = "INSERT INTO SOUROTB VALUES (:EmpId, :Name)";
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("EmpId", employee.getEmpId());
		queryParams.put("Name", employee.getName());
		Object output = null;
		namedParameterJdbcTemplate.execute(insertQuery, queryParams,
				new PreparedStatementCallback<Object>() {
					public Object doInPreparedStatement(
							PreparedStatement prepStmnt) throws SQLException,
							DataAccessException {
						return prepStmnt.executeUpdate();
					}
				});
		return output;
	}

	/* Retrieve */
	public Employee getEmployeebyEmpId_opt1(int empId) {
		String retrieveQuery = "SELECT * FROM SOUROTB WHERE EMPID = ?";
		final Object[] queryParams = new Object[] { empId };
		Employee employee = jdbcTemplate.queryForObject  (retrieveQuery,
				queryParams, new RowMapper<Employee>() {
					public Employee mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Employee employee = new Employee();
						employee.setEmpId(rs.getInt(1));
						employee.setName(rs.getString(2));
						return employee;
					}
				});
		return employee;
	}

	public Employee getEmployeebyEmpId_opt2(final int empId) {
		String retrieveQuery = "SELECT * FROM SOUROTB WHERE EMPID = ?";

		Employee employee = jdbcTemplate.execute(retrieveQuery,
				new PreparedStatementCallback<Employee>() {
					public Employee doInPreparedStatement(
							PreparedStatement prepStmnt) throws SQLException,
							DataAccessException {

						prepStmnt.setInt(1, empId);
						ResultSet rs = prepStmnt.executeQuery();
						Employee employee = null;
						if (rs.next() == true) {
							employee = new Employee();
							employee.setEmpId(rs.getInt("EMPID"));
							employee.setName(rs.getString("NAME"));
						}

						return employee;

					}
				});
		return employee;
	}

	public Employee getEmployeebyEmpId_opt3(final int empId) {
		String retrieveQuery = "SELECT * FROM SOUROTB WHERE EMPID = ?";
		final Object[] queryParams = new Object[] { empId };
		Employee employee = jdbcTemplate.query(retrieveQuery, queryParams,
				new ResultSetExtractor<Employee>() {
					public Employee extractData(ResultSet rs)
							throws SQLException, DataAccessException {

						Employee employee = null;
						if (rs.next() == true) {
							employee = new Employee();
							employee.setEmpId(rs.getInt("EMPID"));
							employee.setName(rs.getString("NAME"));
						}

						return employee;
					}
				});
		return employee;
	}

	public Employee getEmployeebyEmpId_opt4(int empId) {
		String retrieveQuery = "SELECT * FROM SOUROTB WHERE EMPID = ?";
		Object[] queryParams = new Object[] { empId };
		Employee employee = jdbcTemplate.queryForObject(retrieveQuery,
				queryParams,
				new BeanPropertyRowMapper<Employee>(Employee.class));
		return employee;
	}

	/* Delete */
	public void deleteEmployeebyEmpId_opt1(int empId) {
		String deleteQuery = "DELETE FROM SOUROTB WHERE EMPID=" + empId;
		jdbcTemplate.update(deleteQuery);
	}

	public void deleteEmployeebyEmpId_opt2(int empId) {
		String deleteQuery = "DELETE FROM SOUROTB WHERE EMPID=?";
		jdbcTemplate.update(deleteQuery, empId);
	}

	public void deleteEmployeebyEmpId_opt3(int empId) {
		String deleteQuery = "DELETE FROM SOUROTB WHERE EMPID=?";
		Object[] queryParams = new Object[] { empId };
		jdbcTemplate.update(deleteQuery, queryParams);
	}

	public Boolean deleteEmployeebyEmpId_opt4(final int empId) {
		String deleteQuery = "DELETE FROM SOUROTB WHERE EMPID=?";
		Boolean confirmation = jdbcTemplate.execute(deleteQuery,
				new PreparedStatementCallback<Boolean>() {
					public Boolean doInPreparedStatement(
							PreparedStatement prepStmnt) throws SQLException,
							DataAccessException {
						prepStmnt.setInt(1, empId);
						return prepStmnt.execute();
					}
				});
		return confirmation;
	}

	public void deleteEmployeebyEmpId_opt5(int empId) {
		String deleteQuery = "DELETE FROM SOUROTB WHERE EMPID= :EmpId";
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("EmpId", empId);
		namedParameterJdbcTemplate.update(deleteQuery, queryParams);
	}
}
