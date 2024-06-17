package com.example.Exam.Service;

import com.example.Exam.Dto.EmployeeDTO;
import com.example.Exam.Model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployees();
    Employee addEmployee(EmployeeDTO employeeDTO);
    Employee updateEmployee(int id,EmployeeDTO employeeDTO);
    String deleteEmployee(int id);
}
