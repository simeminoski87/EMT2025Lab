package mk.ukim.finki.emt2025.repository.views;

import jakarta.transaction.Transactional;
import mk.ukim.finki.emt2025.model.views.BooksPerAuthorView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksPerAuthorViewRepository extends JpaRepository<BooksPerAuthorView,Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.books_per_authors",nativeQuery = true)
    void refreshMaterializedView();

}
