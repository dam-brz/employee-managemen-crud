package com.dambrz.emscrud.controller;

import com.dambrz.emscrud.model.Employee;
import com.dambrz.emscrud.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // DISPLAY LIST OF EMPLOYEES
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "index";
    }

    // DISPLAY NEW EMPLOYEE FORM
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "new-employee";
    }

    // SAVE EMPLOYEE TO DATABASE
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {

        this.employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    // UPDATE TO DATABASE
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "update-employee";
    }

    //DELETE EMPLOYEE
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {

        Optional<Employee> employee = this.employeeService.deleteEmployeeById(id);
        if (employee.isEmpty()) {
            return "redirect:/?deleteSuccess=false";
        }

        return "redirect:/?deleteSuccess=true";
    }
}
