package am.job.rest.Controller;

import am.job.rest.model.Employee;
import am.job.rest.service.abstraction.EmployeeService;
import am.job.rest.util.cardException.DuplicateDataException;
import am.job.rest.util.employeeValid.employeeExceptions.AccessNumberContainsLettersException;
import am.job.rest.util.employeeValid.employeeExceptions.AccessNumberOutOfSizeRestrictionError;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Log4j2
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping(path = "/allOld", produces = MediaType.APPLICATION_JSON_VALUE)//veradardznum em json
    public ResponseEntity getAllOld() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@PathVariable int id) {
        return ResponseEntity.of(employeeService.findById(id));
    }

    @Transactional
    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)// spasum em json
    public ResponseEntity add(@Valid @RequestBody Employee employee) throws DuplicateDataException, RuntimeException, AccessNumberOutOfSizeRestrictionError, AccessNumberContainsLettersException {
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @PutMapping(path = "/up/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)// spasum em json
    @Transactional
    public ResponseEntity update(@Valid @RequestBody Employee employee, @PathVariable int id) throws DuplicateDataException, RuntimeException, AccessNumberOutOfSizeRestrictionError, AccessNumberContainsLettersException {
        employee.setId(id);
        return ResponseEntity.ok(employeeService.update(employee));
    }

    @DeleteMapping(path = "/del/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable int id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
