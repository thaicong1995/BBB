package com.example.Exam.Model;

import com.example.Exam.Annotation.Column;
import com.example.Exam.Annotation.Id;
import com.example.Exam.Annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(tableName = "")
public class Employee {
    @Id(name = "id")
    private int id;
    @Column(columnName = "full_name")
    private String fullName;
    @Column(columnName = "birthday")
    private Date birthday;
    @Column(columnName = "address")
    private String address;
    @Column(columnName = "position")
    private String position;
    @Column(columnName = "department")
    private String department;
}
