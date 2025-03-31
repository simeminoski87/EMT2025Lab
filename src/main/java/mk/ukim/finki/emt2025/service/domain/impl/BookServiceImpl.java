package mk.ukim.finki.emt2025.service.domain.impl;


import mk.ukim.finki.emt2025.model.domain.Book;
import mk.ukim.finki.emt2025.repository.JpaBookRepository;
import mk.ukim.finki.emt2025.service.domain.AuthorService;
import mk.ukim.finki.emt2025.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final JpaBookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(JpaBookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(Book book) {
        if(book.getAuthor() != null &&
                authorService.findById(book.getAuthor().getId()).isPresent()){
            return Optional.of(bookRepository.save(new Book(book.getName(), book.getCategory(), authorService.findById(book.getAuthor().getId()).get(),book.getAvailableCopies(),false)));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> saveAll(List<Book> books) {
        bookRepository.saveAll(books);
        return Optional.empty();
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        return bookRepository.findById(id).map(
                existingBook -> {
                    if (book.getName() != null) {
                        existingBook.setName(book.getName());
                    }
                    if(book.getCategory() != null){
                        existingBook.setCategory(book.getCategory());
                    }
                    if(book.getAvailableCopies() != null){
                        existingBook.setAvailableCopies(book.getAvailableCopies());
                        existingBook.setRented(book.isRented());
                    }
                    if (book.getAuthor() != null &&
                            authorService.findById(book.getAuthor().getId()).isPresent()) {
                        existingBook.setAuthor(authorService.findById(book.getAuthor().getId()).get());
                    }
                    return bookRepository.save(existingBook);
                }
        );
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.findById(id).map(
                existingBook -> {
                    existingBook.setRented(true);
                    return bookRepository.save(existingBook);
                }
        );
    }
}
