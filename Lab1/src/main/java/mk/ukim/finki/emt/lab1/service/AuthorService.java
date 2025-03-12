package mk.ukim.finki.emt.lab1.service;

import mk.ukim.finki.emt.lab1.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AuthorService {
    List<Author> findAll();

}
