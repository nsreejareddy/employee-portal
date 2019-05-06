package com.societegenerale.employeeportal.controller;

import com.societegenerale.employeeportal.model.Employee;
import com.societegenerale.employeeportal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {

  @Autowired
  EmployeeService employeeService;

  @PostMapping("/employee")
  public ResponseEntity saveEmployee(@RequestBody @Valid Employee employee){
    Employee employee1;
    try {
      employee1 = employeeService.saveEmployee(employee);
    } catch (InvalidAttributeValueException e) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(employee1);
  }

  @GetMapping("/employee")
  public List<Employee> retrieveAllEmployees(){
    return employeeService.retrieveAllEmployees();
  }

  @GetMapping("/employee/{id}")
  public Employee retrieveEmployeeById(@PathVariable("id") int id){
    return employeeService.retrieveEmployeeById(id);
  }


}
