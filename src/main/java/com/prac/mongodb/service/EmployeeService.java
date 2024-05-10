package com.prac.mongodb.service;

import com.prac.mongodb.entity.Employee;
import com.prac.mongodb.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee){
       employee.setEmployeeId(UUID.randomUUID().toString().split("-")[0]);
       return this.employeeRepository.save(employee);
    }

    public List<Employee> getEmployees() {
        return this.employeeRepository.findAll();
    }

    public Employee getEmployee(String id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    public String deleteEmployee(String id) {
        Employee existingEmployee =this.employeeRepository.findById(id).orElse(null);
        if(existingEmployee!=null){
        this.employeeRepository.delete(existingEmployee);
        return "SUCCESS";
        }
        return "FAILURE";
    }

    public Employee updateEmployee(String id, Employee employee) {
        Employee existingEmployee = this.employeeRepository.findById(id).orElse(null);
        if (existingEmployee != null) {
            if (null!=employee.getName()) existingEmployee.setName(employee.getName());
            if (null!=employee.getExperience()) existingEmployee.setExperience(employee.getExperience());
            if (null!=employee.getPosition()) existingEmployee.setPosition(employee.getPosition());
            if (null!=employee.getSalary()) existingEmployee.setSalary(employee.getSalary());
            return this.employeeRepository.save(existingEmployee);
        }
        return null;
    }

    public List<Employee> getEmployeesByDesignation(String designation) {
       return this.employeeRepository.findAllByPosition(designation);
    }

    public List<Employee> getEmployeesByPagination(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo,pageSize,Sort.by("experience").ascending());
        Page<Employee> pagingEmployees = employeeRepository.findAll(pageRequest);
        return pagingEmployees.getContent();
    }
}
