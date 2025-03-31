package mk.ukim.finki.emt2025.service.application;

import mk.ukim.finki.emt2025.dto.DisplayBookDto;
import mk.ukim.finki.emt2025.dto.WishListDto;
import mk.ukim.finki.emt2025.model.domain.WishList;

import java.util.List;
import java.util.Optional;

public interface WishListApplicationService {
    List<DisplayBookDto> listAllProductsInShoppingCart(Long cartId);

    Optional<WishListDto> getActiveShoppingCart(String username);

    Optional<WishListDto> addProductToShoppingCart(String username, Long productId);
    Optional<WishListDto> rentAllBooks(String username);

}
