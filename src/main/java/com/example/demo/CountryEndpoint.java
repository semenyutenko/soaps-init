package com.example.demo;

import demo.example.com.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://com.example.demo";

    private final CountryRepository repository;

    @Autowired
    public CountryEndpoint(CountryRepository repository) {
        this.repository = repository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CountryRequest")
    @ResponsePayload
    public CountryResponse findCountry(@RequestPayload CountryRequest request) {
        CountryResponse response = new CountryResponse();
        Country country = repository.findCountry(request.getIsoCode());
        response.setCountry(country);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PopulationRequest")
    @ResponsePayload
    public PopulationResponse getPopulation(@RequestPayload PopulationRequest request) {
        PopulationResponse response = new PopulationResponse();
        List<Country> countries = repository.findAllByNames(request.getCountryName());
        response.setTotalPopulation(countries.stream()
                .mapToInt(Country::getPopulation)
                .sum());
        return response;
    }
}
