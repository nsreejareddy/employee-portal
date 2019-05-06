package com.societegenerale.employeeportal.IntegrationTest;

import com.societegenerale.employeeportal.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeePortalIT {

  @LocalServerPort
  private int port;

  TestRestTemplate restTemplate = new TestRestTemplate();
  HttpHeaders headers = new HttpHeaders();

  @Test
  public void testSaveEmployeeValid(){
    Employee expectedEmployee = new Employee("fn1", "ln1", 30, "sales", "M");
    headers.add("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");
    HttpEntity<Employee> entity = new HttpEntity<>(expectedEmployee, headers);
    ResponseEntity<Employee> response = restTemplate.exchange(
      createURLWithPort("/employee"), HttpMethod.POST, entity, Employee.class);

    assertEquals(200, response.getStatusCode().value());
    assertEquals("fn1", response.getBody().getFirstName());
    assertEquals("ln1", response.getBody().getLastName());
    assertEquals(30, response.getBody().getAge());
    assertEquals("sales", response.getBody().getDepartment());
    assertEquals("M", response.getBody().getGender());
  }

  @Test
  public void testSaveEmployeeInvalidAge() {
    Employee expectedEmployee = new Employee("fn1", "ln1", 10, "sales", "M");
    headers.add("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");
    HttpEntity<Employee> entity = new HttpEntity<>(expectedEmployee, headers);
    ResponseEntity<Employee> response = restTemplate.exchange(
      createURLWithPort("/employee"), HttpMethod.POST, entity, Employee.class);
    assertEquals(400, response.getStatusCode().value());
  }

  @Test
  public void testRetrieveEmployeeById() throws Exception {

    Employee expectedEmployee = new Employee("fn1", "ln1", 30, "sales", "M");
    headers.add("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");
    HttpEntity<Employee> postEntity = new HttpEntity<>(expectedEmployee, headers);
    restTemplate.exchange(createURLWithPort("/employee"), HttpMethod.POST, postEntity, Employee.class);

    HttpEntity<String> getEntity = new HttpEntity<>(null, headers);

    ResponseEntity<String> response = restTemplate.exchange(
      createURLWithPort("/employee/1"), HttpMethod.GET, getEntity, String.class);

    System.out.println(response.getBody());
    String expected = "{\"id\":1,\n" +
      "\"firstName\":\"fn1\",\n" +
      "\"lastName\":\"ln1\",\n" +
      "\"department\":\"sales\",\n" +
      "\"age\": 30,\n" +
      "\"gender\":\"M\"\n" +
      "}";
    assertEquals(200, response.getStatusCode().value());
    JSONAssert.assertEquals(expected, response.getBody(), false);
  }

  @Test
  public void testRetrieveAllEmployees() throws Exception {

    Employee expectedEmployee1 = new Employee("fn1", "ln1", 30, "sales", "M");
    headers.add("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");
    HttpEntity<Employee> postEntity1 = new HttpEntity<>(expectedEmployee1, headers);
    restTemplate.exchange(createURLWithPort("/employee"), HttpMethod.POST, postEntity1, Employee.class);

    Employee expectedEmployee2 = new Employee("fn2", "ln2", 23, "sales", "F");
    HttpEntity<Employee> postEntity2 = new HttpEntity<>(expectedEmployee2, headers);
    restTemplate.exchange(createURLWithPort("/employee"), HttpMethod.POST, postEntity2, Employee.class);

    HttpEntity<String> getEntity = new HttpEntity<>(null, headers);

    ResponseEntity<List> response = restTemplate.exchange(
      createURLWithPort("/employee"), HttpMethod.GET, getEntity, List.class);

    System.out.println(response.getBody());
    assertEquals(200, response.getStatusCode().value());

    assertEquals(3, response.getBody().size());
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }
}
