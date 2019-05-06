package com.societegenerale.employeeportal.service.Impl;

import com.societegenerale.employeeportal.model.Employee;
import com.societegenerale.employeeportal.repository.EmployeeRepository;
import com.societegenerale.employeeportal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository;

  @Override
  public Employee saveEmployee(Employee employee) throws InvalidAttributeValueException {
    if(employee.getAge() > 20) {
      return employeeRepository.save(employee);
    }else {
      throw new InvalidAttributeValueException();
    }
  }

  @Override
  public Employee retrieveEmployeeById(Integer id) {
    return employeeRepository.findById(id).get();
  }

  @Override
  public List<Employee> retrieveAllEmployees() {
    return employeeRepository.findAll();
  }

}
