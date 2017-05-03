package com.souro.SpringJDBCTemplateDemo;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.souro.SpringJDBCTemplateDemo.employee.Employee;
import com.souro.SpringJDBCTemplateDemo.employeeDao.EmployeeDao;


public class App 
{
    public static void main( String[] args )
    {
    	@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-App-Config.xml");
    	 
        EmployeeDao employeeDao = (EmployeeDao) context.getBean("employeeDao");
        Employee employee = new Employee();
        
        /* Insert */
        employee.setEmpId(3);
        employee.setName("Souro Mukherjee");
        employeeDao.insertEmployee_opt1(employee);
        
        employee.setEmpId(4);
        employee.setName("Souro Mukherjee");
        employeeDao.insertEmployee_opt2(employee);
        
        employee.setEmpId(5);
        employee.setName("Souro Mukherjee");
        employeeDao.insertEmployee_opt3(employee);
        
        employee.setEmpId(6);
        employee.setName("Souro Mukherjee");
        Boolean confirmation = employeeDao.insertEmployee_opt4(employee);
        if(confirmation == true){
        	System.out.println("Insertion operation done successfully through method: insertEmployee_opt3");
        }
        
        employee.setEmpId(7);
        employee.setName("Souro Mukherjee");
        employeeDao.insertEmployee_opt5(employee);
    	
        
        /* Retrieve */
        Employee emp=null;
        
        employee = employeeDao.getEmployeebyEmpId_opt1(3);
        System.out.println("Employee ID: " + employee.getEmpId() + " Employee Name: " + employee.getName());
        
        emp = employeeDao.getEmployeebyEmpId_opt2(4);
        System.out.println("Employee ID: " + emp.getEmpId() + " Employee Name: " + emp.getName());

        emp = employeeDao.getEmployeebyEmpId_opt3(5);
        System.out.println("Employee ID: " + emp.getEmpId() + " Employee Name: " + emp.getName());
        
        emp = employeeDao.getEmployeebyEmpId_opt4(6);
        System.out.println("Employee ID: " + emp.getEmpId() + " Employee Name: " + emp.getName());
        
        
        /* Delete */
        employeeDao.deleteEmployeebyEmpId_opt1(3);
        employeeDao.deleteEmployeebyEmpId_opt2(4);
        employeeDao.deleteEmployeebyEmpId_opt3(5);
        confirmation = employeeDao.deleteEmployeebyEmpId_opt4(6);
        if(confirmation == true){
        	System.out.println("Insertion operation done successfully through method: insertEmployee_opt3");
        }
        employeeDao.deleteEmployeebyEmpId_opt5(7);
    }
}
