package mk.ukim.finki.emt2025.service.domain.impl;


import mk.ukim.finki.emt2025.model.domain.Book;

import mk.ukim.finki.emt2025.repository.JpaBookRepository;
import mk.ukim.finki.emt2025.repository.views.BooksPerAuthorViewRepository;
import mk.ukim.finki.emt2025.service.domain.AuthorService;
import mk.ukim.finki.emt2025.service.domain.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final JpaBookRepository bookRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final AuthorService authorService;
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;

    public BookServiceImpl(JpaBookRepository bookRepository, ApplicationEventPublisher applicationEventPublisher, AuthorService authorService, BooksPerAuthorViewRepository booksPerAuthorViewRepository) {
        this.bookRepository = bookRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.authorService = authorService;
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
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
        Optional<Book> savedBook=Optional.empty();
        if(book.getAuthor() != null && authorService.findById(book.getAuthor().getId()).isPresent()){
            savedBook=Optional.of(bookRepository.save(new Book(book.getName(),book.getCategory(),authorService.findById(book.getAuthor().getId()).get(),book.getAvailableCopies(),book.isRented())));
            this.refreshMaterializedView();
        }
        return savedBook;
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
                   Book updatedBook=bookRepository.save(existingBook);
                    this.refreshMaterializedView();
                    return updatedBook;
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

    @Override
    public void refreshMaterializedView() {
        this.booksPerAuthorViewRepository.refreshMaterializedView();

    }
}
