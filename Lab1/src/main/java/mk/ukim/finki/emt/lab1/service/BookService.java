package mk.ukim.finki.emt.lab1.service;

import mk.ukim.finki.emt.lab1.model.Book;
import mk.ukim.finki.emt.lab1.model.dto.BookDto;

import java.util.List;
import java.util.Optional;


public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> update(Long id, BookDto book);
    Optional<Book> save(BookDto book);
    void deleteById(Long id);


}
