package com.example.demo.service;

import com.example.demo.model.Country;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();

    void saveCountry(Country country);
    Country getCountryById(Long id);
    void deleteCountryById(Long id);
    Page<Country> findPagination (int pageNumber, int pageSize, String sortField,String sortDirection);
}
