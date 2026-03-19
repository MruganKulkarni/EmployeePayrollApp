package com.example.employeepayrollapp.controller;

import com.example.employeepayrollapp.model.Employee;
import com.example.employeepayrollapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("/")
    public List<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public Employee getById(@PathVariable int id) {
        return service.getById(id).orElse(null);
    }

    @PostMapping("/create")
    public Employee create(@RequestBody Employee emp) {
        return service.create(emp);
    }

    @PutMapping("/update")
    public Employee update(@RequestBody Employee emp) {
        return service.update(emp);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "Deleted Successfully";
    }
}