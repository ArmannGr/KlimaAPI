package com.example.klima.dao;

import com.example.klima.model.CountryEmission;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

@Repository
public class CountryEmissionDaoImpl implements  CountryEmissionDao{
    private final JdbcTemplate jdbcTemplate;

    public CountryEmissionDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addCountryEmission(CountryEmission countryEmission){
        String sql = """
           INSERT into country_emission(country,year,co2)
           VALUES (?,?,?);
           """;

        jdbcTemplate.update(sql, countryEmission.getCountry(), countryEmission.getYear(), countryEmission.getCo2());

    }

    @Override
    public void readCountryEmissionFile(String folder, String filename){
        System.out.println(filename);
        System.out.println(LocalDateTime.now());
        File file = new File(folder + "/" + filename);

        if (!file.canRead() || !file.isFile())
            System.exit(0);

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            String row;
            in.readLine();
            while ((row = in.readLine()) != null) {
                try {
                    String[] splitLine = row.split(";");
                    CountryEmission countryEmission = new CountryEmission();
                    countryEmission.setCountry(splitLine[0]);
                    countryEmission.setYear(splitLine[1]);

                    countryEmission.setCo2(splitLine[2]);
                    addCountryEmission(countryEmission);

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
    }
}
