package am.job.rest.repository;


import am.job.rest.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM card WHERE number=:Number")
    Card getByNumber(@Param("Number") String number);

    Card save(Card card);

    boolean existsByNumber(String number);

    boolean existsByNumberAndIdNot(String num, int id);
}
