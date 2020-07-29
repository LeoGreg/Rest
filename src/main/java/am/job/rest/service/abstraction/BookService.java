package am.job.rest.service.abstraction;


import am.job.rest.model.Book;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAll();

    Optional<Book> findById(int id);

//    List<Book> search(Book book) throws RuntimeException;

    List<Book> searchByName(String name) throws RuntimeException;

    Book save(Book book);

    @Transactional
    Book update(Book book);

    @Transactional
    void delete(int id);
}
