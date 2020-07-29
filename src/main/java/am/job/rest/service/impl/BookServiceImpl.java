package am.job.rest.service.impl;

import am.job.rest.model.Book;
import am.job.rest.repository.BookRepository;
import am.job.rest.service.abstraction.BookService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EntityManager em;

    @Override
    public List<Book> getAll() throws RuntimeException {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(int id) throws RuntimeException {
        return bookRepository.findById(id);
    }

//    @Override
//   public List<Book> search(Book book) throws RuntimeException {
//        return bookRepository.findAll(Example.of(book));
//    }


    @Override
    public List<Book> searchByName(String name) throws RuntimeException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> card = cq.from(Book.class);
        Predicate carNumberPredict = cb.like(card.get("name"), "%" + name + "%");
        cq.where(carNumberPredict);
        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Book save(Book book) throws RuntimeException {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book update(Book book) throws RuntimeException {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void delete(int id) throws RuntimeException {
        bookRepository.deleteById(id);
    }
}
