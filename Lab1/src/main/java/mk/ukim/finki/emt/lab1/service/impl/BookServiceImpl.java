package mk.ukim.finki.emt.lab1.service.impl;

import mk.ukim.finki.emt.lab1.model.Author;
import mk.ukim.finki.emt.lab1.model.Book;
import mk.ukim.finki.emt.lab1.model.dto.AuthorDto;
import mk.ukim.finki.emt.lab1.model.dto.BookDto;
import mk.ukim.finki.emt.lab1.repository.JpaBookRepository;
import mk.ukim.finki.emt.lab1.service.AuthorService;
import mk.ukim.finki.emt.lab1.service.BookService;
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
    public Optional<Book> save(BookDto book) {
        if(book.getAuthor() != null &&
                authorService.findById(book.getAuthor()).isPresent()){
            return Optional.of(bookRepository.save(new Book(book.getName(), book.getCategory(), authorService.findById(book.getAuthor()).get(),book.getAvailableCopies(),false)));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> update(Long id, BookDto book) {
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
                            authorService.findById(book.getAuthor()).isPresent()) {
                        existingBook.setAuthor(authorService.findById(book.getAuthor()).get());
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
