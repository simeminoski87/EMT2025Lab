package mk.ukim.finki.emt2025.service.application.impl;

import mk.ukim.finki.emt2025.dto.CreateBookDto;
import mk.ukim.finki.emt2025.dto.DisplayBookDto;
import mk.ukim.finki.emt2025.model.domain.Author;
import mk.ukim.finki.emt2025.model.events.BookCreatedEvent;
import mk.ukim.finki.emt2025.repository.views.BooksPerAuthorViewRepository;
import mk.ukim.finki.emt2025.service.application.BookApplicationService;
import mk.ukim.finki.emt2025.service.domain.AuthorService;
import mk.ukim.finki.emt2025.service.domain.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookApplicationServiceImpl implements BookApplicationService {

    private final BookService bookService;
    private final AuthorService authorService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService, ApplicationEventPublisher applicationEventPublisher, BooksPerAuthorViewRepository booksPerAuthorViewRepository) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.applicationEventPublisher = applicationEventPublisher;
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return bookService.findAll().stream().map(DisplayBookDto::from).toList();
    }

    @Override
    public Optional<DisplayBookDto> save(CreateBookDto book) {
        Optional<Author> author=authorService.findById(book.author());
         if(author.isPresent()){
           //  this.refreshMaterializedView();
             this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book.toBook(author.get())));

             return bookService.save(book.toBook(author.get()))
                     .map(DisplayBookDto::from);
         }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto book) {
        Optional<Author> author=authorService.findById(book.author());
        return bookService.update(id,book.toBook(author.orElse(null)))
                .map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }

    @Override
    public void refreshMaterializedView() {
        this.booksPerAuthorViewRepository.refreshMaterializedView();

    }
}
