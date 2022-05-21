package com.example.klima.dao;

import com.example.klima.model.Employee;
import com.example.klima.model.Wetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class WetterDaoImpl implements WetterDao{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WetterDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addWetter(Wetter wetter) {
        String sql = """
           INSERT into wetter(country,temperature,time)
           VALUES (?,?,?);
           """;
        return jdbcTemplate.update(sql, wetter.getCountry(),
                wetter.getTemperature(),
                wetter.getTime());
    }
}
