package mk.ukim.finki.emt2025.model.exceptions;

public class NoAvailableCopiesOfTheBook extends RuntimeException {
    public NoAvailableCopiesOfTheBook(String name) {
        super("No available copies of book: "+name);
    }
}
