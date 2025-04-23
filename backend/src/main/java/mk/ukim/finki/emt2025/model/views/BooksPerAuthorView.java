package mk.ukim.finki.emt2025.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Subselect("SELECT * FROM public.books_per_authors")
@Immutable
public class BooksPerAuthorView {
    @Id
    @Column(name = "author_id")
    private Long author_id;
    @Column(name = "num_books")
    private Integer num_books;
}
