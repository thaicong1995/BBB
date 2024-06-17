package com.example.Exam.Service;

import com.example.Exam.Dto.EmployeeDTO;
import com.example.Exam.Model.Employee;
import com.example.Exam.Repository.EmployeeRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;


    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;

    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepo.findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee addEmployee(Employee employee) {

        Employee addedEmployee = employeeRepo.insert(employee);

        return addedEmployee;
    }

    @Override
    public Employee updateEmployee(int id,EmployeeDTO employeeDTO) {
        var exist = employeeRepo.findById(id);
        if (exist == null){
            throw new RuntimeException("Not Found");
        }
        exist.setFullName(employeeDTO.getFullName());
        exist.setBirthday(employeeDTO.getBirthday());
        exist.setAddress(employeeDTO.getAddress());
        exist.setPosition(employeeDTO.getPosition());
        exist.setDepartment(employeeDTO.getDepartment());
        Employee updatedEmployee = employeeRepo.update(exist);
        return updatedEmployee;
    }


    @Override
    public String deleteEmployee(int id) {
        var exist = employeeRepo.findById(id);
        if (exist != null){
            throw new RuntimeException("Not Found");
        }
        employeeRepo.delete(id);
       return "Successfully deleted";
    }


}
