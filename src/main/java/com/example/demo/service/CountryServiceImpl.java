package com.example.demo.service;

import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService{

    @Autowired
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public void saveCountry(Country country) {
        this.countryRepository.save(country);
    }

    @Override
    public Country getCountryById(Long id) {
        Optional<Country> optional = countryRepository.findById(Long.valueOf(id));
        Country country = null;
        if(optional.isPresent()){
            country = optional.get();
        }
        else{
            throw new RuntimeException("Country not found for id ::" + id);
        }
        return country;
    }

    @Override
    public void deleteCountryById(Long id) {
        this.countryRepository.deleteById(id);
    }

    @Override
    public Page<Country> findPagination(int pageNumber, int pageSize,String sortField,String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1,pageSize,sort);
        return this.countryRepository.findAll(pageable);
    }
}
