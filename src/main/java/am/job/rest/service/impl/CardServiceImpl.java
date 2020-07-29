package am.job.rest.service.impl;

import am.job.rest.model.Card;
import am.job.rest.repository.CardRepository;
import am.job.rest.service.abstraction.CardService;
import am.job.rest.util.cardException.DuplicateDataException;
import am.job.rest.util.transfer.exceptions.NumberSizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;


    @Override
    @Transactional
    public Card sign_up(Card card) throws DuplicateDataException, NumberSizeException, RuntimeException {
        NumberSizeException.check(card.getNumber().length() != 16, "wrong.size");
        DuplicateDataException.check(cardRepository.existsByNumber(card.getNumber()), "duplicate.number");
        return cardRepository.save(card);
    }

    @Override
    @Transactional
    public Card updateCardInfo(Card card) throws NumberSizeException, DuplicateDataException, RuntimeException {
        NumberSizeException.check(card.getNumber().length() != 16, "wrong.size");
        DuplicateDataException.check(cardRepository.existsByNumberAndIdNot(card.getNumber(), card.getId()), "duplicate.employee.by.number");
        return cardRepository.save(card);
    }

    @Override
    @Transactional
    public void deleteById(int id) throws RuntimeException {
        cardRepository.deleteById(id);
    }

    @Override
    public List<Card> getAll() throws RuntimeException {
        return cardRepository.findAll();
    }

    @Override
    public Optional<Card> findById(int id) throws RuntimeException {
        return cardRepository.findById(id);
    }
}
