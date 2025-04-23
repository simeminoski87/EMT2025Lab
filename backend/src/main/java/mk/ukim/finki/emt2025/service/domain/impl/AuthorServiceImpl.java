package mk.ukim.finki.emt2025.service.domain.impl;


import mk.ukim.finki.emt2025.model.domain.Author;
import mk.ukim.finki.emt2025.repository.JpaAuthorRepository;
import mk.ukim.finki.emt2025.service.domain.AuthorService;
import mk.ukim.finki.emt2025.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final JpaAuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(JpaAuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }


    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(Author author) {
        if(author.getCountry() != null &&
        countryService.findById(author.getCountry().getId()).isPresent()){
            return Optional.of(authorRepository.save(new Author(author.getName(), author.getSurname(), countryService.findById(author.getCountry().getId()).get())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Author> update(Long id, Author author) {
        return authorRepository.findById(id).map(
                existingAuthor -> {
                    if (author.getName() != null) {
                        existingAuthor.setName(author.getName());
                    }
                    if(author.getSurname() != null){
                        existingAuthor.setSurname(author.getSurname());
                    }
                    if (author.getCountry() != null &&
                            countryService.findById(author.getCountry().getId()).isPresent()) {
                        existingAuthor.setCountry(countryService.findById(author.getCountry().getId()).get());
                    }
                    return authorRepository.save(existingAuthor);
                }
        );
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
