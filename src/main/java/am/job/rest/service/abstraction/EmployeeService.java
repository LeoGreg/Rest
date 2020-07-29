package am.job.rest.service.abstraction;

import am.job.rest.model.Employee;
import am.job.rest.util.cardException.DuplicateDataException;
import am.job.rest.util.employeeValid.employeeExceptions.AccessNumberContainsLettersException;
import am.job.rest.util.employeeValid.employeeExceptions.AccessNumberOutOfSizeRestrictionError;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAll();

    Optional<Employee> findById(int id);

    Employee save(Employee employee) throws DuplicateDataException, AccessNumberOutOfSizeRestrictionError, AccessNumberContainsLettersException;

    Employee update(Employee employee) throws DuplicateDataException, AccessNumberOutOfSizeRestrictionError, AccessNumberContainsLettersException, AccessNumberContainsLettersException, AccessNumberOutOfSizeRestrictionError;

    @Transactional
    void deleteById(int id) throws RuntimeException;
}
