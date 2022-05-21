package com.example.klima.model;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WetterRowMapper implements RowMapper<Wetter>{
    @Override
    public Wetter mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Wetter(
                rs.getInt("id"),
                rs.getString("country"),
                rs.getString("temperature"),
                rs.getString("time")
        );
    }
}
