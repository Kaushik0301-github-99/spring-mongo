package com.prac.mongodb.controller;

import com.prac.mongodb.entity.Employee;
import com.prac.mongodb.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // add the employee
    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable String id){
        return employeeService.getEmployee(id);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable String id){
        return employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable String id,@RequestBody Employee employee){
        return employeeService.updateEmployee(id,employee);
    }

    @GetMapping("/all")
    public List<Employee> getEmployeeBasisOnDesignation(@RequestParam String designation){
        return employeeService.getEmployeesByDesignation(designation);
    }
}
