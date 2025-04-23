package mk.ukim.finki.emt2025.repository;

import mk.ukim.finki.emt2025.model.views.BookPerCountryView;
import mk.ukim.finki.emt2025.repository.views.BooksPerCountryViewRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BooksViewTest {
    @Autowired
    private BooksPerCountryViewRepository perCountryViewRepository;

    @Test
    public void testBooksPerCountryViewRepositoryFindAll(){
        List<BookPerCountryView> list=this.perCountryViewRepository.findAll();
        Assert.assertEquals(1,list.size());
    }
}
