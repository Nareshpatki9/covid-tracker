package com.covidtracker.service;

import com.covidtracker.dto.CovidStatisticRequest;
import com.covidtracker.dto.CovidStatisticResponse;

import java.time.LocalDate;
import java.util.List;

public interface CovidStatisticService {

    CovidStatisticResponse createStatistic(
            CovidStatisticRequest request
    );

    CovidStatisticResponse getStatisticById(Long id);

    CovidStatisticResponse getStatisticByCountry(String country);

    List<CovidStatisticResponse> getAllStatistics();

    List<CovidStatisticResponse> getStatisticsByContinent(
            String continent
    );

    List<CovidStatisticResponse> getStatisticsByDate(
            LocalDate date
    );

    CovidStatisticResponse updateStatistic(
            Long id,
            CovidStatisticRequest request
    );

    void deleteStatistic(Long id);

    CovidStatisticResponse fetchAndSaveCountryStatistics(
            String country
    );

    CovidStatisticResponse refreshCountryStatistics(
            String country
    );
}