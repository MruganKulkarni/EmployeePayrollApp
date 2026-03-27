package com.example.employeepayrollapp.service;

import com.example.employeepayrollapp.dto.EmployeeDTO;
import com.example.employeepayrollapp.model.Employee;
import com.example.employeepayrollapp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<EmployeeDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getById(int id) {
        Employee emp = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        return convertToDTO(emp);
    }

    public EmployeeDTO create(EmployeeDTO dto) {
        Employee emp = convertToEntity(dto);
        return convertToDTO(repo.save(emp));
    }

    public EmployeeDTO update(int id, EmployeeDTO dto) {
        Employee existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        existing.setName(dto.getName());
        existing.setSalary(dto.getSalary());

        return convertToDTO(repo.save(existing));
    }

    public void delete(int id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        repo.deleteById(id);
    }

    private EmployeeDTO convertToDTO(Employee emp) {
        return new EmployeeDTO(emp.getName(), emp.getSalary());
    }

    private Employee convertToEntity(EmployeeDTO dto) {
        Employee emp = new Employee();
        emp.setName(dto.getName());
        emp.setSalary(dto.getSalary());
        return emp;
    }
}