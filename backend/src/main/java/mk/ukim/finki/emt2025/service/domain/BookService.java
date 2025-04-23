package mk.ukim.finki.emt2025.service.domain;


import mk.ukim.finki.emt2025.model.domain.Book;

import java.util.List;
import java.util.Optional;


public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> update(Long id, Book book);
    Optional<Book> save(Book book);
    Optional<Book> saveAll(List<Book> books);
    void deleteById(Long id);
    void refreshMaterializedView();


}
