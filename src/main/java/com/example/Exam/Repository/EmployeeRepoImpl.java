package com.example.Exam.Repository;

import com.example.Exam.DBContext.DbConnection;
import com.example.Exam.Model.Employee;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class EmployeeRepoImpl implements EmployeeRepo {
    private static final String SELECT_BY_ID = "SELECT * FROM employee WHERE id=?";
    private static final String SELECT_ALL = "SELECT * FROM employee";
    private static final String SELECT_BY_NAME ="select * from employee where full_name=?";
    private static final String INSERT_EMPLOYEE = "INSERT INTO employee (full_name, birthday, address, position, department) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE id=?";
    private static final String UPDATE_EMPLOYEE = "UPDATE employee SET full_name=?, birthday=?, address=?, position=?, department=? WHERE id=?";

    private DbConnection databaseConnection;

    public EmployeeRepoImpl() {
        this.databaseConnection = DbConnection.getInstance();
    }

    @Override
    public Employee findById(int id) {
        Employee employee = null;
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setFullName(rs.getString("full_name"));
                employee.setBirthday(rs.getDate("birthday"));
                employee.setAddress(rs.getString("address"));
                employee.setPosition(rs.getString("position"));
                employee.setDepartment(rs.getString("department"));
            }
       }catch (SQLException e){
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setFullName(rs.getString("full_name"));
                employee.setBirthday(rs.getDate("birthday"));
                employee.setAddress(rs.getString("address"));
                employee.setPosition(rs.getString("position"));
                employee.setDepartment(rs.getString("department"));
                employees.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public Employee insert(Employee employee) {
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_EMPLOYEE, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, employee.getFullName());
            stmt.setDate(2, new java.sql.Date(employee.getBirthday().getTime()));
            stmt.setString(3, employee.getAddress());
            stmt.setString(4, employee.getPosition());
            stmt.setString(5, employee.getDepartment());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Insert Fail!");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    employee.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("ID Fail");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }



    @Override
    public Employee update(Employee employee) {
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_EMPLOYEE)) {

            stmt.setString(1, employee.getFullName());
            stmt.setDate(2, new java.sql.Date(employee.getBirthday().getTime()));
            stmt.setString(3, employee.getAddress());
            stmt.setString(4, employee.getPosition());
            stmt.setString(5, employee.getDepartment());
            stmt.setInt(6, employee.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Update Fail!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    @Override
    public void delete(int id) {
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_EMPLOYEE)) {

            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Delete Fail!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
