package com.societegenerale.employeeportal;

import com.societegenerale.employeeportal.model.Employee;
import com.societegenerale.employeeportal.repository.EmployeeRepository;
import com.societegenerale.employeeportal.service.Impl.EmployeeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.management.InvalidAttributeValueException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

  @InjectMocks
  EmployeeServiceImpl employeeService;

  @Mock
  EmployeeRepository employeeRepository;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test//should not throw exception
  public void validateEmployeeAgeShouldNotThrowException() throws InvalidAttributeValueException {
    Employee employee = new Employee(1, "fn", "ln", 21, "sales", "M");
    when(employeeRepository.save(employee)).thenReturn(employee);
    assertThat(employeeService.saveEmployee(employee)).isEqualToComparingFieldByFieldRecursively(employee);
  }


  @Test(expected = InvalidAttributeValueException.class)
  public void validateEmployeeAgeShouldThrowException() throws InvalidAttributeValueException {
    Employee employee = new Employee("fn", "ln", 10, "sales", "M");
    employeeService.saveEmployee(employee);
  }

  @Test
  public void testRetrieveEmployeeById(){
    Employee employee = new Employee(1, "fn", "ln", 21, "sales", "M");
    when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
    assertThat(employeeService.retrieveEmployeeById(1)).isEqualToComparingFieldByFieldRecursively(employee);
  }

  @Test
  public void testRetrieveAllEmployees(){
    Employee employee1 = new Employee(1, "fn1", "ln1", 21, "sales", "M");
    Employee employee2 = new Employee(2, "fn2", "ln2", 35, "sales", "F");
    List<Employee> employeeList = Arrays.asList(employee1, employee2);
    when(employeeRepository.findAll()).thenReturn(employeeList);
    assertEquals(2, employeeService.retrieveAllEmployees().size());
  }


}
