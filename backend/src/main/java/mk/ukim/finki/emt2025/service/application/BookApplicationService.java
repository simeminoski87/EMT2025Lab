package mk.ukim.finki.emt2025.service.application;



import mk.ukim.finki.emt2025.dto.CreateBookDto;
import mk.ukim.finki.emt2025.dto.DisplayBookDto;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService{
    List<DisplayBookDto> findAll();
    Optional<DisplayBookDto> save(CreateBookDto book);
    Optional<DisplayBookDto> update(Long id,CreateBookDto book);
    Optional<DisplayBookDto> findById(Long id);
    void deleteById(Long id);
    void refreshMaterializedView();
}
