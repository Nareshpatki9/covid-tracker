package com.covidtracker.service.impl;

import com.covidtracker.client.CovidApiClient;
import com.covidtracker.client.CovidApiResponse;
import com.covidtracker.dto.CovidStatisticRequest;
import com.covidtracker.dto.CovidStatisticResponse;
import com.covidtracker.entity.CovidStatistic;
import com.covidtracker.exception.ResourceNotFoundException;
import com.covidtracker.mapper.CovidStatisticMapper;
import com.covidtracker.repository.CovidStatisticRepository;
import com.covidtracker.service.CovidStatisticService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CovidStatisticServiceImpl implements CovidStatisticService {

    private final CovidStatisticRepository repository;
    private final CovidStatisticMapper mapper;
    private final CovidApiClient covidApiClient;

    public CovidStatisticServiceImpl(
            CovidStatisticRepository repository,
            CovidStatisticMapper mapper,
            CovidApiClient covidApiClient) {

        this.repository = repository;
        this.mapper = mapper;
        this.covidApiClient = covidApiClient;
    }

    @Override
    public CovidStatisticResponse createStatistic(
            CovidStatisticRequest request) {

        boolean exists =
                repository.existsByCountryIgnoreCaseAndRecordDate(
                        request.getCountry(),
                        request.getRecordDate()
                );

        if (exists) {
            throw new IllegalArgumentException(
                    "COVID statistic already exists for country: "
                            + request.getCountry()
                            + " on date: "
                            + request.getRecordDate()
            );
        }

        CovidStatistic entity = mapper.toEntity(request);

        CovidStatistic savedEntity = repository.save(entity);

        return mapper.toResponse(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public CovidStatisticResponse getStatisticById(Long id) {

        CovidStatistic entity =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "COVID statistic not found with id: "
                                                + id
                                )
                        );

        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public CovidStatisticResponse getStatisticByCountry(
            String country) {

        CovidStatistic entity =
                repository.findByCountryIgnoreCase(country)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "COVID statistic not found for country: "
                                                + country
                                )
                        );

        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CovidStatisticResponse> getAllStatistics() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CovidStatisticResponse> getStatisticsByContinent(
            String continent) {

        return repository.findByContinentIgnoreCase(continent)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CovidStatisticResponse> getStatisticsByDate(
            LocalDate date) {

        return repository.findByRecordDate(date)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public CovidStatisticResponse updateStatistic(
            Long id,
            CovidStatisticRequest request) {

        CovidStatistic entity =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "COVID statistic not found with id: "
                                                + id
                                )
                        );

        mapper.updateEntity(entity, request);

        CovidStatistic updatedEntity =
                repository.save(entity);

        return mapper.toResponse(updatedEntity);
    }

    @Override
    public void deleteStatistic(Long id) {

        if (!repository.existsById(id)) {

            throw new ResourceNotFoundException(
                    "COVID statistic not found with id: " + id
            );
        }

        repository.deleteById(id);
    }

    @Override
    public CovidStatisticResponse fetchAndSaveCountryStatistics(
            String country) {

        CovidApiResponse apiResponse =
                covidApiClient.getCountryStatistics(country);

        if (apiResponse == null) {
            throw new IllegalStateException(
                    "No data received from COVID API"
            );
        }

        LocalDate today = LocalDate.now();

        CovidStatistic existing =
                repository.findByCountryIgnoreCase(country)
                        .orElse(null);

        CovidStatistic entity;

        if (existing != null) {
            entity = existing;
        } else {
            entity = new CovidStatistic();
        }

        entity.setCountry(apiResponse.getCountry());
        entity.setCountryCode(apiResponse.getCountryCode());
        entity.setContinent(apiResponse.getContinent());
        entity.setPopulation(apiResponse.getPopulation());

        entity.setTotalCases(apiResponse.getTotalCases());
        entity.setNewCases(apiResponse.getNewCases());

        entity.setTotalDeaths(apiResponse.getTotalDeaths());
        entity.setNewDeaths(apiResponse.getNewDeaths());

        entity.setTotalRecovered(apiResponse.getTotalRecovered());
        entity.setActiveCases(apiResponse.getActiveCases());
        entity.setCriticalCases(apiResponse.getCriticalCases());

        entity.setCasesPerMillion(
                apiResponse.getCasesPerMillion()
        );

        entity.setDeathsPerMillion(
                apiResponse.getDeathsPerMillion()
        );

        entity.setRecordDate(today);

        CovidStatistic savedEntity =
                repository.save(entity);

        return mapper.toResponse(savedEntity);
    }

    @Override
    public CovidStatisticResponse refreshCountryStatistics(
            String country) {

        return fetchAndSaveCountryStatistics(country);
    }
}