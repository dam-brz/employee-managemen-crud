package com.dambrz.emscrud.service;

import com.dambrz.emscrud.model.Employee;
import com.dambrz.emscrud.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(long id) {
        return this.employeeRepository.findById(id);
    }

    public Optional<Employee> deleteEmployeeById(long id) {

        Optional<Employee> employee = this.employeeRepository.findById(id);
        employee.ifPresent(this.employeeRepository::delete);

        return employee;
    }

}
