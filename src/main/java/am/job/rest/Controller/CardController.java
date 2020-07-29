package am.job.rest.Controller;

import am.job.rest.model.Card;
import am.job.rest.model.Employee;
import am.job.rest.service.abstraction.CardService;
import am.job.rest.util.cardException.DuplicateDataException;
import am.job.rest.util.transfer.exceptions.NumberSizeException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Path;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Card> getAll() throws RuntimeException {
        return cardService.getAll();
    }


    @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@PathVariable int id) throws RuntimeException {
        return ResponseEntity.of(cardService.findById(id));
    }

    @Transactional
    @PostMapping(path = "/del", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sign_up(@Valid @RequestBody Card card) throws DuplicateDataException, NumberSizeException, RuntimeException {
        return ResponseEntity.ok(cardService.sign_up(card));
    }

    @Transactional
    @PutMapping(path = "/up/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@Valid @RequestBody Card card, @PathVariable int id) throws DuplicateDataException, NumberSizeException, RuntimeException {
        card.setId(id);
        return ResponseEntity.ok(cardService.updateCardInfo(card));
    }

    @Transactional
    @DeleteMapping("/del/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        cardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
