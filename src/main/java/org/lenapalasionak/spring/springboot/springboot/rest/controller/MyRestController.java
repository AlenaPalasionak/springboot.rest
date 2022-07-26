package org.lenapalasionak.spring.springboot.springboot.rest.controller;


import org.lenapalasionak.spring.springboot.springboot.rest.entity.Employee;
import org.lenapalasionak.spring.springboot.springboot.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController// управляет рест запросами и ответами
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;//теперь методы возвращают не jsp страницу
    }

    @GetMapping("employees/{id}")
//в любом запросе вместо переменной id можно писать само значение id. Переменная называется path variable
    public Employee getEmployee(@PathVariable int id) {// получить переменную id мы можем из параметра метода с помощью аннотации, получаем из адреса
        Employee employee = employeeService.getEmployee(id);

        return employee;
    }

    @PostMapping("/employees")//@PostMapping связывает запрос с методом контроллера
    public Employee addNewEmployee(@RequestBody Employee employee) {//связывает тело метода, с параметром метода контроллера
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);

        employeeService.deleteEmployee(id);
        return "Employee with id = " + id + " was deleted";
    }
}
