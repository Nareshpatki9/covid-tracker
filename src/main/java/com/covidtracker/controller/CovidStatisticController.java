package com.covidtracker.controller;

import com.covidtracker.dto.CovidStatisticRequest;
import com.covidtracker.dto.CovidStatisticResponse;
import com.covidtracker.service.CovidStatisticService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/covid")
@CrossOrigin(origins = "*")
public class CovidStatisticController {

    private final CovidStatisticService service;

    public CovidStatisticController(
            CovidStatisticService service) {

        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CovidStatisticResponse> createStatistic(
            @Valid @RequestBody CovidStatisticRequest request) {

        CovidStatisticResponse response =
                service.createStatistic(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<CovidStatisticResponse>>
    getAllStatistics() {

        return ResponseEntity.ok(
                service.getAllStatistics()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CovidStatisticResponse>
    getStatisticById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getStatisticById(id)
        );
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<CovidStatisticResponse>
    getByCountry(
            @PathVariable String country) {

        return ResponseEntity.ok(
                service.getStatisticByCountry(country)
        );
    }

    @GetMapping("/continent/{continent}")
    public ResponseEntity<List<CovidStatisticResponse>>
    getByContinent(
            @PathVariable String continent) {

        return ResponseEntity.ok(
                service.getStatisticsByContinent(continent)
        );
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<CovidStatisticResponse>>
    getByDate(
            @PathVariable LocalDate date) {

        return ResponseEntity.ok(
                service.getStatisticsByDate(date)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CovidStatisticResponse>
    updateStatistic(
            @PathVariable Long id,
            @Valid @RequestBody CovidStatisticRequest request) {

        return ResponseEntity.ok(
                service.updateStatistic(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatistic(
            @PathVariable Long id) {

        service.deleteStatistic(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/external/{country}")
    public ResponseEntity<CovidStatisticResponse>
    fetchExternalCountryData(
            @PathVariable String country) {

        CovidStatisticResponse response =
                service.fetchAndSaveCountryStatistics(country);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/refresh/{country}")
    public ResponseEntity<CovidStatisticResponse>
    refreshCountryData(
            @PathVariable String country) {

        CovidStatisticResponse response =
                service.refreshCountryStatistics(country);

        return ResponseEntity.ok(response);
    }
}