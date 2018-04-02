package offer.entities;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MerchantRepository extends CrudRepository<Merchant, Long> {
    List<Merchant> findMerchantByName(String name);
}
