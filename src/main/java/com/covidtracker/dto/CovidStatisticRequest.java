package com.covidtracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public class CovidStatisticRequest {

    @NotBlank(message = "Country is required")
    private String country;

    private String countryCode;

    private String continent;

    @PositiveOrZero(message = "Population cannot be negative")
    private Long population;

    @PositiveOrZero(message = "Total cases cannot be negative")
    private Long totalCases;

    @PositiveOrZero(message = "New cases cannot be negative")
    private Long newCases;

    @PositiveOrZero(message = "Total deaths cannot be negative")
    private Long totalDeaths;

    @PositiveOrZero(message = "New deaths cannot be negative")
    private Long newDeaths;

    @PositiveOrZero(message = "Total recovered cannot be negative")
    private Long totalRecovered;

    @PositiveOrZero(message = "Active cases cannot be negative")
    private Long activeCases;

    @PositiveOrZero(message = "Critical cases cannot be negative")
    private Long criticalCases;

    @PositiveOrZero(message = "Cases per million cannot be negative")
    private Double casesPerMillion;

    @PositiveOrZero(message = "Deaths per million cannot be negative")
    private Double deathsPerMillion;

    @NotNull(message = "Record date is required")
    private LocalDate recordDate;

    public CovidStatisticRequest() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Long getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(Long totalCases) {
        this.totalCases = totalCases;
    }

    public Long getNewCases() {
        return newCases;
    }

    public void setNewCases(Long newCases) {
        this.newCases = newCases;
    }

    public Long getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(Long totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public Long getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(Long newDeaths) {
        this.newDeaths = newDeaths;
    }

    public Long getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(Long totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public Long getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(Long activeCases) {
        this.activeCases = activeCases;
    }

    public Long getCriticalCases() {
        return criticalCases;
    }

    public void setCriticalCases(Long criticalCases) {
        this.criticalCases = criticalCases;
    }

    public Double getCasesPerMillion() {
        return casesPerMillion;
    }

    public void setCasesPerMillion(Double casesPerMillion) {
        this.casesPerMillion = casesPerMillion;
    }

    public Double getDeathsPerMillion() {
        return deathsPerMillion;
    }

    public void setDeathsPerMillion(Double deathsPerMillion) {
        this.deathsPerMillion = deathsPerMillion;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }
}