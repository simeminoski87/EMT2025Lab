package mk.ukim.finki.emt2025.dto;

import mk.ukim.finki.emt2025.model.domain.Author;
import mk.ukim.finki.emt2025.model.domain.Book;
import mk.ukim.finki.emt2025.model.enumerations.BookGenre;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookDto(Long id, String name, BookGenre category, Long author, Integer availableCopies, boolean rented) {


    public static DisplayBookDto from(Book book){
        return new DisplayBookDto(
                book.getId(),
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies(),
                book.isRented()
        );
    }
    public static List<DisplayBookDto> from(List<Book> books){
        return books.stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }
    public Book toBook(Author author){
        return new Book(name,category,author,availableCopies,rented);
    }
}
