package com.example.demo.domain;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CapitalRepository {
    private Map<String, String> capitalsToCountries = new HashMap<>();

    @PostConstruct
    private void init() {
        capitalsToCountries = new HashMap<>();
        capitalsToCountries.put("RU", "Moscow");
    }

    public Optional<String> findCapital(String country) {
        return Optional.ofNullable(capitalsToCountries.get(country));
    }
}
