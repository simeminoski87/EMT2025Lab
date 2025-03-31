package mk.ukim.finki.emt2025.service.application.impl;


import mk.ukim.finki.emt2025.dto.CreateAuthorDto;
import mk.ukim.finki.emt2025.dto.DisplayAuthorDto;
import mk.ukim.finki.emt2025.model.domain.Country;
import mk.ukim.finki.emt2025.service.application.AuthorApplicationService;
import mk.ukim.finki.emt2025.service.domain.AuthorService;
import mk.ukim.finki.emt2025.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return authorService.findById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> save(CreateAuthorDto author) {
        Optional<Country> country=countryService.findById(author.country());
        if(country.isPresent()){
            return authorService.save(author.toAuthor(country.get()))
                    .map(DisplayAuthorDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto author) {
        Optional<Country> country=countryService.findById(author.country());
        return authorService.update(id,author.toAuthor(country.orElse(null)))
                .map(DisplayAuthorDto::from);
    }

    @Override
    public List<DisplayAuthorDto> findAll() {
        return authorService.findAll().stream().map(DisplayAuthorDto::from).toList();
    }

    @Override
    public void deleteById(Long id) {
        authorService.deleteById(id);
    }
}
