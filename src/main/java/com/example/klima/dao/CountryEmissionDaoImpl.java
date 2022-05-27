package com.example.klima.dao;

import com.example.klima.model.WorldEmissionUsage;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CountryEmissionDaoImpl implements  CountryEmissionDao{
    private final JdbcTemplate jdbcTemplate;

    public CountryEmissionDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void readCountryEmissionFolder(String foldername) {

    }
}
