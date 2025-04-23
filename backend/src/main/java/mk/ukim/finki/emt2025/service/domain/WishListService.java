package mk.ukim.finki.emt2025.service.domain;

import mk.ukim.finki.emt2025.model.domain.Book;
import mk.ukim.finki.emt2025.model.domain.WishList;

import java.util.List;
import java.util.Optional;

public interface WishListService {
    List<Book> listAllProductsInShoppingCart(Long cartId);

    Optional<WishList> getActiveShoppingCart(String username);

    Optional<WishList> addProductToShoppingCart(String username, Long productId);
    Optional<WishList> rentAllBooks(String username);
}

