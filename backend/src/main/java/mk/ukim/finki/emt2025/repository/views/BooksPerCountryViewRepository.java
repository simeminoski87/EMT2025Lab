package mk.ukim.finki.emt2025.repository.views;

import mk.ukim.finki.emt2025.model.views.BookPerCountryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksPerCountryViewRepository extends JpaRepository<BookPerCountryView,Long> {
    BookPerCountryView findByCountryId(Long countryId);
}
