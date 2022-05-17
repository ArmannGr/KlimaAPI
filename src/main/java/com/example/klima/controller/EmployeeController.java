package com.example.klima.controller;
import com.example.klima.dao.EmployeeDao;
import com.example.klima.model.Employee;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.validation.Valid;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    private final EmployeeDao employeeDao;


    @Autowired
    public EmployeeController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @PostMapping("/add")
    public void addEmployee(@Valid @RequestBody Employee employee){
        employeeDao.addEmployee(employee);
    }
}
