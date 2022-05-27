package com.example.klima.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class CountryEmissionGrowth {

    @JsonProperty("id")
    private int id;

    @NotBlank
    @JsonProperty("country")
    private String country;

    @NotBlank
    @JsonProperty("year")
    private String year;

    @NotBlank
    @JsonProperty("co2Growth")
    private String co2Growth;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    public String getCo2Growth() {
        return co2Growth;
    }

    public void setCo2Growth(String co2Growth) {
        this.co2Growth = co2Growth;
    }

    @Override
    public String toString() {
        return "CountryEmissionGrowth{" +
                "country='" + country + '\'' +
                ", year='" + year + '\'' +
                ", co2Growth='" + co2Growth+ '\'' +
                '}';
    }
}
