package com.societegenerale.employeeportal.service;

import com.societegenerale.employeeportal.model.Employee;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.util.List;

@Service
public interface EmployeeService {

  Employee saveEmployee(Employee employee) throws InvalidAttributeValueException;

  Employee retrieveEmployeeById(Integer id);

  List<Employee> retrieveAllEmployees();
}
