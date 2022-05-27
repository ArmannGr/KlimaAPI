package com.example.klima.dao;

import com.example.klima.model.Wetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class WetterDaoImpl implements WetterDao{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WetterDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addWetterList(List<Wetter> wetterList){
        String sql = """
           INSERT into Wetter2(country,temperature,time)
           VALUES (?,?,?,?);
           """;
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Wetter wetter = wetterList.get(i);
                ps.setString(1, wetter.getCountry());
                ps.setString(2, wetter.getTemperature());
                ps.setString(3, wetter.getTime());
            }

            @Override
            public int getBatchSize() {
                return wetterList.size();
            }
        });
    }

    @Override
    public void readWetterFolder(String foldername){
        File folder = new File(foldername);
        for (File file: Objects.requireNonNull(folder.listFiles())){
            if (!file.isDirectory() && file.getName().endsWith(".csv")) {
                readWetterFile(foldername, file.getName());
            }
        }
    }

    public void readWetterFile(String folder, String filename){
        System.out.println(filename);
        System.out.println(LocalDateTime.now());
        File file = new File(folder + "/" + filename);

        if (!file.canRead() || !file.isFile())
            System.exit(0);

        BufferedReader in = null;
        List<Wetter> wetterList = new ArrayList<>();
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
                    Wetter wetter = new Wetter();
                    wetter.setCountry(filenameWithoutEnding[0]);
                    wetter.setTime(splitLine[0]);
                    System.out.println(splitLine[1]);
                    wetter.setTemperature((splitLine[1]));
                    wetterList.add(wetter);
                    if (i == 50000){
                        addWetterList(wetterList);
                        wetterList.clear();
                        i = 0;
                    }
                } catch (Exception e){
                    System.out.println("Fehler");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (!wetterList.isEmpty()){
                addWetterList(wetterList);
                wetterList.clear();
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
