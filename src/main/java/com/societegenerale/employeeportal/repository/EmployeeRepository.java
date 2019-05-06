package com.societegenerale.employeeportal.repository;

import com.societegenerale.employeeportal.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
  /**
   * All the required methods are inherited from JpaRepository (which in turn inherits from crudRepository)
   */
}
