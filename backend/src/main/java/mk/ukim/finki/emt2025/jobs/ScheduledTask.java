package mk.ukim.finki.emt2025.jobs;

import mk.ukim.finki.emt2025.service.domain.BookService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    private final BookService bookService;

    public ScheduledTask(BookService bookService) {
        this.bookService = bookService;
    }
@Scheduled(fixedDelay = 5000)
    public void refreshMaterializedView(){
      //  this.bookService.refreshMaterializedView();
    }
}
