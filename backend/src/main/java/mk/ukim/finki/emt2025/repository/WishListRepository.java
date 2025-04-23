package mk.ukim.finki.emt2025.repository;

import mk.ukim.finki.emt2025.model.domain.User;
import mk.ukim.finki.emt2025.model.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList,Long> {
    Optional<WishList> findByUser(User user);
}
