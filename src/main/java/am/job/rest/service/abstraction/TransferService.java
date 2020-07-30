package am.job.rest.service.abstraction;

import am.job.rest.model.Transfer;
import am.job.rest.util.transfer.exceptions.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransferService {
    Transfer transfer(Transfer transfer) throws NumberSizeException, NotSignedUpCardException, ExpirationOutOfDateException, SameNumberException, WrongBalanceException;

    /*@Transactional
    Transfer enterInfoForTransferring(Transfer transfer) throws SameNumberException, NumberSizeException, WrongBalanceException, NotSignedUpCardException, ExpirationOutOfDateException;
*/
    List<Transfer> getTransferringHistory();

    Transfer getBySenderNumber(String number) throws NotFoundException;

    void deleteInfoBySenderNumber(String number);
}
