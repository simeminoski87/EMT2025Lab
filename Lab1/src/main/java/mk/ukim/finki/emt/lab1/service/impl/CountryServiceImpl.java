package mk.ukim.finki.emt.lab1.service.impl;

import mk.ukim.finki.emt.lab1.model.Country;
import mk.ukim.finki.emt.lab1.repository.JpaCountryRepository;
import mk.ukim.finki.emt.lab1.service.CountryService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final JpaCountryRepository countryRepository;

    public CountryServiceImpl(JpaCountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> save(Country country) {
        return Optional.of(countryRepository.save(new Country(country.getName(),country.getContinent())));
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> update(Long id, Country country) {
        return countryRepository.findById(id)
                .map(existingCountry -> {
                    if(country.getName()!=null){
                        existingCountry.setName(country.getName());
                    }
                    if(country.getContinent()!=null){
                        existingCountry.setContinent(country.getContinent());
                    }
                    return countryRepository.save(existingCountry);
                });
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);

    }
}
