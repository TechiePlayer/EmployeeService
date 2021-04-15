package com.tp.employee.employeeservice.controller;

import com.tp.employee.employeeservice.exception.ResourceNotFoundException;
import com.tp.employee.employeeservice.model.Employee;
import com.tp.employee.employeeservice.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:8093")
@RestController
@RequestMapping("/api/V1")
public class EmployeeController {

    @Autowired
    EmployeeRepo employeeRepo;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) throws ResourceNotFoundException {
        Employee employee = employeeRepo.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Requested employee id "+ id + " not found"));
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepo.save(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee)throws ResourceNotFoundException {
        Employee emp = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requested employee id "+ id + " not found"));
        emp.setFirst_name(employee.getFirst_name());
        emp.setLast_name(employee.getLast_name());
        emp.setEmail(employee.getEmail());
        Employee updatedEmployee = employeeRepo.save(emp);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable("id") long id) throws ResourceNotFoundException {
        Map<String, Boolean> returnMap = new HashMap<>();
        Employee emp = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Requested employee id "+ id + " not found"));
        employeeRepo.delete(emp);
        returnMap.put("Deleted",true);
        return returnMap;
    }
}
