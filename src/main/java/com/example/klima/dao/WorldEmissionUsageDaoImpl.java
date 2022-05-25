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

    public void addWorldEmissionUsageList(List<WorldEmissionUsage> worldEmissionUsageList){
        String sql = """
           INSERT into WorldEmissionUsage(year,fossil_fuel_land_usage_emissions,land_use_emissions,fossil_fuel_and_industry_emissions)
           VALUES (?,?,?);
           """;
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                WorldEmissionUsage worldEmissionUsage = worldEmissionUsageList.get(i);
                ps.setString(1, worldEmissionUsage.getYear());
                ps.setString(2, worldEmissionUsage.getLandUseEmissions());
                ps.setString(3, worldEmissionUsage.getFossilFuelLandUsageEmissions());
                ps.setString(4, worldEmissionUsage.getFossilFuelAndIndustryEmissions());
            }

            @Override
            public int getBatchSize() {
                return worldEmissionUsageList.size();
            }
        });
    }

    @Override
    public void readWorldEmissionUsageFolder(String foldername){
        File folder = new File(foldername);
        for (File file: Objects.requireNonNull(folder.listFiles())){
            if (!file.isDirectory() && file.getName().endsWith(".csv")) {
                readWorldEmissionUsageFile(foldername, file.getName());
            }
        }
    }

    public void readWorldEmissionUsageFile(String folder, String filename){
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
                    String[] splitLine = row.split(",");
                    WorldEmissionUsage worldEmissionUsage = new WorldEmissionUsage();
                    worldEmissionUsage.setYear(filenameWithoutEnding[1]);
                    worldEmissionUsage.setFossilFuelLandUsageEmissions(splitLine[2]);
                    System.out.println(splitLine[2]);
                    worldEmissionUsage.setLandUseEmissions((splitLine[3]));
                    worldEmissionUsage.setFossilFuelAndIndustryEmissions((splitLine[3]));
                    WorldEmissionUsageList.add(worldEmissionUsage);
                    if (i == 50000){
                        addWorldEmissionUsageList(WorldEmissionUsageList);
                        WorldEmissionUsageList.clear();
                        i = 0;
                    }
                } catch (Exception e){
                    System.out.println("Fehler");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (!WorldEmissionUsageList.isEmpty()){
                addWorldEmissionUsageList(WorldEmissionUsageList);
                WorldEmissionUsageList.clear();
            }
            System.out.println(LocalDateTime.now());
            if (in != null)
                try {
                    in.close();
                } catch (IOException ignored) {
                }
        }
    }
}
