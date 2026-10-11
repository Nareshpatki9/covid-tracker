package com.covidtracker.client;

import com.covidtracker.exception.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CovidApiClient {

    private final RestClient restClient;

    @Value("${covid.api.base-url}")
    private String baseUrl;

    public CovidApiClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public CovidApiResponse getCountryStatistics(String country) {

        try {

            return restClient.get()
                    .uri(baseUrl + "/countries/" + country)
                    .retrieve()
                    .onStatus(
                            HttpStatusCode::isError,
                            (request, response) -> {
                                throw new ApiException(
                                        "COVID API returned status: "
                                                + response.getStatusCode()
                                );
                            }
                    )
                    .body(CovidApiResponse.class);

        } catch (ApiException exception) {

            throw exception;

        } catch (Exception exception) {

            throw new ApiException(
                    "Unable to connect to COVID API",
                    exception
            );
        }
    }

    public CovidApiResponse getGlobalStatistics() {

        try {

            return restClient.get()
                    .uri(baseUrl + "/all")
                    .retrieve()
                    .onStatus(
                            HttpStatusCode::isError,
                            (request, response) -> {
                                throw new ApiException(
                                        "COVID API returned status: "
                                                + response.getStatusCode()
                                );
                            }
                    )
                    .body(CovidApiResponse.class);

        } catch (ApiException exception) {

            throw exception;

        } catch (Exception exception) {

            throw new ApiException(
                    "Unable to connect to global COVID API",
                    exception
            );
        }
    }
}