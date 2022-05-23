package com.example.klima.controller;

import com.example.klima.dao.WetterDao;
import com.example.klima.model.Wetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/wetter")
public class WetterController {
    private final WetterDao wetterDao;

    @Autowired
    public WetterController(WetterDao wetterDao) {
        this.wetterDao = wetterDao;
    }

    @PostMapping("/add")
    public void addWetter(@Valid @RequestBody Wetter wetter){
        wetterDao.addWetter(wetter);
    }

    @GetMapping("/importToDB")
    public String importToDB(){
        String folder = "C:/Users/robin/OneDrive/Desktop/test5";
       wetterDao.readWetterFolder(folder);
        return "Success";
    }

}
