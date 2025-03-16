package mk.ukim.finki.emt.lab1.service;

import mk.ukim.finki.emt.lab1.model.Author;
import mk.ukim.finki.emt.lab1.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;


public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> save(AuthorDto author);
    Optional<Author> update(Long id,AuthorDto author);
    void deleteById(Long id);

}
