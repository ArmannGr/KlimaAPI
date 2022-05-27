package com.example.klima.dao;

import com.example.klima.model.WorldEmissionUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class WorldEmissionUsageDaoImpl implements WorldEmissionUsageDao{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorldEmissionUsageDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int  addWorldEmissionUsage(WorldEmissionUsage worldEmissionUsage) {
        String sql = """
           INSERT into world_emission_usage(year,fossil_fuel_land_usage_emissions,land_use_emissions,fossil_fuel_and_industry_emissions)
           VALUES (?,?,?,?);
           """;

        return jdbcTemplate.update(sql,worldEmissionUsage.getYear(),worldEmissionUsage.getFossilFuelLandUsageEmissions(),worldEmissionUsage.getLandUseEmissions(), worldEmissionUsage.getFossilFuelAndIndustryEmissions());

      }

    @Override
    public int readWorldEmissionUsageFile(String folder, String filename){
        System.out.println(filename);
        System.out.println(LocalDateTime.now());
        File file = new File(folder + "/" + filename);

        if (!file.canRead() || !file.isFile())
            System.exit(0);

        BufferedReader in = null;
        List<WorldEmissionUsage> WorldEmissionUsageList = new ArrayList<>();
        try {
            in = new BufferedReader(new FileReader(file));
            String row;
            in.readLine();
            int i = 0;
            String[] filenameWithoutEnding;
            if (filename.contains("_")) {
                filenameWithoutEnding = filename.split("_");
            } else {
                filenameWithoutEnding = filename.split("\\.");
            }
            while ((row = in.readLine()) != null) {
                try {
                    i++;
                    String[] splitLine = row.split(";");
                    WorldEmissionUsage worldEmissionUsage = new WorldEmissionUsage();
                    worldEmissionUsage.setYear(splitLine[1]);
                    worldEmissionUsage.setFossilFuelLandUsageEmissions(splitLine[2]);

                    worldEmissionUsage.setLandUseEmissions(splitLine[3]);
                    worldEmissionUsage.setFossilFuelAndIndustryEmissions(splitLine[4]);
                    addWorldEmissionUsage(worldEmissionUsage);

                } catch (Exception e){
                    System.out.println("Fehler");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            System.out.println(LocalDateTime.now
                    ());
            if (in != null)
                try {
                    in.close();
                } catch (IOException ignored) {
                }
        }
        return 0;
    }
}
