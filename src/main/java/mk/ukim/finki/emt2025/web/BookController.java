package mk.ukim.finki.emt2025.web;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import mk.ukim.finki.emt2025.dto.CreateBookDto;
import mk.ukim.finki.emt2025.dto.DisplayBookDto;
import mk.ukim.finki.emt2025.service.application.BookApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book API",description = "desc")
public class BookController {
    private final BookApplicationService bookApplicationService;

    public BookController(BookApplicationService bookApplicationService) {
        this.bookApplicationService = bookApplicationService;
    }

    @Operation(summary = "Get all books", description = "Retrieves a list of all available books.")
    @GetMapping
    public List<DisplayBookDto> findAll(){
        return bookApplicationService.findAll();
    }

    @Operation(summary = "Get book by ID", description = "Finds a books by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id){
        return bookApplicationService.findById(id)
                .map(c->ResponseEntity.ok().body(c))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @Operation(
            summary = "Add a new book",
            description = "Creates a new book based on the given BookDto."
    )

    @PostMapping("/add")
    public ResponseEntity<DisplayBookDto> save(@RequestBody CreateBookDto book){
        return bookApplicationService.save(book)
                .map(c->ResponseEntity.ok().body(c))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Update an existing book", description = "Updates a book by ID using BookDto."
    )

    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayBookDto> update(@PathVariable Long id,@RequestBody CreateBookDto book){
        return bookApplicationService.update(id,book)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @Operation(summary = "Delete a book", description = "Deletes a book by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if(bookApplicationService.findById(id).isPresent()){
            bookApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
