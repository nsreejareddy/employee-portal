package com.societegenerale.employeeportal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @NotBlank(message = "firstName is mandatory")
  private String firstName;

  @NotBlank(message = "lastName is mandatory")
  private String lastName;

  @NotNull(message = "age is mandatory")
  private int age;

  @NotBlank(message = "department is mandatory")
  private String department;

  @NotBlank(message = "gender is mandatory")
  private String gender;

  public Employee(){};


  public Employee(String firstName, String lastName, int age, String department, String gender) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.department = department;
    this.gender = gender;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return id == employee.id &&
      age == employee.age &&
      firstName.equals(employee.firstName) &&
      lastName.equals(employee.lastName) &&
      department.equals(employee.department) &&
      gender.equals(employee.gender);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, age, department, gender);
  }

  @Override
  public String toString() {
    return "Employee{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", age=" + age +
      ", department='" + department + '\'' +
      ", gender='" + gender + '\'' +
      '}';
  }
}
