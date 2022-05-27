package com.example.klima.dao;

import com.example.klima.model.CountryEmissionGrowth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CountryEmissionGrowthDaoImpl implements CountryEmissionGrowthDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CountryEmissionGrowthDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addCountryEmissionGrowth(CountryEmissionGrowth countryEmissionGrowth){
        String sql = """
           INSERT into Wetter2(country,year,co2Growth)
           VALUES (?,?,?);
           """;
        return jdbcTemplate.update(sql,countryEmissionGrowth.getCountry(),countryEmissionGrowth.getYear(), countryEmissionGrowth.getCo2Growth());
    }

    @Override
    public int readCountryEmissionGrowthFile(String folder, String filename){
        System.out.println(filename);
        System.out.println(LocalDateTime.now());
        File file = new File(folder + "/" + filename);

        if (!file.canRead() || !file.isFile())
            System.exit(0);

        BufferedReader in = null;
        List<CountryEmissionGrowthDao> countryEmissionGrowthDaoList = new ArrayList<>();
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
                    CountryEmissionGrowth countryEmissionGrowth = new CountryEmissionGrowth();
                    countryEmissionGrowth.setCountry(splitLine[1]);
                    countryEmissionGrowth.setYear(splitLine[2]);

                    countryEmissionGrowth.setCo2Growth(splitLine[3]);
                    addCountryEmissionGrowth(countryEmissionGrowth);

                } catch (Exception e){
                    System.out.println(e);
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
