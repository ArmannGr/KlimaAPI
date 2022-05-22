package com.example.klima.dao;

import com.example.klima.model.Wetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WetterDaoImpl implements WetterDao{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WetterDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addWetter(Wetter wetter) {
        String sql = """
           INSERT into Wetter(country,temperature,time)
           VALUES (?,?,?);
           """;
        return jdbcTemplate.update(sql, wetter.getCountry(),
                wetter.getTemperature(),
                wetter.getTime());
    }

    public List<Wetter> addWetterList(List<Wetter> wetterList){
        addWetter((Wetter) wetterList);
        return wetterList;
    }

    @Override
    public void readWetterFolder(String foldername){
        File folder = new File(foldername);
        for (File file: folder.listFiles()){
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
            String zeile = null;
            in.readLine();
            int i = 0;
            String[] filenameWithoutEnding;
            if (filename.contains("_")) {
                filenameWithoutEnding = filename.split("_");
            } else {
                filenameWithoutEnding = filename.split("\\.");
            }
            while ((zeile = in.readLine()) != null) {
                try {
                    i++;
                    String[] splitLine = zeile.split(",");
                    Wetter wetter = new Wetter();
                    wetter.setCountry(filenameWithoutEnding[0]);
                    wetter.setTime(splitLine[0]);
                    //System.out.println(splitLine[1]);
                    wetter.setTemperature((splitLine[1]));
                    addWetter(wetter);
                   // wetterList.add(wetter);
                  /*  if (i == 10000){
                        addWetterList(wetterList);
                        wetterList.clear();
                        i = 0;
                    }*/
                } catch (Exception e){
                    System.out.println("Fehler");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /*if (!wetterList.isEmpty()){
                addWetterList(wetterList);
                wetterList.clear();
            }*/
            System.out.println(LocalDateTime.now());
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                }
        }
    }
}
