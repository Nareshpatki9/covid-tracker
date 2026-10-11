package com.covidtracker.config;

import com.covidtracker.service.CovidStatisticService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initializeData(CovidStatisticService service) {

        return args -> {

            String[] countries = {
                    "India",
                    "USA",
                    "Brazil",
                    "Germany",
                    "United-Kingdom",
                    "France",
                    "Italy",
                    "Spain",
                    "Canada",
                    "Australia",
                    "Japan",
                    "China",
                    "South-Korea",
                    "Russia",
                    "Mexico",
                    "Indonesia",
                    "Turkey",
                    "Argentina",
                    "South-Africa",
                    "Saudi-Arabia"
            };

            System.out.println();
            System.out.println("==========================================");
            System.out.println("     COVID TRACKER DATA INITIALIZATION");
            System.out.println("==========================================");

            for (String country : countries) {

                try {

                    System.out.println(
                            "Fetching data for: " + country
                    );

                    service.fetchAndSaveCountryStatistics(country);

                    System.out.println(
                            "SUCCESS: " + country
                    );

                } catch (Exception exception) {

                    System.err.println(
                            "FAILED: " + country
                                    + " -> "
                                    + exception.getMessage()
                    );
                }
            }

            System.out.println("==========================================");
            System.out.println("     DATA INITIALIZATION COMPLETED");
            System.out.println("==========================================");
            System.out.println();

        };
    }
}