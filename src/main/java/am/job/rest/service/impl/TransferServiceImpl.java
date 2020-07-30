package am.job.rest.service.impl;

import am.job.rest.model.Card;
import am.job.rest.model.Transfer;
import am.job.rest.repository.CardRepository;
import am.job.rest.repository.TransferRepository;
import am.job.rest.service.abstraction.TransferService;
import am.job.rest.util.transfer.exceptions.*;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Data
@Log4j2
@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private CardRepository cardRepository;



    @Override
    @Transactional
    public Transfer transfer(Transfer transfer) throws RuntimeException, NumberSizeException, NotSignedUpCardException /*ExpirationOutOfDateException*/, SameNumberException, WrongBalanceException, ExpirationOutOfDateException {
        SameNumberException.check(transfer.getGetterNumber() == transfer.getSenderNumber(), "same.number");
        NumberSizeException.check(transfer.getGetterNumber().length() != 16, "wrong.size.for.card");
        NumberSizeException.check(transfer.getSenderNumber().length() != 16, "wrong.size.for.card");
        NotSignedUpCardException.check(!cardRepository.existsByNumber(transfer.getSenderNumber()), "no.card");
        NotSignedUpCardException.check(!cardRepository.existsByNumber(transfer.getGetterNumber()), "no.card");
        Card sender = cardRepository.getByNumber(transfer.getSenderNumber());
        Card getter = cardRepository.getByNumber(transfer.getGetterNumber());
        WrongBalanceException.check(sender.getBalance() == 0 || sender.getBalance() - transfer.getCash() < 0, "wrong.balance");
        ExpirationOutOfDateException.check(new Date().getTime() - getter.getExpirationDate().getTime() >= 0, "card.expiration.outrun");
       ExpirationOutOfDateException.check(new Date().getTime() - sender.getExpirationDate().getTime() >= 0, "card.expiration.outrun");
        sender.setBalance(sender.getBalance() - transfer.getCash());
        getter.setBalance(getter.getBalance() + transfer.getCash());
        cardRepository.save(sender);
        cardRepository.save(getter);
        return transferRepository.save(transfer);
    }

//    @Override
//    @Transactional
//    public Transfer enterInfoForTransferring(Transfer transfer) throws RuntimeException,SameNumberException, NumberSizeException, WrongBalanceException, NotSignedUpCardException/*ExpirationOutOfDateException*/ {
//        SameNumberException.check(transfer.getGetterNumber() == transfer.getSenderNumber(), "same.number");
//        NumberSizeException.check(transfer.getGetterNumber().length() != 16, "wrong.size.for.card");
//        NumberSizeException.check(transfer.getSenderNumber().length() != 16, "wrong.size.for.card");
//        NotSignedUpCardException.check(!cardRepository.existsByNumber(transfer.getSenderNumber()), "no.card");
//        NotSignedUpCardException.check(!cardRepository.existsByNumber(transfer.getGetterNumber()), "no.card");
//        Card sender = cardRepository.getByNumber(transfer.getSenderNumber());
//        Card getter = cardRepository.getByNumber(transfer.getGetterNumber());
//        WrongBalanceException.check(sender.getBalance() == 0 || sender.getBalance() - transfer.getCash() < 0, "wrong.balance");
///*
//        ExpirationOutOfDateException.check(new Date().getTime() - getter.getExpirationDate().getTime() >= 0, "card.expiration.outrun");
//*/
////        ExpirationOutOfDateException.check(new Date().getTime() - sender.getExpirationDate().getTime() >= 0, "card.expiration.outrun");
//        return transferRepository.save(transfer);
//    }

    @Override
    public List<Transfer> getTransferringHistory() throws RuntimeException{
        return transferRepository.findAll();
    }

    @Override
    public Transfer getBySenderNumber(String number) throws NotFoundException ,RuntimeException{
        NotFoundException.check(cardRepository.getByNumber(number)==null,"no.transfer.by.number");
        return transferRepository.getBySenderNumber(number);
    }

    @Override
    @Transactional
    public void deleteInfoBySenderNumber(String number) throws RuntimeException{
        transferRepository.deleteById(transferRepository.getBySenderNumber(number).getId());
    }
}
