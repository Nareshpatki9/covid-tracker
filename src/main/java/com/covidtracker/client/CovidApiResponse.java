package com.covidtracker.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CovidApiResponse {

    private String country;

    @JsonProperty("countryCode")
    private String countryCode;

    private String continent;

    private Long population;

    @JsonProperty("cases")
    private Long totalCases;

    @JsonProperty("todayCases")
    private Long newCases;

    @JsonProperty("deaths")
    private Long totalDeaths;

    @JsonProperty("todayDeaths")
    private Long newDeaths;

    @JsonProperty("recovered")
    private Long totalRecovered;

    @JsonProperty("active")
    private Long activeCases;

    @JsonProperty("critical")
    private Long criticalCases;

    @JsonProperty("casesPerOneMillion")
    private Double casesPerMillion;

    @JsonProperty("deathsPerOneMillion")
    private Double deathsPerMillion;

    public CovidApiResponse() {
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
}