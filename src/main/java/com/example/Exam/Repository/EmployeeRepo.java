package com.example.Exam.Repository;

import com.example.Exam.Model.Employee;

import java.util.List;

public interface EmployeeRepo {
    Employee findById(int id);
    List<Employee> findAll();
    Employee insert(Employee employee);
    Employee update(Employee employee);
    void delete(int id);
}
