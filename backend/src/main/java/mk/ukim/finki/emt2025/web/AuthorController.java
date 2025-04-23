package mk.ukim.finki.emt2025.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import mk.ukim.finki.emt2025.dto.CreateAuthorDto;
import mk.ukim.finki.emt2025.dto.DisplayAuthorDto;
import mk.ukim.finki.emt2025.service.application.AuthorApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Author API", description = "Endpoints for managing authors")
public class AuthorController {
    private final AuthorApplicationService authorApplicationService;

    public AuthorController(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }

    @Operation(summary = "Get all authors", description = "Retrieves a list of all authors.")
    @GetMapping
    public List<DisplayAuthorDto> findAll(){
        return authorApplicationService.findAll();
    }

    @Operation(summary = "Get author by ID", description = "Finds an author by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAuthorDto> findById(@PathVariable Long id){
        return authorApplicationService.findById(id)
                .map(c->ResponseEntity.ok().body(c))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @Operation(
            summary = "Add a new author",
            description = "Creates a new author based on the given AuthorDto."
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayAuthorDto> save(@RequestBody CreateAuthorDto author){
        return authorApplicationService.save(author)
                .map(c->ResponseEntity.ok().body(c))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @Operation(
            summary = "Update an existing author", description = "Updates an author by ID using AuthorDto."
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAuthorDto> update(@PathVariable Long id,@RequestBody CreateAuthorDto author){
        return authorApplicationService.update(id,author)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @Operation(summary = "Delete an author", description = "Deletes an author by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if(authorApplicationService.findById(id).isPresent()){
            authorApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
