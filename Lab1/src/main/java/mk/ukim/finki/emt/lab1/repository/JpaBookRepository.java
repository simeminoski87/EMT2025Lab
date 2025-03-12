package mk.ukim.finki.emt.lab1.repository;

import mk.ukim.finki.emt.lab1.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBookRepository extends JpaRepository<Book,Long> {
}
