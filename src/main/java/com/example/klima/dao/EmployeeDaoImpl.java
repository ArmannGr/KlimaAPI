package com.example.klima.dao;

import com.example.klima.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addEmployee(Employee employee) {
        String sql = """
           INSERT into employee(first_name,last_name,email)
           VALUES (?,?,?);
           """;
        return jdbcTemplate.update(sql, employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail());
    }

}
