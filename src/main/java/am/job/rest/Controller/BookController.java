package am.job.rest.Controller;

import am.job.rest.model.Book;
import am.job.rest.service.abstraction.BookService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.validation.OverridesAttribute;
import javax.validation.Valid;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import java.util.List;
@Log4j2
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @ResponseStatus(HttpStatus.ACCEPTED)//202
    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Book> getAll() throws RuntimeException {
        return bookService.getAll();
    }

    @GetMapping(path = "/allB", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllBooks() throws RuntimeException {
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping(path = "/byId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findById(@PathVariable int id) throws RuntimeException {
        return ResponseEntity.of(bookService.findById(id));
    }

    @GetMapping(path = "/searchByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchByName(@PathVariable String name) throws RuntimeException {
        return ResponseEntity.ok(bookService.searchByName(name));
    }

    //    @GetMapping(path = "/searchEx/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity search(@PathVariable String name) throws RuntimeException {
//        return ResponseEntity.ok(bookService.search(name));
//    }
    @Transactional
    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@Valid @RequestBody Book book) throws RuntimeException {
        return ResponseEntity.ok(bookService.save(book));
    }

    @Transactional
    @PutMapping(path = "/add/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@Valid @RequestBody Book book, @PathVariable int id) throws RuntimeException {
        book.setId(id);
        return ResponseEntity.ok(bookService.save(book));
    }

    @Transactional
    @DeleteMapping(path = "/del/{id}")
    public ResponseEntity delete(@PathVariable int id) throws RuntimeException {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
