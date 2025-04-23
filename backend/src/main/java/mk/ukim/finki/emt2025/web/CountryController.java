package mk.ukim.finki.emt2025.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import mk.ukim.finki.emt2025.dto.CreateCountryDto;
import mk.ukim.finki.emt2025.dto.DisplayCountryDto;
import mk.ukim.finki.emt2025.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/countries")
@Tag(name = "Country API",description = "Endpoints for managing countries" )
public class CountryController {
    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @Operation(summary = "Get all countries", description = "Retrieves a list of all available countries.")
    @GetMapping

    public List<DisplayCountryDto> findAll(){
        return countryApplicationService.findAll();
    }

    @Operation(summary = "Get country by ID", description = "Finds a country by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id){
        return countryApplicationService.findById(id)
                .map(c->ResponseEntity.ok().body(c))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @Operation(summary = "Add a new country", description = "Creates a new country.")
    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDto> save(@RequestBody CreateCountryDto countryDto){
        return countryApplicationService.save(countryDto)
                .map(c->ResponseEntity.ok().body(c))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @Operation(summary = "Update a country", description = "Updates a country by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayCountryDto> update(@PathVariable Long id,@RequestBody CreateCountryDto country){
        return countryApplicationService.update(id,country)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
    @Operation(summary = "Delete a country", description = "Deletes a country by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if(countryApplicationService.findById(id).isPresent()){
            countryApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
