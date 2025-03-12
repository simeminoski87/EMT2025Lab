package mk.ukim.finki.emt.lab1.web;

import mk.ukim.finki.emt.lab1.model.Book;
import mk.ukim.finki.emt.lab1.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String getAllBooks() {
        return "books";
    }
    @PostMapping("books/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }
}
