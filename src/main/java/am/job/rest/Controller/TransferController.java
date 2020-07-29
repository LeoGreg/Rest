package am.job.rest.Controller;


import am.job.rest.model.Transfer;
import am.job.rest.service.abstraction.TransferService;
import am.job.rest.util.transfer.exceptions.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import javax.ws.rs.Path;
import javax.xml.soap.SAAJResult;
import java.awt.*;

@Log4j2
@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll() {
        return ResponseEntity.ok(transferService.getTransferringHistory());
    }

    @GetMapping(value = "/num/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBySenderNumber(@PathVariable String number) throws NotFoundException, RuntimeException {
        return ResponseEntity.ok(transferService.getBySenderNumber(number));
    }

    @Transactional
    @DeleteMapping("/del/{num}")// nayel
    public ResponseEntity delete(@PathVariable String num) throws RuntimeException {
        transferService.deleteInfoBySenderNumber(num);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addCardInfo(@Valid @RequestBody Transfer transfer) throws WrongBalanceException, RuntimeException, ExpirationOutOfDateException, NotSignedUpCardException, NumberSizeException, SameNumberException {
        return ResponseEntity.ok(transferService.enterInfoForTransferring(transfer));
    }

    @PutMapping(value = "/up/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity transfer(@Valid @RequestBody Transfer transfer, @PathVariable int id) throws SameNumberException,RuntimeException, WrongBalanceException, NotSignedUpCardException, NumberSizeException, ExpirationOutOfDateException {
        transfer.setId(id);
        return ResponseEntity.ok(transferService.transfer(transfer));
    }


}
