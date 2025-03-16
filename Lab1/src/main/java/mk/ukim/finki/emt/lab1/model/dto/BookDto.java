package mk.ukim.finki.emt.lab1.model.dto;


import mk.ukim.finki.emt.lab1.model.enums.BookGenre;

public class BookDto {
    String name;
    BookGenre category;
    Long author;
    Integer availableCopies;
    boolean rented=false;

    public BookDto(String name, BookGenre category, Long author, Integer availableCopies, boolean rented) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        this.rented = rented;
    }

    public BookDto() {
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

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
}
