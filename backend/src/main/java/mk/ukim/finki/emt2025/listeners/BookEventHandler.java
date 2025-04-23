package mk.ukim.finki.emt2025.listeners;

import mk.ukim.finki.emt2025.model.events.BookCreatedEvent;
import mk.ukim.finki.emt2025.service.domain.BookService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookEventHandler {
    private  final BookService bookService;

    public BookEventHandler(BookService bookService) {
        this.bookService = bookService;
    }
    @EventListener
    public  void onBookCreated(BookCreatedEvent event){
        this.bookService.refreshMaterializedView();

    }
}
