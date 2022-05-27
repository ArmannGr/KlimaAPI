package com.example.klima.controller;

import com.example.klima.dao.CountryEmissionDao;
import com.example.klima.dao.WetterDao;
import com.example.klima.dao.WorldEmissionUsageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/wetter")
public class WetterController {
    private final WetterDao wetterDao;
    private final WorldEmissionUsageDao worldEmissionUsageDao;
    private final CountryEmissionDao countryEmissionDao;

    @Autowired
    public WetterController(WetterDao wetterDao, WorldEmissionUsageDao worldEmissionUsageDao, CountryEmissionDao countryEmissionDao) {
        this.wetterDao = wetterDao;
        this.worldEmissionUsageDao = worldEmissionUsageDao;
        this.countryEmissionDao = countryEmissionDao;
    }

    @GetMapping("/importToDB")
    public String importToDB(){
        String folder = "C:/Users/robin/OneDrive/Desktop/test5";
       wetterDao.readWetterFolder(folder);
        return "Success";
    }



    @GetMapping("/importToDBWorldEmissionUsage")
    public String importToDBWorldEmissionUsage(){

        worldEmissionUsageDao.readWorldEmissionUsageFile("C:/Users/alexa/Desktop/DATA_PROJECT", "ANNUAL.csv" +
                "");
        return "Success";
    }

    @GetMapping("/importToDBWaterAgriculture")
    public String importToDBWaterAgriculture(){

        worldEmissionUsageDao.readWorldEmissionUsageFile("C:/Users/alexa/Desktop/DATA_PROJECT", "ANNUAL2.csv" +
                "");
        return "Success";
    }

    @GetMapping("/importToDBWaterAgriculture")
    public String importToDBCountryEmission(){

        countryEmissionDao.readCountryEmissionFile("C:/Users/alexa/Desktop/DATA_PROJECT", "ANNUAL2.csv" +
                "");
        return "Success";
    }


}

