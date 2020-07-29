package am.job.rest.repository;



import am.job.rest.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {


    Transfer save(Transfer transfer);


    @Query(nativeQuery = true, value = "SELECT * FROM transfer WHERE senderNumber=:Number")
    Transfer getBySenderNumber(@Param("Number") String number);
}
