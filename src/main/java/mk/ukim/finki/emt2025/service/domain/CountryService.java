package mk.ukim.finki.emt2025.service.domain;



import mk.ukim.finki.emt2025.model.domain.Country;

import java.util.List;
import java.util.Optional;


public interface CountryService {
    List<Country> findAll();
    Optional<Country> save(Country country);
    Optional<Country> findById(Long id);
    Optional<Country> update(Long id,Country country);
    void deleteById(Long id);
}
