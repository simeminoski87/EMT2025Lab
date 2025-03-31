package mk.ukim.finki.emt2025.model.exceptions;

public class BookAlreadyInWishListException extends RuntimeException {
    public BookAlreadyInWishListException(Long bookId, String username) {
        super(String.format("Book with id: %d already exists in wish list for user with username %s", bookId, username));
    }
}


