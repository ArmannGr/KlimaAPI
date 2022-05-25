package com.example.klima.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class WorldEmissionUsage {
    @NotBlank
    @JsonProperty("year")
    String year;

    @NotBlank
    @JsonProperty("fossilFuelLandUsageEmissions")
    String fossilFuelLandUsageEmissions;

    @NotBlank
    @JsonProperty("landUseEmissions")
    String landUseEmissions;

    @NotBlank
    @JsonProperty("fossilFuelAndIndustryEmissions")
    String fossilFuelAndIndustryEmissions;

    public WorldEmissionUsage(){}

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFossilFuelLandUsageEmissions() {
        return fossilFuelLandUsageEmissions;
    }

    public void setFossilFuelLandUsageEmissions(String fossilFuelLandUsageEmissions) {
        this.fossilFuelLandUsageEmissions = fossilFuelLandUsageEmissions;
    }

    public String getLandUseEmissions() {
        return landUseEmissions;
    }

    public void setLandUseEmissions(String landUseEmissions) {
        this.landUseEmissions = landUseEmissions;
    }

    public String getFossilFuelAndIndustryEmissions() {
        return fossilFuelAndIndustryEmissions;
    }

    public void setFossilFuelAndIndustryEmissions(String fossilFuelAndIndustryEmissions) {
        this.fossilFuelAndIndustryEmissions = fossilFuelAndIndustryEmissions;
    }

    @Override
    public String toString() {
        return "WorldEmissionUsage{" +
                "year='" + year + '\'' +
                ", fossilFuelLandUsageEmissions='" + fossilFuelLandUsageEmissions + '\'' +
                ", fossilFuelAndIndustryEmissions='" + fossilFuelAndIndustryEmissions + '\'' +
                ", landUseEmissions='" + landUseEmissions + '\'' +
                '}';
    }
}
