package am.job.rest.service.impl;

import am.job.rest.model.Employee;
import am.job.rest.repository.EmployeeRepository;
import am.job.rest.service.abstraction.EmployeeService;
import am.job.rest.util.cardException.DuplicateDataException;
import am.job.rest.util.employeeValid.AccessNumberValidation;
import am.job.rest.util.employeeValid.employeeExceptions.AccessNumberContainsLettersException;
import am.job.rest.util.employeeValid.employeeExceptions.AccessNumberOutOfSizeRestrictionError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) throws DuplicateDataException, RuntimeException, AccessNumberContainsLettersException, AccessNumberOutOfSizeRestrictionError {
        AccessNumberValidation.checkIfThereAreLetters(employee.getAccessNumber());
        AccessNumberValidation.validate(employee.getAccessNumber());
        DuplicateDataException.check(employeeRepository.existsByAccessNumber(employee.getAccessNumber()), "duplicate.employee.by.number");
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional// not done yet
    public Employee update(Employee employee) throws DuplicateDataException, RuntimeException, AccessNumberContainsLettersException, AccessNumberOutOfSizeRestrictionError {
        AccessNumberValidation.checkIfThereAreLetters(employee.getAccessNumber());
        AccessNumberValidation.validate(employee.getAccessNumber());
        DuplicateDataException.check(employeeRepository.existsByAccessNumberAndIdNot(employee.getAccessNumber(), employee.getId()), "duplicate.employee.by.number");
        return employeeRepository.update(employee.getId(), employee.getUpdatedAt(), employee.getFirstName(), employee.getLastName(), employee.getBalance(), employee.getAccessNumber());
    }

    @Override
    @Transactional
    public void deleteById(int id) throws RuntimeException {
        employeeRepository.deleteById(id);
    }
}
