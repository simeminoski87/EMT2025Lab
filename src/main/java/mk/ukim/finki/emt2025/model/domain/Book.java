package mk.ukim.finki.emt2025.model.domain;


import jakarta.persistence.*;
import mk.ukim.finki.emt2025.model.enumerations.BookGenre;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    BookGenre category;
    @ManyToOne
    Author author;
    Integer availableCopies;
    boolean rented=false;

    public Book(String name, BookGenre category, Author author, Integer availableCopies, boolean rented) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        this.rented = false;
    }

    public Book() {
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BookGenre getCategory() {
        return category;
    }

    public void setCategory(BookGenre category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}