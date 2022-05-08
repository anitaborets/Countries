package com.example.demo.controller;

import com.example.demo.model.Country;
import com.example.demo.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping("/index")
    public String viewHomePage(Model model) {
        return findPagination(1, "nameOfCountry","asc",model);
    }

    @GetMapping("/page/showNewCountryForm")
    public String showNewCountryForm(Model model) {
        Country country = new Country();
        model.addAttribute("country", country);
        return "/new_country";
    }

    @PostMapping("/saveCountry")
    public String saveCountry(@ModelAttribute("country") Country country) {
        countryService.saveCountry(country);
        return "index";
    }

    @GetMapping("/page/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
        Country country = countryService.getCountryById(id);
        model.addAttribute("country", country);
        return "update_country";
    }

    @GetMapping("/page/deleteCountry/{id}")
    public String deleteCountry(@PathVariable(value = "id") long id) {
        this.countryService.deleteCountryById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNumber}")
    public String findPagination(@PathVariable(value = "pageNumber") int pageNumber, @RequestParam("sortField") String sortField,
                                 @RequestParam("sortDir") String sortDir, Model model) {
        int pageSize = 5;
        Page<Country> page = countryService.findPagination(pageNumber, pageSize,sortField,sortDir);
        List<Country> listCountries = page.getContent();
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listCountries", listCountries);
        return "index";

    }
}
