package am.job.rest.repository;


import am.job.rest.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.PATCH;
import java.util.Date;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Transactional
    Employee save(Employee employee);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE employee SET updatedAt=:D, firstName=:f,lastName=:l,balance=:b,accessNumber=:Acc WHERE id=:Id")
    Employee update(@Param("Id") int Id, @Param("D") Date D, @Param("f") String f, @Param("l") String l, @Param("b") double b, @Param("Acc") String Acc);



    boolean existsByAccessNumber(String number);

    boolean existsByAccessNumberAndIdNot(String number, int id);
}
