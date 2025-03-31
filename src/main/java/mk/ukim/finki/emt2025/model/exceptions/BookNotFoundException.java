package mk.ukim.finki.emt2025.model.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long bookId) {
        super("Book not found with this id "+bookId);
    }
}
