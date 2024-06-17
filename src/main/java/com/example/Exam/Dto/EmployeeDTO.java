package com.example.Exam.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmployeeDTO {
    private String fullName;
    private Date birthday;
    private String address;
    private String position;
    private String department;
}
