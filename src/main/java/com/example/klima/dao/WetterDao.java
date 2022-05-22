package com.example.klima.dao;

import com.example.klima.model.Wetter;

public interface WetterDao {
    int addWetter(Wetter wetter);
    void readWetterFolder(String foldername);
}
