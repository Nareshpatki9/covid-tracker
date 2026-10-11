package com.covidtracker.repository;

import com.covidtracker.entity.CovidStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CovidStatisticRepository
        extends JpaRepository<CovidStatistic, Long> {

    Optional<CovidStatistic> findByCountryIgnoreCase(String country);

    List<CovidStatistic> findByContinentIgnoreCase(String continent);

    List<CovidStatistic> findByRecordDate(LocalDate recordDate);

    boolean existsByCountryIgnoreCaseAndRecordDate(
            String country,
            LocalDate recordDate
    );
}