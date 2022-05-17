package com.example.klima.dao;
import com.example.klima.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface EmployeeDao {
    int addEmployee(Employee employee);
}
