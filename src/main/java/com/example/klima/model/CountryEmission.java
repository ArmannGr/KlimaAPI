package com.example.klima.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class CountryEmission {
    @NotBlank
    @JsonProperty("country")
    String country;

    @NotBlank
    @JsonProperty("year")
    String year;

    @NotBlank
    @JsonProperty("co2")
    String co2;

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

    public String getCo2() {
        return co2;
    }

    public void setCo2(String co2) {
        this.co2 = co2;
    }

    @Override
    public String toString() {
        return "CountryEmission{" +
                "country='" + country + '\'' +
                ", year='" + year + '\'' +
                ", co2='" + co2 + '\'' +
                '}';
    }
}
