package com.example.demo;

import demo.example.com.Country;
import demo.example.com.Currency;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CountryRepository {

    private final List<Country> countries = new ArrayList<>();

    @PostConstruct
    private void initCountries() {
        Country russia = new Country();
        russia.setPopulation(150_000_000);
        russia.setCurrency(Currency.RUB);
        russia.setCapital("Moscow");
        russia.setName("Russia");
        russia.setIsoCode("RU");

        countries.add(russia);

        Country usa = new Country();
        usa.setPopulation(331_000_000);
        usa.setCurrency(Currency.DOL);
        usa.setCapital("Washington");
        usa.setName("USA");
        usa.setIsoCode("USA");

        countries.add(usa);

        Country germany = new Country();
        germany.setPopulation(82_000_000);
        germany.setCurrency(Currency.EUR);
        germany.setCapital("Berlin");
        germany.setName("Germany");
        germany.setIsoCode("DE");

        countries.add(germany);
    }

    public Country findCountry(String isoCode) {
        return countries.stream()
                .filter(c -> c.getIsoCode().equalsIgnoreCase(isoCode))
                .findFirst()
                .orElseThrow(() -> new BusinessException("Country with isoCode \"" +
                        isoCode + "\" not found"));
    }

    public List<Country> findAllByNames(List<String> names) {
        List<Country> result = new ArrayList<>();
        for (String name : names) {
            Country country = countries.stream()
                    .filter(c -> c.getName().equalsIgnoreCase(name))
                    .findFirst()
                    .orElseThrow(() -> new BusinessException("Country with name \"" + name + "\" not found"));
            result.add(country);
        }
        return result;
    }
}
