package com.souro.SpringJDBCTemplateDemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.souro.SpringJDBCDemo.employee.Employee;
import com.souro.SpringJDBCDemo.employeeDao.EmployeeDao;


public class App 
{
    public static void main( String[] args )
    {
    	@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-App-Config.xml");
    	 
        EmployeeDao employeeDao = (EmployeeDao) context.getBean("employeeDao");
        Employee employee = new Employee();
        employee.setEmpId(4);
        employee.setName("Souro Mukherjee");
        employeeDao.insertEmployee(employee);
    	
        Employee emp = employeeDao.getEmployeebyEmpId(4);
        System.out.println("Employee ID: " + emp.getEmpId() + " Employee Name: " + emp.getName());
        
        employeeDao.deleteEmployeebyEmpId(4);
    }
}
