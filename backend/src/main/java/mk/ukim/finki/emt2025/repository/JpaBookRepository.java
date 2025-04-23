package mk.ukim.finki.emt2025.repository;


import mk.ukim.finki.emt2025.model.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBookRepository extends JpaRepository<Book,Long> {
}
