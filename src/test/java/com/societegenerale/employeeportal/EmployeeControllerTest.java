package com.societegenerale.employeeportal;

import com.societegenerale.employeeportal.controller.EmployeeController;
import com.societegenerale.employeeportal.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {

  @Autowired
  EmployeeController employeeController;

  @Test
  public void testEmployeeSave(){

    Employee expectedEmployee1 = new Employee("fn1", "ln1", 30, "sales", "M");
    Employee expectedEmployee2 = new Employee("fn2", "ln2", 35, "R&D", "F");

    employeeController.saveEmployee(expectedEmployee1);
    employeeController.saveEmployee(expectedEmployee2);

    List<Employee> actualEmployees = employeeController.retrieveAllEmployees();
    assertEquals(actualEmployees.size(), 2);

  }

  @Test
  public void testEmployeeGetById(){
    Employee expectedEmployee1 = new Employee("fn3", "ln3", 40, "sales", "M");

    employeeController.saveEmployee(expectedEmployee1);
    Employee actualEmployee = employeeController.retrieveEmployeeById(3);

    assertEquals(expectedEmployee1.getFirstName(), actualEmployee.getFirstName());
    assertEquals(expectedEmployee1.getLastName(), actualEmployee.getLastName());
    assertEquals(expectedEmployee1.getAge(), actualEmployee.getAge());
    assertEquals(expectedEmployee1.getDepartment(), actualEmployee.getDepartment());
  }

}
