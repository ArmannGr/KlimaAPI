package com.example.klima.controller;

import com.example.klima.dao.WetterDao;
import com.example.klima.dao.WorldEmissionUsageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/wetter")
public class WetterController {
    private final WetterDao wetterDao;
    private final WorldEmissionUsageDao worldEmissionUsageDao;

    @Autowired
    public WetterController(WetterDao wetterDao, WorldEmissionUsageDao worldEmissionUsageDao) {
        this.wetterDao = wetterDao;
        this.worldEmissionUsageDao = worldEmissionUsageDao;
    }

    @GetMapping("/importToDB")
    public String importToDB(){
        String folder = "C:/Users/robin/OneDrive/Desktop/test5";
       wetterDao.readWetterFolder(folder);
        return "Success";
    }



    @GetMapping("/importToDBWorldEmissionUsage")
    public String importToDBWorldEmissionUsage(){
        String folder = "C:/Users/alexa/Desktop/DATA_PROJECT";

        worldEmissionUsageDao.readWorldEmissionUsageFile("C:/Users/alexa/Desktop/DATA_PROJECT", "ANNUAL.csv" +
                "");
        return "Success";
    }

    @GetMapping("/importToDBWaterAgriculture")
    public String importToDBWaterAgriculture(){
        String folder = "C:/Users/alexa/Desktop/DATA_PROJECT";

        worldEmissionUsageDao.readWorldEmissionUsageFile("C:/Users/alexa/Desktop/DATA_PROJECT", "ANNUAL2.csv" +
                "");
        return "Success";
    }


}

