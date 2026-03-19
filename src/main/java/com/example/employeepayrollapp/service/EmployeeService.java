package com.example.employeepayrollapp.service;

import com.example.employeepayrollapp.model.Employee;
import com.example.employeepayrollapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repo;

    public List<Employee> getAll() {
        return repo.findAll();
    }

    public Optional<Employee> getById(int id) {
        return repo.findById(id);
    }

    public Employee create(Employee emp) {
        return repo.save(emp);
    }

    public Employee update(Employee emp) {
        return repo.save(emp);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}