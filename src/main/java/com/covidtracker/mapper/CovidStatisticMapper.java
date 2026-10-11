package com.covidtracker.mapper;

import com.covidtracker.dto.CovidStatisticRequest;
import com.covidtracker.dto.CovidStatisticResponse;
import com.covidtracker.entity.CovidStatistic;
import org.springframework.stereotype.Component;

@Component
public class CovidStatisticMapper {

    public CovidStatistic toEntity(CovidStatisticRequest request) {

        CovidStatistic entity = new CovidStatistic();

        entity.setCountry(request.getCountry());
        entity.setCountryCode(request.getCountryCode());
        entity.setContinent(request.getContinent());
        entity.setPopulation(request.getPopulation());

        entity.setTotalCases(request.getTotalCases());
        entity.setNewCases(request.getNewCases());

        entity.setTotalDeaths(request.getTotalDeaths());
        entity.setNewDeaths(request.getNewDeaths());

        entity.setTotalRecovered(request.getTotalRecovered());
        entity.setActiveCases(request.getActiveCases());
        entity.setCriticalCases(request.getCriticalCases());

        entity.setCasesPerMillion(request.getCasesPerMillion());
        entity.setDeathsPerMillion(request.getDeathsPerMillion());

        entity.setRecordDate(request.getRecordDate());

        return entity;
    }

    public CovidStatisticResponse toResponse(CovidStatistic entity) {

        CovidStatisticResponse response =
                new CovidStatisticResponse();

        response.setId(entity.getId());
        response.setCountry(entity.getCountry());
        response.setCountryCode(entity.getCountryCode());
        response.setContinent(entity.getContinent());
        response.setPopulation(entity.getPopulation());

        response.setTotalCases(entity.getTotalCases());
        response.setNewCases(entity.getNewCases());

        response.setTotalDeaths(entity.getTotalDeaths());
        response.setNewDeaths(entity.getNewDeaths());

        response.setTotalRecovered(entity.getTotalRecovered());
        response.setActiveCases(entity.getActiveCases());
        response.setCriticalCases(entity.getCriticalCases());

        response.setCasesPerMillion(
                entity.getCasesPerMillion()
        );

        response.setDeathsPerMillion(
                entity.getDeathsPerMillion()
        );

        response.setRecordDate(entity.getRecordDate());

        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());

        return response;
    }

    public void updateEntity(
            CovidStatistic entity,
            CovidStatisticRequest request) {

        entity.setCountry(request.getCountry());
        entity.setCountryCode(request.getCountryCode());
        entity.setContinent(request.getContinent());
        entity.setPopulation(request.getPopulation());

        entity.setTotalCases(request.getTotalCases());
        entity.setNewCases(request.getNewCases());

        entity.setTotalDeaths(request.getTotalDeaths());
        entity.setNewDeaths(request.getNewDeaths());

        entity.setTotalRecovered(request.getTotalRecovered());
        entity.setActiveCases(request.getActiveCases());
        entity.setCriticalCases(request.getCriticalCases());

        entity.setCasesPerMillion(
                request.getCasesPerMillion()
        );

        entity.setDeathsPerMillion(
                request.getDeathsPerMillion()
        );

        entity.setRecordDate(request.getRecordDate());
    }
}