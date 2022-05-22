package com.example.klima.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class Wetter {

    @JsonProperty("id")
    private int id;

    @NotBlank
    @JsonProperty("country")
    private String country;

    @NotBlank
    @JsonProperty("temperature")
    private String temperature;

    @NotBlank
    @JsonProperty("time")
    private String time;

    public Wetter(int id, String country, String temperature, String time){
        this.id = id;
        this.country = country;
        this.temperature = temperature;
        this.time = time;
    }

    public Wetter(){}

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Wetter{" +
                "country='" + country + '\'' +
                ", temperature='" + temperature + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
