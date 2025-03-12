package mk.ukim.finki.emt.lab1.service.impl;

import mk.ukim.finki.emt.lab1.model.Author;
import mk.ukim.finki.emt.lab1.model.Book;
import mk.ukim.finki.emt.lab1.model.enums.BookGenre;
import mk.ukim.finki.emt.lab1.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.lab1.repository.JpaBookRepository;
import mk.ukim.finki.emt.lab1.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    private final JpaBookRepository bookRepository;

    public BookServiceImpl(JpaBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(Book book) {
        bookRepository.save(book);
        return book;
    }

    @Override
    public Book deleteBookById(Long id) {
        Book book=bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        bookRepository.delete(book);
        return book;
    }

    @Override
    public Book changeBookDetails(Long id, String name, BookGenre category, Author author, Integer availableCopies) {
        Book book=bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        bookRepository.save(book);
        return book;
    }


    @Override
    public Book rented(Long id) {
        Book book=bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
        return book;
    }
}
