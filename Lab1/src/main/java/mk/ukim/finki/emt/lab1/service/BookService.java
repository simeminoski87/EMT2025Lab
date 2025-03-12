package mk.ukim.finki.emt.lab1.service;

import mk.ukim.finki.emt.lab1.model.Author;
import mk.ukim.finki.emt.lab1.model.Book;
import mk.ukim.finki.emt.lab1.model.enums.BookGenre;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookService {
    public List<Book> findAll();
    public Book save(Book book);
    Book deleteBookById(Long id);
    Book changeBookDetails(Long id, String name, BookGenre category, Author author, Integer availableCopies);
    Book rented(Long id);

}
