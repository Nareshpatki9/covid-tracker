package com.covidtracker.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CovidStatisticResponse {

    private Long id;
    private String country;
    private String countryCode;
    private String continent;
    private Long population;
    private Long totalCases;
    private Long newCases;
    private Long totalDeaths;
    private Long newDeaths;
    private Long totalRecovered;
    private Long activeCases;
    private Long criticalCases;
    private Double casesPerMillion;
    private Double deathsPerMillion;
    private LocalDate recordDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CovidStatisticResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}