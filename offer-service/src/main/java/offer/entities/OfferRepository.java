package offer.entities;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OfferRepository extends CrudRepository<Offer, Long> {
    @Modifying
    @Query(value = "UPDATE offer SET status= 'ACTIVE' where status = 'CREATED' AND start_date < ?1", nativeQuery = true)
    int updateStatusToActive(Date currentDate);

    @Modifying
    @Query(value = "UPDATE offer SET status= 'EXPIRED' WHERE status = 'ACTIVE' AND expiry_date < ?1", nativeQuery = true)
    int updateStatusToExpired(Date currentDate);

    List<Offer> findOfferByStatus(String status);

    @Modifying
    @Query(value = "UPDATE offer SET status = 'CANCELLED' WHERE id = ?1", nativeQuery = true)
    int updateStatusToCancelled(int id);

}
