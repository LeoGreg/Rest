package am.job.rest.service.abstraction;


import am.job.rest.model.Card;
import am.job.rest.util.cardException.DuplicateDataException;
import am.job.rest.util.transfer.exceptions.NumberSizeException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CardService {
    Card sign_up(Card card) throws DuplicateDataException, NumberSizeException;

    Card updateCardInfo(Card card) throws NumberSizeException, DuplicateDataException;

    @Transactional
    void deleteById(int id) throws RuntimeException;

    List<Card> getAll();

    Optional<Card> findById(int id);
}
