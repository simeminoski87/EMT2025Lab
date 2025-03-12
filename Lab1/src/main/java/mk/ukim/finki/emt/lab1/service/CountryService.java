package mk.ukim.finki.emt.lab1.service;

import mk.ukim.finki.emt.lab1.model.Country;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CountryService {
    List<Country> findAll();
}
