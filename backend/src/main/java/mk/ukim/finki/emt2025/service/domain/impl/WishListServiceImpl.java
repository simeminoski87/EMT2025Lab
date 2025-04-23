package mk.ukim.finki.emt2025.service.domain.impl;

import mk.ukim.finki.emt2025.model.domain.Book;
import mk.ukim.finki.emt2025.model.domain.User;
import mk.ukim.finki.emt2025.model.domain.WishList;
import mk.ukim.finki.emt2025.model.exceptions.BookAlreadyInWishListException;
import mk.ukim.finki.emt2025.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt2025.model.exceptions.WishListNotFoundException;
import mk.ukim.finki.emt2025.repository.WishListRepository;
import mk.ukim.finki.emt2025.service.domain.BookService;
import mk.ukim.finki.emt2025.service.domain.UserService;
import mk.ukim.finki.emt2025.service.domain.WishListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class WishListServiceImpl implements WishListService {
    private final WishListRepository wishListRepository;
    private final UserService userService;
    private final BookService bookService;

    public WishListServiceImpl(WishListRepository wishListRepository, UserService userService, BookService bookService) {
        this.wishListRepository = wishListRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    @Override
    public List<Book> listAllProductsInShoppingCart(Long cartId) {
        if (wishListRepository.findById(cartId).isEmpty())
            throw new WishListNotFoundException(cartId);
        return wishListRepository.findById(cartId).get().getBooks();

    }

    @Override
    public Optional<WishList> getActiveShoppingCart(String username) {
        User user = userService.findByUsername(username);

        return Optional.of(wishListRepository.findByUser(
                user
        ).orElseGet(() -> wishListRepository.save(new WishList(user))));


    }

    @Override
    public Optional<WishList> addProductToShoppingCart(String username, Long productId) {
        if (getActiveShoppingCart(username).isPresent()) {
            WishList shoppingCart = getActiveShoppingCart(username).get();

            Book product = bookService.findById(productId)
                    .orElseThrow(() -> new BookNotFoundException(productId));
            if (!shoppingCart.getBooks().stream().filter(i -> i.getId().equals(productId)).toList().isEmpty())
                throw new BookAlreadyInWishListException(productId, username);
            shoppingCart.getBooks().add(product);
            return Optional.of(wishListRepository.save(shoppingCart));
        }
        return Optional.empty();

    }

    @Override
    public Optional<WishList> rentAllBooks(String username) {
        User user = userService.findByUsername(username);
        WishList wishList = getActiveShoppingCart(username).get();
        List<Book> books=wishList.getBooks();
        books.forEach(b->b.setAvailableCopies(b.getAvailableCopies()-1));
        books.forEach(bookService::save);
        return Optional.of(wishListRepository.findByUser(
                user
        ).orElseGet(() -> wishListRepository.save(new WishList(user))));
    }
}
