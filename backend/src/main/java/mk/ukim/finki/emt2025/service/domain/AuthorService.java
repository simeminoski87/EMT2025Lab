package mk.ukim.finki.emt2025.service.domain;



import mk.ukim.finki.emt2025.model.domain.Author;

import java.util.List;
import java.util.Optional;


public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> save(Author author);
    Optional<Author> update(Long id,Author author);
    void deleteById(Long id);

}
