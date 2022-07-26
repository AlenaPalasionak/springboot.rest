package org.lenapalasionak.spring.springboot.springboot.rest.dao;

import org.lenapalasionak.spring.springboot.springboot.rest.entity.Employee;

import java.util.List;


public interface EmployeeDAO {
    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    public Employee getEmployee(int id);

    public void deleteEmployee(int id);
}
